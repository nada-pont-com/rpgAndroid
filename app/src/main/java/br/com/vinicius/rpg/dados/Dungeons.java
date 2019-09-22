package br.com.vinicius.rpg.dados;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.vinicius.rpg.jogo.informacoes.Sessao;
import br.com.vinicius.rpg.objetosTabelas.DungeonTable;

public class Dungeons { //TODO nomes novos de acordo com o rank; limitar monstros aos nomes; até 3 para o rank G, aumentar de acordo com o rank;
    private String[] nome = {"Floresta Norte","Scarlet","cubias","Dragonideos","Planice"};
    private String[] nomeRF = {};
    private String[] andares = {"25","30","40","45","55","60","80","70","90","100"};
    private String[] rank = {"G","F","E","D","C","B","A","S"};
    private List<DungeonTable> listaDeDungeons = new ArrayList<>();



    public List<DungeonTable> getListaDeDungeons() {
        return listaDeDungeons;
    }

    public void list(){
        Random random = new Random();
        int andar = random.nextInt(andares.length);
        int name = random.nextInt(nome.length);
        int vali;
        do{
            int randRank = random.nextInt(rank.length);
            vali = 0;
            if(Sessao.getDadosPerso().get(0).validadorRank(rank[randRank])) {
                DungeonTable dungeon;
                    dungeon = new DungeonTable();
                    dungeon.setNome(nome[name]);
                    dungeon.setAndares("1-" + andares[andar]);
                    dungeon.setRank(rank[randRank]);
                    listaDeDungeons.add(dungeon);
            }else{
                vali = 1;
            }
        }while (vali==1);
    }
}

