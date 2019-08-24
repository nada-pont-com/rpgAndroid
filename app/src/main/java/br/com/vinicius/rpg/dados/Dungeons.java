package br.com.vinicius.rpg.dados;

import java.util.ArrayList;
import java.util.List;

import br.com.vinicius.rpg.jogo.informacoes.Sessao;
import br.com.vinicius.rpg.objetosTabelas.DungeonTable;

public class Dungeons {
    private String[] nome = {"Floresta Norte"};
    private String[] andares = {"1-25"};
    private String[] rank = {"G"};
    public List<DungeonTable> listaDeDungeons;

    public void list(){
        listaDeDungeons = new ArrayList<>();
        DungeonTable dungeon;
        for (int i =0;i<nome.length;i++){
            if(Sessao.getDadosPerso().get(0).validadorRank(rank[i])){
                dungeon = new DungeonTable();
                dungeon.setNome(nome[i]);
                dungeon.setAndares(andares[i]);
                dungeon.setRank(rank[i]);
            listaDeDungeons.add(dungeon);
            }
        }
    }
}

