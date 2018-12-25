package br.com.vinicius.rpg;

import java.util.Random;

public enum Monstros implements MonstrosInterface{
    S{
        @Override
        public MonstroUni monstro(int andar,int andarMax) {
            return null;
        }
    },
    A{
        @Override
        public MonstroUni monstro(int andar,int andarMax) {
            return null;
        }
    },
    B{
        @Override
        public MonstroUni monstro(int andar,int andarMax) {
            return null;
        }
    },
    C{
        @Override
        public MonstroUni monstro(int andar,int andarMax) {
            return null;
        }
    },
    D{
        @Override
        public MonstroUni monstro(int andar,int andarMax) {
            return null;
        }
    },
    E{
        @Override
        public MonstroUni monstro(int andar,int andarMax) {
            return null;
        }
    },
    F{
        @Override
        public MonstroUni monstro(int andar,int andarMax) {
            return null;
        }
    },
    G{
        private Random random = new Random();
        public MonstroUni monstro(int andar,int andarMax){
            MonstrosDados monstroDados = new MonstrosDados();
            String rank = monstroDados.getRank()[7];
            int vida = Integer.parseInt(monstroDados.getVida()[7]);
            String variLevel = monstroDados.getLevel()[7];
            String[] levelMinMax = variLevel.split("-");
            int levelMax = Integer.parseInt(levelMinMax[0]);
            int levelMin = Integer.parseInt(levelMinMax[1]);
            double valor = levelMax-levelMin;
            valor = valor/andarMax;
            valor = valor*andar;
            System.out.println(valor);
            int valorInt = (int) valor;
            System.out.println(valorInt);
            int level;
            if(valorInt==0){
                level=levelMin;
            }else{
                level = random.nextInt(valorInt);
                level+=levelMin;
            }
            vida = vida + vida * (level-1) / 10;
            int status = monstroDados.getStatus()[7];
            int mp = monstroDados.getMp()[7];
            int teste = random.nextInt(g.length);
            double mod = monstroDados.mod();
            RankG G = g[teste];
            double mod2 = G.getMod();
            status = (int) (status*mod2);
            status = (int) (status*mod);
            if (status==0){
                status = 1;
            }
            int ex = G.getExp()*level;
            return new MonstroUni(rank,vida,status,level,G.getNome(),mp,ex);
        }
        RankG[] g = RankG.values();
    }
}
enum RankG implements MostrosInterface2{
    SLIME{
        @Override
        public double getMod() {
            return modMoster;
        }
        @Override
        public String getNome(){
            return nome;
        }
        public int getExp() {
            return exp;
        }
        private double modMoster = 1.2;
        private int exp = 50;
        private String nome = "Slime";
    },
    ESQUELETO{
        @Override
        public double getMod() {
            return modMoster;
        }
        @Override
        public String getNome(){
            return nome;
        }
        public int getExp() {
            return exp;
        }
        private double modMoster = 1.5;
        private int exp = 100;
        private String nome = "Esqueleto";
    }
}
