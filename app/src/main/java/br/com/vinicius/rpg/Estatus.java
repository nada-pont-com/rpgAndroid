package br.com.vinicius.rpg;

import java.util.List;

public enum Estatus implements status {
    guerreiro {
        @Override
        public DadosTable getStatus() {
            List<DadosTable> classes = Loads.perso.SQL_LIST_DADOS;
            int i;
            for (i = 0;i<classes.size();i++){
                System.out.println("fora: "+classes.get(i).getClasse());
                if(classes.get(i).getClasse().equals("Guerreiro")){
                    System.out.println("Dentro: "+classes.get(i).getClasse());
                    break;
                }
            }
            System.out.println(classes.get(i));
            return classes.get(i);
        }
    },
    aventureiro{
        @Override
        public DadosTable getStatus() {
            List<DadosTable> classes = Loads.perso.SQL_LIST_DADOS;
            System.out.println(classes);
            int i;
            for (i = 0;i<classes.size();i++){
                System.out.println("fora: "+classes.get(i).getClasse());
                if(classes.get(i).getClasse().equals("Aventureiro")){
                    System.out.println("Dentro: "+classes.get(i).getClasse());
                    break;
                }
            }
            return classes.get(i);
        }
    }


}
