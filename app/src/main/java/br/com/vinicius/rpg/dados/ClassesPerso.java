package br.com.vinicius.rpg.dados;

import java.util.List;

import br.com.vinicius.rpg.banco.Loads;
import br.com.vinicius.rpg.jogo.interfaces.ClassesInterface;
import br.com.vinicius.rpg.objetosTabelas.PersoTable;

public enum ClassesPerso implements ClassesInterface {
    guerreiro {
        @Override
        public PersoTable getStatus() {
            List<PersoTable> classes = Loads.perso.SQL_LIST_DADOS;
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
    explorador{
        @Override
        public PersoTable getStatus() {
            List<PersoTable> classes = Loads.perso.SQL_LIST_DADOS;
            System.out.println(classes);
            int i;
            for (i = 0;i<classes.size();i++){
                System.out.println("fora: "+classes.get(i).getClasse());
                if(classes.get(i).getClasse().equals("Explorador")){
                    System.out.println("Dentro: "+classes.get(i).getClasse());
                    break;
                }
            }
            return classes.get(i);
        }
    }


}
