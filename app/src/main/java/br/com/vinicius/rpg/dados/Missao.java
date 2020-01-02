package br.com.vinicius.rpg.dados;

import java.util.Random;
import br.com.vinicius.rpg.jogo.informacoes.Sessao;
import br.com.vinicius.rpg.jogo.monstros.RankG;
import br.com.vinicius.rpg.objetosTabelas.ItensTable;
import br.com.vinicius.rpg.objetosTabelas.MissoesTable;
import br.com.vinicius.rpg.objetosTabelas.MonstroUni;

public class Missao {
    private Random random = new Random();
    public void geraMissao(){
        int tipo = random.nextInt(3)+1;
        int quant = -1;
        MissoesTable missao = new MissoesTable();
        switch (tipo){
            case 1: // encontrar Item (drop de monstro)
                Itens itens  = new Itens();
                int vali;
                do {
                    vali = 0;
                    int valor = random.nextInt(itens.getListaDeItens().size());
                    ItensTable item = itens.getListaDeItens().get(valor);
                    if(Sessao.getDadosPerso().get(0).validadorRank(item.getRaridade())){
                        quant = random.nextInt(10)+1;
                        missao.setItem(item);
                    }else{
                        vali = 1;
                    }
                }while(vali == 1);
                break;
            case 2:
                MonstrosDados monstrosDados = new MonstrosDados();
                do {
                    vali = 0;
                    int valor = monstrosDados.getRank().length;
                    String rank = monstrosDados.getRank()[random.nextInt(valor)];
                    if(Sessao.getDadosPerso().get(0).validadorRank(rank)){
                        quant = random.nextInt(10)+1;
                        MonstroUni monstro = null;
                        switch (rank){
                            case "S":
                                break;
                            case "A":
                                break;
                            case "B":
                                break;
                            case "C":
                                break;
                            case "D":
                                break;
                            case "E":
                                break;
                            case "F":
                                break;
                            case "G":
                                valor = RankG.values().length;
                                RankG g = RankG.values()[valor];
                                monstro = new MonstroUni(rank,0,0,0,g.getNome(),0,0,g.getItem());
                                break;
                        }
                        missao.setMonstro(monstro);
                    }else{
                        vali = 1;
                    }
                }while(vali == 1);
                break;
            case 3:
                Dungeons dungeonDados = new Dungeons();
                dungeonDados.list(null,true);
                break;
        }
        missao.setTipo(tipo);
        missao.setQuant(quant);
        missao.setDificuldadeERank();
    }
}
/*
missoes - tipo - encontrar, matar, completar dungeon
        -         "itens","monst", "o que o nome diz"
        - quant- nun-NAN  , nun  , NAN
        - exp  - a*       , b*   , c*
        - dif  - a*       , b*	 , c*
        - rank - a1*	  , b1*  , c1*

    a = "quant*raridade"
	b = "quant*dificuldade"
	c = "andares*(RankMonsters*nivelsMonsters)"
	a1= "rank do Lugar que se encontra"
	b1= "rank do munstro/lugar"
	c1= "rank da dungeon"

*/