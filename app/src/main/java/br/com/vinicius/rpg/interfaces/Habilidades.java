package br.com.vinicius.rpg.interfaces;

import br.com.vinicius.rpg.objetosTabelas.Status;

public interface Habilidades {
    int getId();
    String getNome();
    int getTipo();
    int getValor();
    int getNumberAtk();
    int getNocalte();
    Status getExtra();
    int getCusto();
    int getMpCusto();
    String getDescricao();
    int getAumento();
}
