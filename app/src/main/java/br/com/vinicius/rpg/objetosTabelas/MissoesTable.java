package br.com.vinicius.rpg.objetosTabelas;

import android.support.annotation.NonNull;

import br.com.vinicius.rpg.dados.ItensDados;
import br.com.vinicius.rpg.dados.Monstros;

public class MissoesTable {

    private String nome;
    private int tipo; //salv
    private ItensTable item; //id
    private MonstroUni monstro; //id
    private DungeonTable dungeon; //nome
    private int quant;//salv
    private int dificuldade;
    private String rank;//salv
    private int exp;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public ItensTable getItem() {
        return item;
    }

    public void setItem(ItensTable item) {
        this.item = item;
    }

    public MonstroUni getMonstro() {
        return monstro;
    }

    public void setMonstro(MonstroUni monstro) {
        this.monstro = monstro;
    }

    public DungeonTable getDungeon() {
        return dungeon;
    }

    public void setDungeon(DungeonTable dungeon) {
        this.dungeon = dungeon;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public int getDificuldade(){
        return dificuldade;
    }

    public String getRank(){
        return rank;
    }

    public void setDificuldadeERank(){
        switch (tipo){
            case 1:
                dificuldade = quant*converte(item.getRaridade());
                rank = item.getRaridade();
                nome = "Encontrar "+item.getNome();
                nome+="\nQuant:"+quant+"  Rank:" +rank+"  Dificuldade: "+dificuldade;
                break;
            case 2:
                dificuldade = quant*converte(monstro.getRank());
                rank = monstro.getRank();
                nome = "Matar "+monstro.getNome();
                nome+="\nQuant:"+quant+"  Rank:" +rank+"  Dificuldade: "+dificuldade;
                break;
            case 3:
                dificuldade = (converte(dungeon.getRank()));
                rank = dungeon.getRank();
                nome = "Completar dungeon "+dungeon.getNome();
                nome+="\nRank:" +rank+"  Dificuldade: "+dificuldade;
                break;
        }
    }

    private int converte(String rank){
        switch (rank){
            case "S":
                return 10;
            case "A":
                return 9;
            case "B":
                return 7;
            case "C":
                return 5;
            case "D":
                return 4;
            case "E":
                return 3;
            case "F":
                return 2;
            case "G":
                return 1;
        }
        return 0;
    }

    public void setDados(int itemId,int monstroId,String rank,String dungeonNome){
        switch (tipo){
            case 1:
                this.item = new ItensTable(ItensDados.values()[itemId]);
                break;
            case 2:
                String nome = "";
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
                        break;
                    case "G":
                        nome = Monstros.values()[monstroId].getNome();
                        break;
                }
                monstro.setRank(rank);
                monstro.setNome(nome);
                break;
            case 3:
                dungeon.setNome(dungeonNome);
                dungeon.setRank(rank);
                break;
        }
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }
}
