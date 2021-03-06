package br.com.vinicius.rpg.dados;

import java.util.Random;
import br.com.vinicius.rpg.jogo.informacoes.Sessao;
import br.com.vinicius.rpg.objetosTabelas.DungeonTable;
import br.com.vinicius.rpg.objetosTabelas.ItensTable;
import br.com.vinicius.rpg.objetosTabelas.MissoesTable;
import br.com.vinicius.rpg.objetosTabelas.MonstroUni;

public class Missao {
    private Random random = new Random();
    public MissoesTable geraMissao(){
        int tipo = random.nextInt(3)+1;
        int quant = -1;
        MissoesTable missao = new MissoesTable();
        String[] rankMissao = {"G","F","E","D","C","B","A","S"};
        String rank;
        boolean vali;
        do {
            int rankRand = random.nextInt(rankMissao.length);
            rank = rankMissao[rankRand];
            vali = Sessao.getDadosPerso().get(0).validadorRank(rank);
        }while (!vali);
        System.out.println("test tipo"+tipo);
        switch (tipo){
            case 1: // encontrar Item (drop de monstro)
                do {
                    vali = true;
                    int valor = random.nextInt(ItensDados.values().length);
                    ItensTable item = new ItensTable(ItensDados.values()[valor]);

                    if(item.getRaridade().equals(rank)){
                        quant = random.nextInt(10)+1;
                        missao.setItem(item);
                        vali = false;
                    }
                }while(vali);
                break;
            case 2:
                int valor;
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
                        monstro = new MonstroUni(0,100,0,"G");
                        break;
                }
                missao.setMonstro(monstro);
                break;
            case 3:
                Dungeons dungeonDados = new Dungeons();
                String[] dungeonNomes = dungeonDados.getNome(rank);
                DungeonTable dungeonTable = new DungeonTable();
                dungeonTable.setRank(rank);
                valor = random.nextInt(dungeonNomes.length);
                dungeonTable.setNome(dungeonNomes[valor]);
                missao.setDungeon(dungeonTable);
                break;
        }
        missao.setTipo(tipo);
        missao.setQuant(quant);
        missao.setDificuldadeERank();
        System.out.println("validando");
        return missao;
    }
}
/*
missoes - tipo - encontrar, matar, completar dungeon
        -         "itens","monst", "o que o nome diz"
        - quant- nun     ,  nun  , NAN
        - exp  - a*      ,  b*   , c*
        - dif  - a*      ,  b*	 , c*
        - rank - a1*     ,  b1*  , c1*

    a = "quant*raridade"
	b = "quant*dificuldade"
	c = "andares*(RankMonsters*nivelsMonsters)"
	a1= "rank do Lugar que se encontra"
	b1= "rank do munstro/lugar"
	c1= "rank da dungeon"

*/