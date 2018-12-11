package br.com.vinicius.rpg;

import java.util.Random;

public enum Monstros implements MonstrosInterface{
    S{
        @Override
        public MonstroUni monstro() {
            return null;
        }
    },
    A{
        @Override
        public MonstroUni monstro() {
            return null;
        }
    },
    B{
        @Override
        public MonstroUni monstro() {
            return null;
        }
    },
    C{
        @Override
        public MonstroUni monstro() {
            return null;
        }
    },
    D{
        @Override
        public MonstroUni monstro() {
            return null;
        }
    },
    E{
        @Override
        public MonstroUni monstro() {
            return null;
        }
    },
    F{
        @Override
        public MonstroUni monstro() {
            return null;
        }
    },
    G{
        private Random random = new Random();
        public MonstroUni monstro(){
            MonstrosDados monstroDados = new MonstrosDados();
            String rank = monstroDados.getRank()[7];
            int vida = Integer.parseInt(monstroDados.getVida()[7]);
            String variLevel = monstroDados.getLevel()[7];
            String[] levelMinMax = variLevel.split("-");
            int levelMax = Integer.parseInt(levelMinMax[0]);
            int levelMin = Integer.parseInt(levelMinMax[1]);
            int level = random.nextInt(levelMax-levelMin+1)+levelMin;
            int status = monstroDados.getStatus()[7];
            int teste = random.nextInt(2);
            double mod = monstroDados.mod();
            RankG G = g[teste];
            double mod2 = G.getMod();
            status = (int) (status*mod2);
            status = (int) (status*mod);
            MonstroUni monstro = new MonstroUni(rank,vida,status,level,G.getNome());
            //monstro.setNome(G.getNome());
            //monstro.setRank(rank);
            //monstro.setStatus(status);
            //monstro.setVida(vida);
            //monstro.setLevel(level);
            return monstro;
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
        private double modMoster = 1.2;
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
        private double modMoster = 1.7;
        private String nome = "Esqueleto";
    }

}
