package br.com.vinicius.rpg.dados;

import java.io.Serializable;
import java.util.Random;

public class MonstrosDados {
    private Random random = new Random();
    //                          0           1          2          3         4        5       6       7
    private String[] rank   = {"S",        "A",       "B",       "C",      "D",     "E",    "F",    "G"};
    private String[] vida   = {"990000000","90000000","10000000","2000000","710000","22000","1500", "100"};
    private int[]    status = {100000,      45000,     17500,     7500,     2340,    500,    89,     7};
    private String[] level  = {"9999-5000","4999-900","899-400", "399-100","99-50", "49-25","24-11","10-1"};
    private int[]    mp     = {700000,      150000,    75000,     25000,    10000,   1000,   100,    10};

    public double mod(){
        double mod  = random.nextDouble()+random.nextInt(1);//{1.5,0.5,1,2};
        System.out.println("ola mundo: "+mod);
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

    public int[] getMp() {
        return mp;
    }
}