package br.com.vinicius.rpg.jogo.monstros;

import br.com.vinicius.rpg.jogo.interfaces.MostrosRankInterface;

public enum MonstrosRG implements MostrosRankInterface {
    SLIME{
        public double getMod() {
            return modMoster;
        }
        public String getNome(){
            return nome;
        }
        public int getExp() {
            return exp;
        }
        public String[] getItem(){
                return item;
        }
        private double modMoster = 1.2;
        private int exp = 50;
        private String nome = "Slime";
        //             item = nome-minimo-maximo -----------------------------------------
        private String[] item = {"Gosma-1-3","pocaoHP-0-1","pocaoMP-0-1"};
    },
    ESQUELETO{
        public double getMod() {
            return modMoster;
        }
        public String getNome(){
            return nome;
        }
        public int getExp() {
            return exp;
        }
        public String[] getItem(){
            return item;
        }
        private double modMoster = 1.5;
        private int exp = 100;
        private String nome = "Esqueleto";
        //             item = nome-minimo-maximo -----------------------------------------
        private String[] item = {"Osso-1-3","pocaoHP-0-1","pocaoMP-0-1"};
    },
    LOBO_G{
        public double getMod() {
            return modMoster;
        }
        public String getNome(){
            return nome;
        }
        public int getExp() {
            return exp;
        }
        public String[] getItem(){
            return item;
        }
        private double modMoster = 1.6;
        private int exp = 12;
        private String nome = "Lobo de baixo rank";
        //             item = nome-minimo-maximo -----------------------------------------
        private String[] item = {"carne_C-1-2","pocaoHP-0-1","pocaoMP-0-1"};
    }
}
