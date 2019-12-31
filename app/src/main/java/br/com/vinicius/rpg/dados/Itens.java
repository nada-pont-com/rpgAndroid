package br.com.vinicius.rpg.dados;

import java.util.ArrayList;
import java.util.List;

import br.com.vinicius.rpg.objetosTabelas.ItensTable;

public class Itens {
    private String[] nome   =   {"Gosma","Osso","pocaoHP","pocaoMP","Couro","","","","","","",""};
    private String[] estatistica={""    ,""    ,"HP:100" ,"MP:10"  ,""     ,"","","","","","",""};
    private String[] raridade =  {"G"    ,"G"  ,"G"      ,"G"      ,"G"    ,"","","","","","",""};
    private List<ItensTable> listaDeItens = new ArrayList<>();

    public void geraList(){
        for (int i = 0;i<nome.length;i++){
            ItensTable itensTable;
            if(!nome[i].equals("")){
                itensTable = new ItensTable();
                itensTable.setNome(nome[i]);
                itensTable.setRaridade(raridade[i]);
                if(!estatistica[i].equals("")){
                    itensTable.Estatisticas(estatistica[i]);
                }
                listaDeItens.add(itensTable);
            }
        }
    }

    public List<ItensTable> getListaDeItens() {
        return listaDeItens;
    }
}