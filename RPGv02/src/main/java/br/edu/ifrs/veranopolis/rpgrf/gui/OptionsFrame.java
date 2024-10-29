/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.veranopolis.rpgrf.gui;

import br.edu.ifrs.veranopolis.rpgrf.dados.Evento;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OptionsFrame extends FundoFrame {

    private Evento evento;
    
    public OptionsFrame(Evento evento) {
        this(evento, evento.getLevels().get(0));
    }
    
    public OptionsFrame(Evento evento, Evento.Nivel nivel) {
        super("fundo.png");  
        this.evento = evento;

        // Define o título do evento
        titleLabel.setText(evento.getTitle());

        // Exibe o contexto do evento em TextPanel
        textPanel.setText(nivel.getContext());


        // Adiciona botões com base nas opções do evento
        for (Evento.Opcao opcao : nivel.getOptions()) {
            JButton button = new JButton(opcao.getAction());
            buttonPanel.add(button);

            button.addActionListener(e -> {
                // Exibir sucesso ou falha, dependendo da lógica do evento
                System.out.println("Teste " + opcao.getId());
            });
        }

        add(buttonPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);     
        setVisible(true);
        textPanel.startAnimation();
    }
    
}
