package br.com.vinicius.rpg.dados;

import br.com.vinicius.rpg.interfaces.ClassesInterface;
import br.com.vinicius.rpg.objetosTabelas.PersoTable;

public enum Classes implements ClassesInterface {
    GUERREIRO{
        public String getClasse() {
            return Classe;
        }

        private String Classe = "Guerreiro";

        private int Atk = 10;

        private int Def = 10;

        private int Agi = 5;

        private int AtkM = 2;

        private int DefM = 2;

        private int Vit = 1;

        private int Int = 0;

        public int getId() {
            return 0;
        }

        public PersoTable getStatus() {
            PersoTable perso = new PersoTable();
            perso.setClasse(Classe);
            perso.setAgi(Agi);
            perso.setAtk(Atk);
            perso.setAtkM(AtkM);
            perso.setDef(Def);
            perso.setDefM(DefM);
            perso.setVit(Vit);
            perso.setIntl(Int);
            return perso;
        }
    },
    ESPLORADOR{

        public String getClasse() {
            return Classe;
        }

        private String Classe  = "Explorador";

        private int Atk  = 6;

        private int Def  = 7;

        private int Agi = 9;

        private int AtkM = 5;

        private int DefM = 6;

        private int Vit = 0;

        private int Int = 0;

        public int getId() {
            return 1;
        }

        public PersoTable getStatus() {
            PersoTable perso = new PersoTable();
            perso.setClasse(Classe);
            perso.setAgi(Agi);
            perso.setAtk(Atk);
            perso.setAtkM(AtkM);
            perso.setDef(Def);
            perso.setDefM(DefM);
            perso.setVit(Vit);
            perso.setIntl(Int);
            return perso;
        }
    }
}
