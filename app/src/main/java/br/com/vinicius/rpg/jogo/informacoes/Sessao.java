package br.com.vinicius.rpg.jogo.informacoes;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.vinicius.rpg.banco.Loads;
import br.com.vinicius.rpg.dados.Habilidades;
import br.com.vinicius.rpg.objetosTabelas.DadosTable;
import br.com.vinicius.rpg.objetosTabelas.HabilidadesPersoTable;
import br.com.vinicius.rpg.objetosTabelas.HabilidadesTable;
import br.com.vinicius.rpg.objetosTabelas.LoadTable;

public class Sessao {
    private static LoadTable load;
    private static List<DadosTable> dadosPerso;
    private static Tempo.tempo tempo;
    private static Tempo.autoSalve autoSalve;
    private static List<HabilidadesTable> habilidades;
    private static List<List<HabilidadesTable>> ListaDeHabilidadesDosPersonagens;

    public static LoadTable getLoad() {
        return load;
    }

    public static void setLoad(LoadTable load) {
        Sessao.load = load;
    }

    public static List<DadosTable> getDadosPerso() {
        return dadosPerso;
    }

    public static void setDadosPerso(List<DadosTable> dadosPerso) {
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
        Habilidades habilidades = new Habilidades();
        habilidades.Habilidades();
        Sessao.habilidades = habilidades.getHabilidades();
    }

    public static void setNull(){
        setLoad(null);
        setDadosPerso(null);
        setTempo(null);
        setAutoSalve(null);
        setListaDeHabilidadesDosPersonagens(null);
    }

    private static void setListaDeHabilidadesDosPersonagens(List<List<HabilidadesTable>> listaDeHabilidadesDosPersonagens) {
        ListaDeHabilidadesDosPersonagens = listaDeHabilidadesDosPersonagens;
    }

    public static List<List<HabilidadesTable>> getListaDeHabilidadesDosPersonagens() {
        return ListaDeHabilidadesDosPersonagens;
    }

    public static void HabilidadesPersoDados(SQLiteDatabase db){
        Loads.comandos comandos = new Loads.comandos();
        List<List<HabilidadesTable>> ListaDeHabilidadesDosPersonagens = new ArrayList<>();
        if(dadosPerso!=null){
            for (int i3 = 0;i3<dadosPerso.size();i3++){
                List<HabilidadesPersoTable> HabilidadesPerso = comandos.buscaHabilidadesDoPerso(db,0,dadosPerso.get(i3).getId());
                List<HabilidadesTable> habiPerso = new ArrayList<>();
                for (int i = 0;i<HabilidadesPerso.size();i++){
                    habiPerso.add(habilidades.get(habiPerso.get(i).getId()));
                }
                System.out.println(dadosPerso.get(i3).getId());
                ListaDeHabilidadesDosPersonagens.add(habiPerso);
            }
        }else{
            setDadosPerso(comandos.buscaDados(db));
            HabilidadesPersoDados(db);
        }
        setListaDeHabilidadesDosPersonagens(ListaDeHabilidadesDosPersonagens);
    }
}
