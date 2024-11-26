/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.veranopolis.rpgrf.gui;

import br.edu.ifrs.veranopolis.rpgrf.dados.Personagem;
import br.edu.ifrs.veranopolis.rpgrf.dados.Pessoa;
import javax.swing.*;
import java.awt.*;

public class CriarPersonagem extends FundoFrame {
    private JComboBox<String> comboMotivacao;
    private JComboBox<String> comboAlianca;
    private JSlider sliderAstucia, sliderInfluencia, sliderCombate, sliderOratoria, sliderLealdade;
    private JLabel lblPontosDisponiveis;
    private int pontosDisponiveis = 20;

    public CriarPersonagem() {
        super("fundo_maior.png");
        setTitle("Criar Personagem");
        setLocationRelativeTo(null);

        // Painel principal com fundo de imagem
        JPanel painel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = Toolkit.getDefaultToolkit().getImage("fundo.jpg");
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        painel.setOpaque(false);
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setOpaque(false);

        // Campo de nome
        JTextField campoNome = new JTextField(20);
        painel.add(criarLinha("Nome: ", campoNome));

        // Combobox para motivação
        comboMotivacao = new JComboBox<>(Pessoa.motivacoes);
        painel.add(criarLinha("Motivação: ", comboMotivacao));

        // Combobox para aliança
        comboAlianca = new JComboBox<>(Pessoa.aliancas);
        painel.add(criarLinha("Aliança: ", comboAlianca));

        // Sliders para atributos
        sliderAstucia = criarSlider("Astúcia");
        sliderInfluencia = criarSlider("Influência");
        sliderCombate = criarSlider("Combate");
        sliderOratoria = criarSlider("Oratória");
        sliderLealdade = criarSlider("Lealdade");

        painel.add(sliderAstucia);
        painel.add(sliderInfluencia);
        painel.add(sliderCombate);
        painel.add(sliderOratoria);
        painel.add(sliderLealdade);

        // Pontos disponíveis
        lblPontosDisponiveis = new JLabel("Pontos disponíveis: " + pontosDisponiveis);
        lblPontosDisponiveis.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(lblPontosDisponiveis);

        // Botão para criar personagem
        JButton btnCriar = new JButton("Criar Personagem");
        btnCriar.addActionListener(e -> {
            if (pontosDisponiveis > 0) {
                JOptionPane.showMessageDialog(this, "Distribua todos os pontos antes de continuar.");
                return;
            }

            String nome = campoNome.getText();
            String motivacao = (String) comboMotivacao.getSelectedItem();
            String alianca = (String) comboAlianca.getSelectedItem();

            Personagem personagem = new Personagem(nome);
            personagem.setMotivacao(motivacao);
            personagem.setAlianca(alianca);
            personagem.setAstucia(sliderAstucia.getValue());
            personagem.setInfluencia(sliderInfluencia.getValue());
            personagem.setCombate(sliderCombate.getValue());
            personagem.setOratoria(sliderOratoria.getValue());
            personagem.setLealdade(sliderLealdade.getValue());

            JOptionPane.showMessageDialog(this, "Personagem criado com sucesso!");
        });
        painel.add(btnCriar);

        add(painel);
        setVisible(true);
    }

    private JPanel criarLinha(String label, JComponent componente) {
        JPanel linha = new JPanel();
        linha.setLayout(new FlowLayout(FlowLayout.LEFT));
        linha.setOpaque(false);
        linha.add(new JLabel(label));
        linha.add(componente);
        return linha;
    }

    private JSlider criarSlider(String titulo) {
        JSlider slider = new JSlider(0, 15, 0);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);

        // Adicionando lógica para restringir aumento
        slider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (source.getValueIsAdjusting()) {
                int totalPontos = calcularPontosTotais();
                int valorAtual = source.getValue();
                int novoValor = source.getValue();

                // Restringir aumento se pontos disponíveis forem 0
                if (novoValor > valorAtual && pontosDisponiveis <= 0) {
                    source.setValue(valorAtual); // Impedir aumento
                } else {
                    atualizarPontos();
                }
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.add(new JLabel(titulo), BorderLayout.NORTH);
        panel.add(slider, BorderLayout.CENTER);
        return slider;
    }

    private int calcularPontosTotais() {
        return sliderAstucia.getValue() + sliderInfluencia.getValue() +
               sliderCombate.getValue() + sliderOratoria.getValue() +
               sliderLealdade.getValue();
    }

    private void atualizarPontos() {
        int totalPontos = calcularPontosTotais();
        pontosDisponiveis = 20 - totalPontos;
        lblPontosDisponiveis.setText("Pontos disponíveis: " + pontosDisponiveis);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CriarPersonagem::new);
    }
}
