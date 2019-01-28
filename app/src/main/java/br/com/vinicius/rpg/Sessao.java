package br.com.vinicius.rpg;

import java.util.List;

public class Sessao {
    private static LoadTable load;
    private static List<DadosTable> dadosPerso;
    private static Tempo.tempo tempo;
    private static Tempo.autoSalve autoSalve;

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
}
