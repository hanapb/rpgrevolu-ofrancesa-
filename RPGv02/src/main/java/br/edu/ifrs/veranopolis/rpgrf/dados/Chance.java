/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.veranopolis.rpgrf.dados;

/**
 *
 * @author ruan.poletto
 */
public abstract class Chance {

    private static final double COMPARATIVO = 9.0;
    
    public static boolean sucess(String[] atributos, Personagem p) {

        boolean b = true;
        double sorte = Math.random();

        for (String atr : atributos) {
            if (atr.equals("Astucia")) {
                b = p.getAstucia() / COMPARATIVO <= sorte;
            }
            if (atr.equals("Influencia")) {
                b = p.getInfluencia() / COMPARATIVO <= sorte;
            }
            if (atr.equals("Oratoria")) {
                b = p.getOratoria() / COMPARATIVO <= sorte;
            }
            if (atr.equals("Combate")) {
                b = p.getCombate() / COMPARATIVO <= sorte;
            }
            if (atr.equals("Lealdade")) {
                b = p.getLealdade() / COMPARATIVO <= sorte;
            }
        }
        return b;
    }
}
