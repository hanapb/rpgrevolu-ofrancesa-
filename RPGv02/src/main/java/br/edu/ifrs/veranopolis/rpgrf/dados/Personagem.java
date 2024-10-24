package br.edu.ifrs.veranopolis.rpgrf.dados;

public class Personagem extends Pessoa {

    private String motivacao, alianca;
    private int favorPopular;

    public Personagem(String nome) {
        super(nome);
    }

    public String getMotivacao() {
        return motivacao;
    }

    public void setMotivacao(String motivacao) {
        this.motivacao = motivacao;
    }

    public String getAlianca() {
        return alianca;
    }

    public void setAlianca(String alianca) {
        this.alianca = alianca;
    }

    public int getFavorPopular() {
        return favorPopular;
    }

    public void setFavorPopular(int favorPopular) {
        this.favorPopular = favorPopular;
    }

}
