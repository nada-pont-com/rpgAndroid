package br.com.vinicius.rpg.dados;

import br.com.vinicius.rpg.interfaces.MonstrosInterface;

public enum Monstros implements MonstrosInterface {
    SLIME{
        public double getMod() {
            return 1;
        }
        public String getNome(){
            return "Slime";
        }
        public int getExp() {
            return 50;
        }
        public String[] getItemQuant(){
            String[] itemQuant = {"1-3", "0-1", "0-1"};
            return itemQuant;
        }
        public ItensDados[] getItens(){
            ItensDados[] itens = {ItensDados.GOSMA, ItensDados.POCAO_HP_P, ItensDados.POCAO_MP_P};
            return itens;
        }
        public int getvida() {
            return 25;
        }
        public int getMp() {
            return 5;
        }
        public int getAtk() {
            return 2;
        }
        public int getDef() {
            return 9;
        }
        public int getAgi() {
            return 1;
        }
        public int getAtkM() {
            return 0;
        }
        public int getDefM() {
            return 1;
        }
        public int getLevel(){ return 1; }
        public String getRank(){ return "G";}
    },
    ESQUELETO{
        public double getMod() {
            return 1;
        }
        public String getNome(){
            return "Esqueleto";
        }
        public int getExp() {
            return 100;
        }

        public String[] getItemQuant() {
            return itemQuant;
        }

        public ItensDados[] getItens() {
            return itens;
        }

        //             item = id_minimo-maximo -----------------------------------------
        private String[] itemQuant = {"1-3","0-1","0-1"};
        private ItensDados[] itens = {ItensDados.OSSO,ItensDados.POCAO_HP_P,ItensDados.POCAO_MP_P};

        public int getvida() {
            return 75;
        }
        public int getMp() {
            return 5;
        }
        public int getAtk() {
            return 10;
        }
        public int getDef() {
            return 4;
        }
        public int getAgi() {
            return 5;
        }
        public int getAtkM() {
            return 0;
        }
        public int getDefM() {
            return 0;
        }
        public int getLevel(){ return 3;}
        public String getRank(){ return "G";}

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
        public String[] getItemQuant(){
            return itemQuant;
        }

        public ItensDados[] getItens() {
            return itens;
        }

        private String[] itemQuant = {"1-2","0-1","0-1"};
        private ItensDados[] itens = {ItensDados.CARNE_COMUN,ItensDados.POCAO_HP_P,ItensDados.POCAO_MP_P};

        public int getvida() {
            return 100;
        }
        public int getMp() {
            return 15;
        }
        public int getAtk() {
            return 15;
        }
        public int getDef() {
            return 7;
        }
        public int getAgi() {
            return 20;
        }
        public int getAtkM() {
            return 5;
        }
        public int getDefM() {
            return 7;
        }
        public int getLevel(){ return 5;}
        public String getRank(){ return "G";}
    },
    GOLEM_PEDRA{
        public double getMod() {
            return 1.2;
        }
        public String getNome(){
            return "Golem de Pedra";
        }
        public int getExp() {
            return 12;
        }
        public String[] getItemQuant(){
            return itemQuant;
        }
        public ItensDados[] getItens() {
            return itens;
        }

        private String[] itemQuant = {"0-2","1-2","1-1"};
        private ItensDados[] itens = {ItensDados.FERRO,ItensDados.POCAO_HP_P,ItensDados.POCAO_MP_P};

        public int getvida() {
            return 175;
        }
        public int getMp() {
            return 7;
        }
        public int getAtk() {
            return 10;
        }
        public int getDef() {
            return 24;
        }
        public int getAgi() {
            return 5;
        }
        public int getAtkM() {
            return 0;
        }
        public int getDefM() {
            return 0;
        }
        public int getLevel(){ return 10;}
        public String getRank(){ return "E";}
    }
}
