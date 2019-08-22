package br.com.vinicius.rpg;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Sessao {
    private static LoadTable load;
    private static List<DadosTable> dadosPerso;
    private static Tempo.tempo tempo;
    private static Tempo.autoSalve autoSalve;
    private static List<HabilidadesTable> habilidades;
    private static List<List<HabilidadesTable>> ListaDeHabilidadesDosPersonagens;

    static LoadTable getLoad() {
        return load;
    }

    static void setLoad(LoadTable load) {
        Sessao.load = load;
    }

    static List<DadosTable> getDadosPerso() {
        return dadosPerso;
    }

    static void setDadosPerso(List<DadosTable> dadosPerso) {
        Sessao.dadosPerso = dadosPerso;
    }

    static Tempo.tempo getTempo() {
        return tempo;
    }

    public static void setTempo(Tempo.tempo tempo){
        Sessao.tempo = tempo;
    }

    static Tempo.autoSalve getAutoSalve() {
        return autoSalve;
    }

    public static void setAutoSalve(Tempo.autoSalve autoSalve){
        Sessao.autoSalve = autoSalve;
    }

    public static List<HabilidadesTable> getHabilidades() {
        return habilidades;
    }

    static void setHabilidades(List<HabilidadesTable> habilidades) {
        Sessao.habilidades = habilidades;
    }

    static void setNull(){
        setLoad(null);
        setDadosPerso(null);
        setTempo(null);
        setAutoSalve(null);
        setListaDeHabilidadesDosPersonagens(null);
    }

    private static void setListaDeHabilidadesDosPersonagens(List<List<HabilidadesTable>> listaDeHabilidadesDosPersonagens) {
        ListaDeHabilidadesDosPersonagens = listaDeHabilidadesDosPersonagens;
    }

    static List<List<HabilidadesTable>> getListaDeHabilidadesDosPersonagens() {
        return ListaDeHabilidadesDosPersonagens;
    }

    static void HabilidadesPersoDados(SQLiteDatabase db){
        Loads.comandos comandos = new Loads.comandos();
        List<List<HabilidadesTable>> ListaDeHabilidadesDosPersonagens = new ArrayList<>();
        if(dadosPerso!=null && habilidades!=null){
            for (int i3 = 0;i3<dadosPerso.size();i3++){
                List<HabilidadesPersoTable> HabilidadesPerso = comandos.buscaHabilidadesDoPerso(db,0,dadosPerso.get(i3).getId());
                List<HabilidadesTable> habiPerso = new ArrayList<>();
                for (int i = 0;i<HabilidadesPerso.size();i++){
                    for (int i2 = 0;i2<habilidades.size();i2++){
                        if(habilidades.get(i2).getId()==HabilidadesPerso.get(i).getHabilidadesId()){
                            habiPerso.add(habilidades.get(i2));
                            break;
                        }
                    }
                }
                System.out.println(dadosPerso.get(i3).getId());
                ListaDeHabilidadesDosPersonagens.add(habiPerso);
            }
        }else if(dadosPerso==null){
            setDadosPerso(comandos.buscaDados(db));
            HabilidadesPersoDados(db);
        }else{
            setHabilidades(comandos.buscaHabilidades(db));
            HabilidadesPersoDados(db);
        }
        setListaDeHabilidadesDosPersonagens(ListaDeHabilidadesDosPersonagens);
    }
}
