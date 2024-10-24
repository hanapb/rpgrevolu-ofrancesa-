package br.edu.ifrs.veranopolis.rpgrf.dados;

public abstract class Pessoa {
    String nome;
    private int astucia, influencia, combate, oratoria, lealdade;
    
    public static final String[]motivacoes = {"Revolucionário", "Moderado", "Monarquista", "Sobrevivente"};
    public static final String[]aliancas = {"Jacobino", "Girondino", "Realista", "Povo"};

    public Pessoa(String nome) {
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }

    public int getAstucia() {
        return astucia;
    }

    public void setAstucia(int astucia) {
        this.astucia = astucia;
    }

    public int getInfluencia() {
        return influencia;
    }

    public void setInfluencia(int influencia) {
        this.influencia = influencia;
    }

    public int getCombate() {
        return combate;
    }

    public void setCombate(int combate) {
        this.combate = combate;
    }

    public int getOratoria() {
        return oratoria;
    }

    public void setOratoria(int oratoria) {
        this.oratoria = oratoria;
    }

    public int getLealdade() {
        return lealdade;
    }

    public void setLealdade(int lealdade) {
        this.lealdade = lealdade;
    }
    
    
}
