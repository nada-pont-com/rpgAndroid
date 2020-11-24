package br.com.vinicius.rpg.jogo.informacoes;

import android.annotation.SuppressLint;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.vinicius.rpg.banco.Loads;
import br.com.vinicius.rpg.dados.HabilidadesDados;
import br.com.vinicius.rpg.objetosTabelas.MissoesTable;
import br.com.vinicius.rpg.objetosTabelas.PersoTable;
import br.com.vinicius.rpg.objetosTabelas.HabilidadesPersoTable;
import br.com.vinicius.rpg.objetosTabelas.HabilidadesTable;
import br.com.vinicius.rpg.objetosTabelas.LoadTable;

public class Sessao {
    private static LoadTable load;
    private static List<PersoTable> dadosPerso;
    private static Tempo.tempo tempo;
    @SuppressLint("StaticFieldLeak")
    private static Tempo.autoSalve autoSalve;
    private static List<HabilidadesTable> habilidades;
    private static List<List<HabilidadesPersoTable>> ListaDeHabilidadesDosPersonagens;
    private static List<MissoesTable> ListaDeMissoes;
    private static List<MissoesTable> ListaDeMissoesAceitas;

    public static LoadTable getLoad() {
        return load;
    }

    public static void setLoad(LoadTable load) {
        Sessao.load = load;
    }

    public static List<PersoTable> getDadosPerso() {
        return dadosPerso;
    }

    public static void setDadosPerso(List<PersoTable> dadosPerso) {
        Sessao.dadosPerso = dadosPerso;
    }

    public static Tempo.tempo getTempo() {
        return tempo;
    }

    public static void setTempo(Tempo.tempo tempo){
        Sessao.tempo = tempo;
    }

    public static Tempo.autoSalve getAutoSalve() {
        return autoSalve;
    }

    public static void setAutoSalve(Tempo.autoSalve autoSalve){
        Sessao.autoSalve = autoSalve;
    }

    public static List<HabilidadesTable> getHabilidades() {
        return habilidades;
    }

    public static void setHabilidades() {
        habilidades = new ArrayList<>();
        for(HabilidadesDados habiliade : HabilidadesDados.values()){
            habilidades.add(new HabilidadesTable(habiliade));
        }
//      Habilidades habilidades = new Habilidades();
//      habilidades.Habilidades();
//      Sessao.habilidades = habilidades.getHabilidades();
    }

    public static void setNull(){
        setLoad(null);
        setDadosPerso(null);
        setTempo(null);
        setAutoSalve(null);
        setListaDeHabilidadesDosPersonagens(null);
    }

    private static void setListaDeHabilidadesDosPersonagens(List<List<HabilidadesPersoTable>> listaDeHabilidadesDosPersonagens) {
        ListaDeHabilidadesDosPersonagens = listaDeHabilidadesDosPersonagens;
    }

    public static List<List<HabilidadesPersoTable>> getListaDeHabilidadesDosPersonagens() {
        return ListaDeHabilidadesDosPersonagens;
    }

    public static void HabilidadesPersoDados(Context context){
        Loads.comandos comandos = new Loads.comandos();
        for (PersoTable perso : dadosPerso){
            ListaDeHabilidadesDosPersonagens.add(comandos.buscaHabilidadesDoPerso(context,0,perso.getId()));
        }
    }

    public static List<MissoesTable> getListaDeMissoes() {
        return ListaDeMissoes;
    }

    public static void setListaDeMissoes(List<MissoesTable> listaDeMissoes) {
        ListaDeMissoes = listaDeMissoes;
    }

    public static void setListaDeMissoesAceitas(List<MissoesTable> listaDeMissoesAceitas) {
        ListaDeMissoesAceitas = listaDeMissoesAceitas;
    }

    public static List<MissoesTable> getListaDeMissoesAceitas() {
        return ListaDeMissoesAceitas;
    }
}
