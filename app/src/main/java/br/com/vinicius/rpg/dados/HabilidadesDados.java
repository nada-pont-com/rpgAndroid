package br.com.vinicius.rpg.dados;

import br.com.vinicius.rpg.interfaces.Habilidades;
import br.com.vinicius.rpg.objetosTabelas.Status;

public enum HabilidadesDados implements Habilidades {
    ATK_FORTE{
        public int getId() {
            return 0;
        }

        public String getNome() {
            return "Ataque Forte";
        }

        public int getTipo() {
            return 1;
        }

        public int getValor() {
            return 50;
        }

        public int getNumberAtk() {
            return 1;
        }

        public int getNocalte() {
            return 5;
        }

        public Status getExtra() {
            Status status = new Status();
            status.setStatus(0,0,0,0,0);
            return status;
        }

        public int getCusto() {
            return 1;
        }

        public int getMpCusto() {
            return 2;
        }

        public String getDescricao() {
            return "Um ataque forte";
        }

        public int getAumento() {
            return 3;
        }
    },
    NOCALTEAR{

        public int getId(){
            return 1;
        }
        public String getNome() {
            return "Nocaltear";
        }

        public int getTipo() {
            return 1;
        }

        public int getValor() {
            return 25;
        }

        public int getNumberAtk() {
            return 1;
        }

        public int getNocalte() {
            return 55;
        }

        public Status getExtra() {
            Status status = new Status();
            status.setStatus(0,0,0,0,0);
            return status;
        }

        public int getCusto() {
            return 5;
        }

        public int getMpCusto() {
            return 3;
        }

        public String getDescricao() {
            return "Um ataque que tem chance de nocaltear";
        }

        public int getAumento() {
            return 3;
        }
    },
    CONSENTRASAO{
        public int getId(){
            return 2;
        }

        public String getNome() {
            return "Consentrasão";
        }

        public int getTipo() {
            return 2;
        }

        public int getValor() {
            return 10;
        }

        public int getNumberAtk() {
            return 0;
        }

        public int getNocalte() {
            return 0;
        }

        public Status getExtra() {
            Status status = new Status();
            status.setStatus(0,getValor(),getValor(),0,getValor());
            return status;
        }

        public int getCusto() {
            return 12;
        }

        public int getMpCusto() {
            return 3;
        }

        public String getDescricao() {
            return "Aumanta a concentração do personagem";
        }

        public int getAumento() {
            return 3;
        }
    },
    ATK_UP{
        public int getId(){
            return 3;
        }

        public String getNome() {
            return "Ataque UP";
        }

        public int getTipo() {
            return 2;
        }

        public int getValor() {
            return 10;
        }

        public int getNumberAtk() {
            return 0;
        }

        public int getNocalte() {
            return 0;
        }

        public Status getExtra() {
            Status status = new Status();
            status.setStatus(getValor(),0,0,0,0);
            return status;
        }

        public int getCusto() {
            return 8;
        }

        public int getMpCusto() {
            return 2;
        }

        public String getDescricao() {
            return "Aumenta o ataque do personagem no proximo ataque";
        }

        public int getAumento() {
            return 2;
        }
    }
}
