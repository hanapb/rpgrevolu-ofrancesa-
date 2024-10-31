/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.veranopolis.rpgrf.gui;

import br.edu.ifrs.veranopolis.rpgrf.dados.Evento;
import br.edu.ifrs.veranopolis.rpgrf.dados.NPC;
import javax.swing.*;
import java.awt.*;

public class ConfrontoFrame extends FundoFrame {

    private Evento evento;
    private NPC npc;

    public ConfrontoFrame(Evento evento, NPC npc) {
        this(evento, evento.getLevels().get(0), npc);
    }

    public ConfrontoFrame(Evento evento, Evento.Nivel nivel, NPC npc) {
        super("fundo.png");
        this.evento = evento;
        this.npc = npc;

        titleLabel.setText(evento.getTitle());

        textPanel.setText(nivel.getContext());

        for (Evento.Opcao opcao : nivel.getOptions()) {
            JButton button = new JButton(opcao.getAction());
            buttonPanel.add(button);

            button.addActionListener(e -> {
                // Logica de sucesso/falha
                double d = Math.random();
                OutcomeFrame of = new OutcomeFrame(evento, opcao, d > 0.5);
                dispose();
                of.setVisible(true);
            });
        }

        JButton sair = new JButton("Sair");
        buttonPanel.add(sair);
        sair.addActionListener(e -> dispose());

        NPCPanel npcPanel = new NPCPanel(npc);
        add(npcPanel, BorderLayout.EAST);

        add(buttonPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setVisible(true);
        textPanel.startAnimation();
    }
}
