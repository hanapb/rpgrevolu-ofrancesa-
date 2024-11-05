/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.veranopolis.rpgrf.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class FundoFrame extends JFrame {

    private BufferedImage backgroundImage;
    private Point initialClick;
    protected JLabel titleLabel;
    protected TextPanel textPanel;
    protected JPanel buttonPanel;
    private String musicaPath;
    protected Player musicaPlayer;

    public FundoFrame(String nomeArquivo) {
        this(nomeArquivo, null);  // Chama o novo construtor com música nula
    }

    public FundoFrame(String nomeArquivo, String musica) {
        super("Fundo Frame");
        setUndecorated(true);
        this.musicaPath = musica;

        // Tenta carregar a imagem do classpath
        try {
            String caminhoImagem = "/fundos/" + nomeArquivo;
            URL imagemURL = getClass().getResource(caminhoImagem);

            if (imagemURL != null) {
                backgroundImage = ImageIO.read(imagemURL);
            } else {
                throw new IOException("Imagem não encontrada no caminho: " + caminhoImagem);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Cria o painel de fundo com a imagem
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(new BorderLayout());

        // Adiciona o título
        titleLabel = new JLabel("RPG Revolução Francesa", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(false);

        // Permite mover a janela arrastando o título
        titleLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });

        titleLabel.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int thisX = getLocation().x;
                int thisY = getLocation().y;

                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                setLocation(X, Y);
            }
        });

        backgroundPanel.add(titleLabel, BorderLayout.NORTH);

        textPanel = new TextPanel("Teste");
        textPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        backgroundPanel.add(textPanel, BorderLayout.CENTER);

        // Painel para os botões de opções
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout());

        // Adiciona botões de exemplo
        /*int numeroDeBotoes = 3; 
        for (int i = 0; i < numeroDeBotoes; i++) {
            JButton button = new JButton("Opção " + (i + 1));
            buttonPanel.add(button);
        }
        JButton sair = new JButton("Sair");
        buttonPanel.add(sair);
        sair.addActionListener((e) -> {
            dispose();
        });
         */
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adiciona o painel de fundo ao JFrame
        setContentPane(backgroundPanel);

        // Define o tamanho do JFrame de acordo com a imagem
        setSize(backgroundImage.getWidth(), backgroundImage.getHeight());

        // Configura o JFrame para centralizar
        setLocationRelativeTo(null);

        // Toca a música se for fornecida
        if (musicaPath != null) {
            tocarMusica(musicaPath);
        }
    }

    private void tocarMusica(String caminhoMusica) {
        new Thread(() -> {
            try (InputStream musicaStream = getClass().getResourceAsStream("/musicas/" + caminhoMusica)) {
                if (musicaStream != null) {
                    musicaPlayer = new Player(musicaStream);
                    musicaPlayer.play();
                } else {
                    System.err.println("Arquivo de música não encontrado: " + caminhoMusica);
                }
            } catch (JavaLayerException | IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    @Override
    public void dispose() {
        if (musicaPlayer != null) {
            musicaPlayer.close();
        }
        super.dispose();
    }

    // Painel personalizado para desenhar o fundo com a imagem
    private class BackgroundPanel extends JPanel {

        private BufferedImage image;

        public BackgroundPanel(BufferedImage image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, this);
            }
        }
    }

    public static void main(String[] args) {
        new FundoFrame("fundo.png", "ocllo.mp3").setVisible(true);

    }
}
