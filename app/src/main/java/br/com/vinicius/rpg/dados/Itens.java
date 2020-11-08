package br.com.vinicius.rpg.dados;

import java.util.ArrayList;
import java.util.List;

import br.com.vinicius.rpg.objetosTabelas.ItensTable;

public class Itens {           //   1      2        3          4         5        6       7
    private String[] nome   =   {"Gosma","Osso","Pocao HP","Pocao MP","Couro","Carne_C","","","","","",""};
    private String[] estatistica={""    ,""    ,"HP:100" ,"MP:10"  ,""     ,"","","","","","",""};
    private String[] raridade =  {"G"    ,"G"  ,"G"      ,"G"      ,"G"    ,"G","G","G","G","G","G","G"};
    private List<ItensTable> listaDeItens = null;

    public void geraList(){
        listaDeItens = new ArrayList<>();
        int cont = 0;
        for (int i = 0;i<nome.length;i++){
            ItensTable itensTable;
            if(!nome[i].equals("")){
                itensTable = new ItensTable();
                itensTable.setId(cont);
                itensTable.setNome(nome[i]);
                itensTable.setRaridade(raridade[i]);
                cont++;
                if(!estatistica[i].equals("")){
                    itensTable.Estatisticas(estatistica[i]);
                }
                listaDeItens.add(itensTable);
            }
        }
    }

    public ItensTable getItensId(int id){
        if (listaDeItens==null){
            geraList();
        }
        return listaDeItens.get(id);
    }

    public List<ItensTable> getListaDeItens() {
        if (listaDeItens==null){
            geraList();
        }
        return listaDeItens;
    }
}