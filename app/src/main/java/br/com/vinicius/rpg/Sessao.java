package br.com.vinicius.rpg;

public class Sessao {
    private static LoadTable load;
    private static DadosTable dados;

    public static LoadTable getLoad() {
        return load;
    }

    public static void setLoad(LoadTable load) {
        Sessao.load = load;
    }

    public static DadosTable getDados() {
        return dados;
    }

    public static void setDados(DadosTable dados) {
        Sessao.dados = dados;
    }
}
