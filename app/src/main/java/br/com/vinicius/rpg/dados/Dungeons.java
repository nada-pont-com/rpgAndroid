package br.com.vinicius.rpg.dados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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



    public List<DungeonTable> getListaDeDungeons() {
        return listaDeDungeons;
    }

    public void atuDungeons(SQLiteDatabase db, Loads.comandos comandos){
        listaDeDungeons = comandos.buscaDungeons(db,Sessao.getLoad().getId());
    }

    public void list(Context context,boolean missao){
        Random random = new Random();
        int andar = random.nextInt(andares.length);
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

    public void geraNome(String rank){
        int soma;
        List<String[]> listaDeNomes = new ArrayList<>();
        listaDeNomes.add(nomeRG);
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
                soma = nomeRG.length + nomeRF.length;
                nome = new String[soma];
                listaDeNomes.add(nomeRF);
                break;
            case "G":
                nome = new String[nomeRG.length];
                break;
        }
        somaNomes(listaDeNomes);
    }

    private void somaNomes(List<String[]> listaDeNomes) {
        int cont = 0;
        for (int i = 0;i<listaDeNomes.size();i++){
            for (int i2 = 0;i2<listaDeNomes.get(i).length;i2++){
                nome[cont] = listaDeNomes.get(i)[i2];
                cont++;
            }
        }
    }
}

