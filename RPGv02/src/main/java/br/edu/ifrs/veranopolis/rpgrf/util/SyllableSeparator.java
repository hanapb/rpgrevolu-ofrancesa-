/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.veranopolis.rpgrf.util;

import ufpa.falabrasil.Syllabificator;


public class SyllableSeparator {
    private static Syllabificator syllabificator = new Syllabificator();

    public static String separateSyllables(String word) {
        return syllabificator.syllabs(word); // Método interno para separação
    }

    public static void main(String[] args) {
        String word = "programação";
        String result = separateSyllables(word);
        System.out.println("Separação silábica: " + result);
    }
}