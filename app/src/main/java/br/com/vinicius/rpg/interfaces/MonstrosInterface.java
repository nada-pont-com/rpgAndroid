package br.com.vinicius.rpg.interfaces;

import br.com.vinicius.rpg.dados.ItensDados;

public interface MonstrosInterface {
    double getMod();
    String getNome();
    int getExp();
    String[] getItemQuant();
    ItensDados[] getItens();
    int getvida();
    int getMp();
    int getAtk();
    int getDef();
    int getAgi();
    int getAtkM();
    int getDefM();
    int getLevel();
    String getRank();
}
