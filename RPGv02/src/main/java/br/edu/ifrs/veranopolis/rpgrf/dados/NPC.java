/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.veranopolis.rpgrf.dados;

public class NPC extends Pessoa{
    private String papel;
    private String alianca;

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public String getAlianca() {
        return alianca;
    }

    public void setAlianca(String alianca) {
        this.alianca = alianca;
    }
    
    public NPC(String nome){
        super(nome);
    }
}

