/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.veranopolis.rpgrf;

import br.edu.ifrs.veranopolis.rpgrf.dados.Evento;
import br.edu.ifrs.veranopolis.rpgrf.gui.OptionsFrame;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameMaster {

    private List<Evento> eventos = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    // Método para carregar os arquivos JSON da pasta scenes
    public void carregarEventos() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);  // Ignorar campos desconhecidos
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);  // Verificar null para tipos primitivos
        File folder = new File("scenes");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));

        if (files != null) {
            for (File file : files) {
                Evento evento = objectMapper.readValue(file, Evento.class);
                eventos.add(evento);
            }
        } else {
            System.out.println("Nenhum arquivo de cena encontrado na pasta.");
        }

        /*for (Evento evento : eventos) {
            for (Evento.Nivel nivel : evento.getLevels()) {
                for (Evento.Opcao opcao : nivel.getOptions()) {
                    System.out.println("Action: " + opcao.getAction());
                    System.out.println("Success: " + opcao.getSuccessContext());
                    System.out.println("Failure: " + opcao.getFailureContext());
                }
            }
        }*/
    }

    // Método para exibir o menu principal com os eventos
    public void exibirMenuPrincipal() {
        System.out.println("Escolha um evento para jogar:");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println((i + 1) + ". " + eventos.get(i).getTitle());
        }
        int escolha = scanner.nextInt();
        //executarEvento(eventos.get(escolha - 1));
        OptionsFrame of = new OptionsFrame(eventos.get(escolha - 1));
        of.setVisible(true);
    }

    // Método para executar o evento escolhido
    public void executarEvento(Evento evento) {
        System.out.println("Informação ao game master: " + evento.getDescription());
        exibirNivel(evento.getLevels().get(0), evento);
    }

    private void exibirNivel(Evento.Nivel nivel, Evento evento) {
        System.out.println("Informação ao jogador: " + nivel.getContext());

        System.out.println("Escolha uma ação:");
        for (int i = 0; i < nivel.getOptions().size(); i++) {
            System.out.println((i + 1) + ". " + nivel.getOptions().get(i).getAction());
        }

        int escolha = scanner.nextInt();
        Evento.Opcao opcaoEscolhida = nivel.getOptions().get(escolha - 1);

        // Determinar aleatoriamente o resultado
        boolean sucesso = random.nextBoolean();
        if (sucesso && opcaoEscolhida.getSuccessContext() != null) {
            System.out.println("Informação ao jogador: " + opcaoEscolhida.getSuccessContext());
        } else if (!sucesso && opcaoEscolhida.getFailureContext() != null) {
            System.out.println("Informação ao jogador: " + opcaoEscolhida.getFailureContext());
        } else {
            System.out.println("Informação ao jogador: Resultado indefinido.");
        }

        // Verificar se há um resultado final (outcome)
        if (opcaoEscolhida.getOutcome() != null) {
            System.out.println("Resultado final: " + opcaoEscolhida.getOutcome());
            return;  // Encerra o nível se o resultado final for atingido
        }

        // Buscar o próximo nível, se houver
        String proximoNivelId = opcaoEscolhida.getNext();
        if (proximoNivelId != null) {
            Evento.Nivel proximoNivel = buscarNivelPorId(proximoNivelId, evento);
            if (proximoNivel != null) {
                exibirNivel(proximoNivel, evento);
            }
        }
    }

    // Método para buscar um nível pelo ID ou número
    private Evento.Nivel buscarNivelPorId(String nivelId, Evento evento) {
        for (Evento.Nivel nivel : evento.getLevels()) {
            if (String.valueOf(nivel.getLevel()).equals(nivelId)) {
                return nivel;
            }
        }
        return null;
    }
}
