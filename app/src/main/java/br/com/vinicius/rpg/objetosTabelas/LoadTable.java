package br.com.vinicius.rpg.objetosTabelas;

public class LoadTable {
    private int id;
    private String nome;
    private String tempo;
    private int cobre;
    private int prata;
    private int ouro;


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

    public void addMoeda(int cobre,int prata, int ouro){
        this.cobre += cobre;
        if(this.cobre>1000){
            this.prata +=1;
            this.cobre-=1000;
        }
        this.prata += prata;
        if(this.prata>100){
            this.ouro +=1;
            this.prata-=100;
        }
        this.ouro += ouro;
    }

    public void setMoeda(int cobre,int prata, int ouro) {
        this.cobre = cobre;
        this.prata = prata;
        this.ouro = ouro;
        addMoeda(0,0,0);
    }

    public int getCobre() {
        return cobre;
    }

    public int getOuro() {
        return ouro;
    }

    public int getPrata() {
        return prata;
    }

    @Override
    public String toString() {
        return "Id: "+id+",Nome: " +nome + ",Tempo: " + tempo;
    }

}
