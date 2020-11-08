package br.com.vinicius.rpg.dados;


import java.util.ArrayList;
import java.util.List;

import br.com.vinicius.rpg.objetosTabelas.HabilidadesTable;

public class Habilidades {
    private List<HabilidadesTable> habilidades = new ArrayList<>();
    public void Habilidades() {
        String[] nome = {"Ataque Forte", "Nocaltear", "Consentrasão", "Ataque UP"};
        String[] tipo = {"1", "1", "2", "2"};
        String[] valor = {"50", "25", "10", "10"};
        String[] nunberAtk = {"1", "1", "0", "0"};
        String[] aumento = {"3", "1", "2", "2"};
        String[] nocalte = {"5", "55", "0", "0"};
        String[] extra = {"", "", "def,agi,defM", "atk"};
        String[] pontos = {"1", "5", "12", "8"};
        String[] descricao = {"Um ataque forte", "Um ataque que tem chance de nocaltear", "Aumanta a concentração do personagem", "Aumanta o ataque do personagem no proximo ataque"};
        String[] custo = {"2","3","3","2"};
        HabilidadesTable habilidade;
        for(int i = 0;i<nome.length;i++) {
            habilidade = new HabilidadesTable();
            habilidade.setId(i+1);
            habilidade.setNome(nome[i]);
            habilidade.setTipo(tipo[i]);
            habilidade.setValor(Integer.parseInt(valor[i]));
            habilidade.setNuberAtk(Integer.parseInt(nunberAtk[i]));
            habilidade.setAumento(Integer.parseInt(aumento[i]));
            habilidade.setNocalte(Integer.parseInt(nocalte[i]));
            habilidade.setExtra(extra[i]);
            habilidade.setPontos(Integer.parseInt(pontos[i]));
            habilidade.setCusto(Integer.parseInt(custo[i]));
            habilidade.descricao(descricao[i]);
            System.out.println("habilidade "+i);
            habilidades.add(habilidade);
        }
    }
    public List<HabilidadesTable> getHabilidades(){
        return habilidades;
    }
}
