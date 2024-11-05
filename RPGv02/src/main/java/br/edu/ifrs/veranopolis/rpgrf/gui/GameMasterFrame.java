/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.veranopolis.rpgrf.gui;

import br.edu.ifrs.veranopolis.rpgrf.dados.Evento;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class GameMasterFrame extends FundoFrame {

    private JList<Evento> eventoList;
    private DefaultListModel<Evento> listModel;
    private List<Evento> eventos;

    public GameMasterFrame() {
        super("fundo.png", "ocllo.mp3");
        setTitle("GameMaster - Escolha um Evento");
        eventos = new ArrayList<>();

        // Carrega os eventos da pasta scenes
        try {
            carregarEventos();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configura o painel com a lista de eventos
        listModel = new DefaultListModel<>();
        eventos.forEach(listModel::addElement);
        eventoList = new JList<>(listModel);
        eventoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eventoList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Evento) {
                    setText(((Evento) value).getTitle());
                }
                return renderer;
            }
        });

        // Painel para a lista de eventos
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setOpaque(false);
        listPanel.setBorder(BorderFactory.createTitledBorder("Eventos Disponíveis"));
        listPanel.add(new JScrollPane(eventoList), BorderLayout.CENTER);

        // Botão para abrir o evento selecionado
        JButton abrirButton = new JButton("Abrir Evento");
        abrirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirEventoSelecionado();
            }
        });

        // Adiciona o painel de lista e o botão ao fundo
        buttonPanel.add(abrirButton);
        JButton sair = new JButton("Sair");
        buttonPanel.add(sair);
        sair.addActionListener(e -> {
            dispose();
        });

        add(listPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void carregarEventos() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        File folder = new File("scenes");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));

        if (files != null) {
            for (File file : files) {
                Evento evento = objectMapper.readValue(file, Evento.class);
                eventos.add(evento);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum arquivo de cena encontrado na pasta.");
        }
    }

    private void abrirEventoSelecionado() {
        Evento eventoSelecionado = eventoList.getSelectedValue();
        if (eventoSelecionado != null) {
            if (musicaPlayer != null) {
                musicaPlayer.close();
            }
            OptionsFrame oFrame = new OptionsFrame(eventoSelecionado, eventoSelecionado.getLevels().get(0));
            oFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um evento para abrir.");
        }
    }

    public static void main(String[] args) {
        new GameMasterFrame().setVisible(true);
    }
}
