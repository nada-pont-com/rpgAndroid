package br.com.vinicius.rpg.objetosTabelas;

public class LoadTable {
    private int id;
    private String nome;
    private String tempo;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void setTempo(String tempo){
        this.tempo = tempo;
    }
    public String getTempo(){
        return this.tempo;
    }

    @Override
    public String toString() {
        return "Id: "+id+",Nome: " +nome + ",Tempo: " + tempo;
    }

}
