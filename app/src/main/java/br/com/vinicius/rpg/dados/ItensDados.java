package br.com.vinicius.rpg.dados;

import br.com.vinicius.rpg.interfaces.Item;

public enum ItensDados implements Item {
    GOSMA{
        public String getNome() {
            return "Gosma";
        }

        public String getEstatisticas() {
            return "";
        }

        public String getRaridade() {
            return "G";
        }

        public int getId() {
            return 0;
        }
    },
    OSSO{
        public String getNome() {
            return "Osso";
        }

        public String getEstatisticas() {
            return "";
        }

        public String getRaridade() {
            return "G";
        }

        public int getId() {
            return 1;
        }
    },
    POCAO_HP_P{
        public String getNome() {
            return "Poção pequena de HP";
        }

        public String getEstatisticas() {
            return "HP:100";
        }

        public String getRaridade() {
            return "G";
        }

        public int getId() {
            return 2;
        }
    },
    POCAO_MP_P{
        public String getNome() {
            return "Poção pequena de MP";
        }

        public String getEstatisticas() {
            return "MP:10";
        }

        public String getRaridade() {
            return "G";
        }

        public int getId() {
            return 3;
        }
    },
    COURO{
        public String getNome() {
            return "Couro";
        }

        public String getEstatisticas() {
            return "";
        }

        public String getRaridade() {
            return "G";
        }

        public int getId() {
            return 4;
        }
    },
    CARNE_COMUN{
        public String getNome() {
            return "Carne Comun";
        }

        public String getEstatisticas() {
            return "";
        }

        public String getRaridade() {
            return "G";
        }

        public int getId() {
            return 5;
        }
    },
    FERRO{
        public String getNome() {
            return "Ferro";
        }

        public String getEstatisticas() {
            return "";
        }

        public String getRaridade() {
            return "G";
        }

        public int getId() {
            return 6;
        }
    }
}
