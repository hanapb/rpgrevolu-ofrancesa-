/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.veranopolis.rpgrf.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TextPanel extends JPanel {

    private String text;
    private List<JLabel> letters = new ArrayList<>();
    private float[] alphas;
    private Timer timer;
    private int currentLetterIndex = 0;
    private Font customFont;

    public TextPanel(String text) {
        this.text = text;
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        loadCustomFontFromResources("/fontes/PixelifySans-Regular.ttf");
        initText();
        setOpaque(false);
    }

    private void loadCustomFontFromResources(String fontPath) {
        try {
            InputStream fontStream = getClass().getResourceAsStream(fontPath);
            if (fontStream != null) {
                customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(24f);
            } else {
                System.err.println("Não foi possível carregar a fonte do caminho: " + fontPath);
                customFont = new Font("Serif", Font.BOLD, 24);
            }
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            customFont = new Font("Serif", Font.BOLD, 24);
        }
    }

    private void initText() {
        for (char c : text.toCharArray()) {
            JLabel label = new JLabel(String.valueOf(c)) {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g;
                    int index = letters.indexOf(this);
                    float alpha = alphas[index];
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                    setOpaque(false);
                    super.paintComponent(g);
                }
            };

            label.setFont(customFont);
            label.setForeground(Color.BLACK);

            //label.setBorder(new EmptyBorder(-1, 5, -1, 5));
            this.add(label);
            letters.add(label);
        }

        alphas = new float[letters.size()];
        for (int i = 0; i < alphas.length; i++) {
            alphas[i] = 0f;
        }
    }

    public void setText(String text) {
        this.text = text;
        this.removeAll();
        letters.clear();
        initText();
        revalidate();
        repaint();
    }

     public void startAnimation() {
        currentLetterIndex = 0;
        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Incrementar alpha para todas as letras que estão em transição
                for (int i = 0; i < currentLetterIndex; i++) {
                    if (alphas[i] < 1f) {
                        alphas[i] += 0.05f;  // Pequenos incrementos para suavidade
                        if (alphas[i] > 1f) {
                            alphas[i] = 1f;  // Garantir que não ultrapasse 1
                        }
                        letters.get(i).repaint();
                    }
                }

                // Iniciar o fade-in da próxima letra se não passamos do último caractere
                if (currentLetterIndex < letters.size()) {
                    alphas[currentLetterIndex] = 0f;  // Iniciar invisível
                    currentLetterIndex++;
                } else {
                    // Quando todas as letras tiverem começado a aparecer, paramos o timer
                    if (isAllTextShown()) {
                        timer.stop();
                    }
                }
            }
        });
        timer.start();
    }

    private boolean isAllTextShown() {
        for (float alpha : alphas) {
            if (alpha < 1f) {
                return false;
            }
        }
        return true;
    }

    public void showAllText() {
        if (timer != null && timer.isRunning()) {
            timer.stop(); // Parar a animação
        }
        for (int i = 0; i < alphas.length; i++) {
            alphas[i] = 1f;  // Tornar todas as letras visíveis
            letters.get(i).repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("RPG Text Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 200);

        TextPanel textPanel = new TextPanel("Bem-vindo ao RPG! Prepare-se para uma aventura épica.");

        JButton startButton = new JButton("Iniciar Animação");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textPanel.startAnimation();
            }
        });

        JButton skipButton = new JButton("Pular Animação");
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textPanel.showAllText();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(skipButton);

        frame.add(textPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
