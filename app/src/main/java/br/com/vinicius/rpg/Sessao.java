package br.com.vinicius.rpg;

public class Sessao {
    private static LoadTable load;
    private static DadosTable dadosPerso;

    public static LoadTable getLoad() {
        return load;
    }

    public static void setLoad(LoadTable load) {
        Sessao.load = load;
    }

    public static DadosTable getDadosPerso() {
        return dadosPerso;
    }

    public static void setDadosPerso(DadosTable dadosPerso) {
        Sessao.dadosPerso = dadosPerso;
    }
}
