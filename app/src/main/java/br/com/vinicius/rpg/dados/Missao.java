package br.com.vinicius.rpg.dados;

import java.util.Random;

import br.com.vinicius.rpg.jogo.informacoes.Sessao;
import br.com.vinicius.rpg.objetosTabelas.ItensTable;

public class Missao {
    private Random random = new Random();
    public void geraMissao(){
        int tipo = random.nextInt(3)+1;
        switch (tipo){
            case 1: // encontrar Item (drop de monstro)
                Itens itens  = new Itens();
                int vali = 0;
                do {
                    vali = 0;
                    int valor = random.nextInt(itens.getListaDeItens().size());
                    ItensTable item = itens.getListaDeItens().get(valor);
                    if(Sessao.getDadosPerso().get(0).validadorRank(item.getRaridade())){

                    }
                }while(vali == 1);
                break;
            case 2:
                break;
            case 3:
                break;
        }
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