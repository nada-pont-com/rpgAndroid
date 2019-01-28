package br.com.vinicius.rpg;

import java.util.ArrayList;
import java.util.List;

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
class DungeonTable{
    private String nome;
    private String andares;
    private String rank;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAndares() {
        return andares;
    }

    public void setAndares(String andares) {
        this.andares = andares;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
