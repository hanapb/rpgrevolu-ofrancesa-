/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.veranopolis.rpgrf.gui;

import br.edu.ifrs.veranopolis.rpgrf.GameMaster;
import br.edu.ifrs.veranopolis.rpgrf.dados.Evento;
import br.edu.ifrs.veranopolis.rpgrf.dados.Evento.Nivel;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class OutcomeFrame extends FundoFrame {

    private Evento evento;

    public OutcomeFrame(Evento evento, Evento.Opcao opcao, boolean sucesso) {
        super("fundo.png");
        this.evento = evento;

        // Define o título do evento
        titleLabel.setText(evento.getTitle());

        // Exibe o contexto do evento em TextPanel
        textPanel.setText(sucesso ? opcao.getSuccessContext() : opcao.getFailureContext());

        JButton proximo = new JButton("Entendido");
        buttonPanel.add(proximo);

        proximo.addActionListener(e -> {
            if(opcao.getNext().equals("fim")) {
                dispose();
            } else {
                Nivel nivel = GameMaster.buscarNivelPorId(opcao.getNext(), evento);
                if(nivel == null) {
                    System.out.println("O nivel com id " + opcao.getNext() + " não foi encontrado!");
                } else {
                    OptionsFrame of = new OptionsFrame(evento, nivel);
                    of.setVisible(true);
                    dispose();
                }
            }
        });
        
        /*JButton sair = new JButton("Sair");
        buttonPanel.add(sair);

        sair.addActionListener(e -> {
            dispose();
        });*/

        add(buttonPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setVisible(true);
        textPanel.startAnimation();
    }

}
