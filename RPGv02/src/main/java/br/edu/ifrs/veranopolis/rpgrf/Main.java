/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.edu.ifrs.veranopolis.rpgrf;

public class Main {

public static void main(String[] args) {
        try {
            GameMaster gameMaster = new GameMaster();
            gameMaster.carregarEventos();  // Carregar os eventos da pasta scenes
            gameMaster.exibirMenuPrincipal();  // Exibir o menu e iniciar o jogo
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
