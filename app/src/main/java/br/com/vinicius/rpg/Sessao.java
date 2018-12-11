package br.com.vinicius.rpg;

import java.util.List;

public class Sessao {
    private static LoadTable load;
    private static List<DadosTable> dadosPerso;

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
}
