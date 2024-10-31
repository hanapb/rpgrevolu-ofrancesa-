/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.veranopolis.rpgrf.gui;

import javax.swing.*;
import java.awt.*;
import br.edu.ifrs.veranopolis.rpgrf.dados.NPC;

public class NPCPanel extends JPanel {

    public NPCPanel(NPC npc) {
        setLayout(new BorderLayout());

        //setBackground(new Color(240, 240, 240));
        setOpaque(false);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel avatarLabel = new JLabel();
        avatarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // a definir
        avatarLabel.setPreferredSize(new Dimension(100, 100));

        try {
            ImageIcon avatarIcon = new ImageIcon(getClass().getResource(npc.getImagem()));
            Image avatarImage = avatarIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            avatarLabel.setIcon(new ImageIcon(avatarImage));
        } catch (Exception e) {
            System.err.println("Erro ao carregar a imagem de avatar: " + e.getMessage());
            avatarLabel.setText("Sem imagem");
        }

        // informações do NPC
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        JLabel nameLabel = new JLabel("Nome: " + npc.getNome());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel roleLabel = new JLabel("Papel: " + npc.getPapel());
        JLabel allianceLabel = new JLabel("Aliança: " + npc.getAlianca());

        // atributos
        JLabel astuciaLabel = new JLabel("Astúcia: " + npc.getAstucia());
        JLabel influenciaLabel = new JLabel("Influência: " + npc.getInfluencia());
        JLabel combateLabel = new JLabel("Combate: " + npc.getCombate());
        JLabel oratoriaLabel = new JLabel("Oratória: " + npc.getOratoria());
        JLabel lealdadeLabel = new JLabel("Lealdade: " + npc.getLealdade());

        infoPanel.add(nameLabel);
        infoPanel.add(roleLabel);
        infoPanel.add(allianceLabel);
        infoPanel.add(Box.createVerticalStrut(10)); // espaçamento
        infoPanel.add(astuciaLabel);
        infoPanel.add(influenciaLabel);
        infoPanel.add(combateLabel);
        infoPanel.add(oratoriaLabel);
        infoPanel.add(lealdadeLabel);

        add(avatarLabel, BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);
    }
}

