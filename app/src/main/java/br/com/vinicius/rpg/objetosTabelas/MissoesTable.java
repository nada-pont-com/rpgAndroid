package br.com.vinicius.rpg.objetosTabelas;

import br.com.vinicius.rpg.dados.Itens;
import br.com.vinicius.rpg.jogo.monstros.RankG;

public class MissoesTable {
    private int tipo;
    private ItensTable item;
    private MonstroUni monstro;
    private DungeonTable dungeon;
    private int quant;
    private int dificuldade;
    private String rank;
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

    public MonstroUni getMonstro() {
        return monstro;
    }

    public DungeonTable getDungeon() {
        return dungeon;
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
                break;
            case 2:
                dificuldade = quant*converte(monstro.getRank());
                rank = monstro.getRank();
                break;
            case 3:
                int andar = Integer.parseInt((dungeon.getAndares().split("-"))[1]);
                dificuldade = (andar*converte(dungeon.getRank()))/10;
                rank = dungeon.getRank();
                break;
        }
    }

    private int converte(String string){
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

    public void setDados(int itemId,int monstroId,String rankMostro,String dungeonNome){
        switch (tipo){
            case 1:
                Itens itens = new Itens();
                item = itens.getListaDeItens().get(itemId);
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
                        nome = RankG.values()[monstroId].getNome();
                        break;
                }
                monstro.setRank(rankMostro);
                monstro.setNome(nome);
                break;
            case 3:
                break;
        }
    }
}
