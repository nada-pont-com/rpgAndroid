package br.com.vinicius.rpg.jogo.monstros;

import java.util.Random;

import br.com.vinicius.rpg.objetosTabelas.MonstroUni;
import br.com.vinicius.rpg.dados.MonstrosDados;
import br.com.vinicius.rpg.jogo.interfaces.MonstrosInterface;
import br.com.vinicius.rpg.jogo.interfaces.MostrosInterface2;

public enum Monstros implements MonstrosInterface {
    S{
        @Override
        public MonstroUni monstro(int andar, int andarMax,int tipo) {
            return null;
        }
    },
    A{
        @Override
        public MonstroUni monstro(int andar,int andarMax,int tipo) {
            return null;
        }
    },
    B{
        @Override
        public MonstroUni monstro(int andar,int andarMax,int tipo) {
            return null;
        }
    },
    C{
        @Override
        public MonstroUni monstro(int andar,int andarMax,int tipo) {
            return null;
        }
    },
    D{
        @Override
        public MonstroUni monstro(int andar,int andarMax,int tipo) {
            return null;
        }
    },
    E{
        @Override
        public MonstroUni monstro(int andar,int andarMax,int tipo) {
            return null;
        }
    },
    F{
        @Override
        public MonstroUni monstro(int andar,int andarMax,int tipo) {
            return null;
        }
    },
    G{
        private Random random = new Random();
        public MonstroUni monstro(int andar,int andarMax,int tipo){
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
            status = (int) (status*mod);
            if (status==0){
                status = 1;
            }
            status = (int) (status*mod2);
            double mod3 = 1;
            switch (tipo){
                case 2: // alfa
                    mod3 = 2;
                    break;
                case 3: // boss
                    mod3 = random.nextInt(4)+4;// 4x a 7x
                    double valorMin = 1;
                    double valorMax = 3.5;
                    double valorF = valorMax-valorMin;
                    valor = valorF;
                    valor = valor/andarMax;
                    valor = valor*andar;
                    valorInt = (int) valor;
                    if (valorInt==0){
                        valorF = valorMax;
                    }else{
                        valorF = valorF/valor;
                    }
                    mod3 = mod3/valorF;
                    break;
            }
            status = (int)(mod3*status);
            int ex = G.getExp()*level;
            return new MonstroUni(rank,vida,status,level,G.getNome(),mp,ex,G.getItem());
        }
        RankG[] g = RankG.values();
    }
}
