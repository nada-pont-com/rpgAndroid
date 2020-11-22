package br.com.vinicius.rpg.interfaces;

import br.com.vinicius.rpg.objetosTabelas.PersoTable;

public interface ClassesInterface {
    String Classe = "";
    String getClasse();
    PersoTable getStatus();
    int Atk = 0;
    int Def = 0;
    int Agi = 0;
    int AtkM = 0;
    int DefM = 0;
    int Vit = 0;
    int Int = 0;
    int getId();
}
