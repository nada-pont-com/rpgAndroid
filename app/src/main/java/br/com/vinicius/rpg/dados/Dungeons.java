package br.com.vinicius.rpg.dados;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import br.com.vinicius.rpg.banco.Loads;
import br.com.vinicius.rpg.jogo.informacoes.Sessao;
import br.com.vinicius.rpg.objetosTabelas.DungeonTable;

public class Dungeons { //TODO nomes novos de acordo com o rank; limitar monstros aos nomes; até 3 para o rank G, aumentar de acordo com o rank;
    private String[] nome = {};
    private String[] nomeRG = {"Floresta Norte","Scarlet","cubias","Dragonideos","Planice"};
    private String[] nomeRF = {"Floresta Sombria"};//R = Rank; F = F;
    private String[] andares = {"25","30","40","45","55","60","80","70","90","100"};
    private String[] rank = {"G","F","E","D","C","B","A","S"};
    private List<DungeonTable> listaDeDungeons = new ArrayList<>();

    public String[] getNome(String rank){
        geraNome(rank);
        return nome;
    }

    public List<DungeonTable> getListaDeDungeons() {
        return listaDeDungeons;
    }

    public void atuDungeons(Context context, Loads.comandos comandos){
        listaDeDungeons = comandos.buscaDungeons(context,Sessao.getLoad().getId());
    }

    public void list(Context context,boolean missao){
        Random random = new Random();
        int andar = random.nextInt(andares.length);
        System.out.println(andar);
        int vali;
        do{
            int randRank = random.nextInt(rank.length);
            vali = 0;
            if(Sessao.getDadosPerso().get(0).validadorRank(rank[randRank])) {
                geraNome(rank[randRank]);
                int name = random.nextInt(nome.length);
                DungeonTable dungeon = new DungeonTable();
                dungeon.setNome(nome[name]);
                dungeon.setAndares("1-" + andares[andar]);
                dungeon.setRank(rank[randRank]);
                listaDeDungeons.add(dungeon);
                if(!missao){
                    salvaDungeon(dungeon,context);
                }
            }else{
                vali = 1;
            }
        }while (vali==1);
    }

    private void salvaDungeon(DungeonTable dungeon,Context context) {
        int loadId = Sessao.getLoad().getId();
        Loads.comandos comando = new Loads.comandos();
        comando.InserirDungeon(loadId,dungeon,context);
    }

    private void geraNome(String rank){
        switch (rank){
            case "S":
                break;
            case "A":
                break;
            case "B":
                break;
            case "C":
                break;
            case "D":
                break;
            case "E":
                break;
            case "F":
                nome = nomeRF;
                break;
            case "G":
                nome = nomeRG;
                break;
        }
    }
}

