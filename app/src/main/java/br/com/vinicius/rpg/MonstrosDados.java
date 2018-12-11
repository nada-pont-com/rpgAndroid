package br.com.vinicius.rpg;

import java.io.Serializable;
import java.util.Random;

public class MonstrosDados {
    private Random random = new Random();
    //                          0           1          2          3         4        5       6       7
    private String[] rank   = {"S",        "A",       "B",       "C",      "D",     "E",    "F",    "G"};
    private String[] vida   = {"990000000","90000000","10000000","2000000","750000","25000","2000", "100"};
    private int[]    status = {100000,      45000,     17500,     7500,     2340,    500,    89,     7};
    private String[] level  = {"9999-5000","4999-900","899-400", "399-100", "99-50","49-25","24-11","10-1"};

    double mod(){
        double mod  = random.nextDouble()+random.nextInt(2);//{1.5,0.5,1,2};
        if(mod==0){
            return mod();
        }else{
            return mod;
        }
    }

    public String[] getRank() {
        return rank;
    }

    public String[] getVida() {
        return vida;
    }

    public int[] getStatus() {
        return status;
    }

    public String[] getLevel() {
        return level;
    }
}

class MonstroUni implements Serializable {
    private String rank;
    private int vida;
    private int status;
    private int level;
    private String nome;


    public MonstroUni(String rank,int vida,int status,int level,String nome){
        this.rank = rank;
        this.vida = vida;
        this.status = status;
        this.level = level;
        this.nome = nome;
    }

    void setRank(String rank) {
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVida() {
        return vida;
    }

    void setStatus(int status) {
        this.status = status;
    }

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
}
