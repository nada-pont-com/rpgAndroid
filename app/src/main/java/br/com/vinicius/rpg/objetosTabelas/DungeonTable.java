package br.com.vinicius.rpg.objetosTabelas;

import android.support.annotation.NonNull;

public class DungeonTable {
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

    @Override
    public String toString() {
        return "DungeonTable{" +
                "nome='" + nome + '\'' +
                ", andares='" + andares + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }
}
