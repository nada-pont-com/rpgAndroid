package br.com.vinicius.rpg.jogo.monstros;

import br.com.vinicius.rpg.jogo.interfaces.MostrosRankInterface;

public enum MonstrosRG implements MostrosRankInterface {
    SLIME{
        public double getMod() {
            return 1.2;
        }
        public String getNome(){
            return "Slime";
        }
        public int getExp() {
            return 50;
        }
        public String[] getItem(){
                return item;
        }
        //             item = id_minimo-maximo -----------------------------------------
        private String[] item = {"id:1_1-3","id:3_0-1","id:4_0-1"};
    },
    ESQUELETO{
        public double getMod() {
            return 1.5;
        }
        public String getNome(){
            return "Esqueleto";
        }
        public int getExp() {
            return 100;
        }
        public String[] getItem(){
            return item;
        }
        //             item = id_minimo-maximo -----------------------------------------
        private String[] item = {"id:2_1-3","id:3_0-1","id:4_0-1"};
    },
    LOBO_G{
        public double getMod() {
            return 1.6;
        }
        public String getNome(){
            return "Lobo de baixo rank";
        }
        public int getExp() {
            return 12;
        }
        public String[] getItem(){
            return item;
        }
        //             item = id_minimo-maximo -----------------------------------------
        private String[] item = {"id:6_1-2","id:3_0-1","id:4_0-1"};
    }
}
