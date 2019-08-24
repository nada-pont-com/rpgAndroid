package br.com.vinicius.rpg.dados;

import java.io.Serializable;

public class MonstroUni implements Serializable {
    private String rank;
    private int vida;
    private int status;
    private int level;
    private String nome;
    private int mp;
    private int ex;
    private String[] item;


    public MonstroUni(String rank, int vida, int status, int level, String nome, int mp, int ex, String[] item){
        this.rank = rank;
        this.vida = vida;
        this.status = status;
        this.level = level;
        this.nome = nome;
        this.mp = mp;
        this.ex = ex;
        this.item = item;// (randon(max-min)) + min
    }

   /* void setRank(String rank) {
        this.rank = rank;
    }*/

    public String getRank() {
        return rank;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVida() {
        return vida;
    }

    /*void setStatus(int status) {
        this.status = status;
    }*/

    public int getStatus() {
        return status;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMp() {
        return mp;
    }

    public int getEx() {
        return ex;
    }

    public String[] getItem() {
        return item;
    }
}
