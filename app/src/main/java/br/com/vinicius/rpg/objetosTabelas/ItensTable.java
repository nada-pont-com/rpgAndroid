package br.com.vinicius.rpg.objetosTabelas;

public class ItensTable {
    private int id;
    private String nome;
    private String raridade;
    private int quantidade;
    private String[] referencia;
    private int[] valor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRaridade(String raridade){
        this.raridade = raridade;
    }

    public String getRaridade() {
        return raridade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String[] getReferencia() {
        return referencia;
    }

    private void setReferencia(String[] referencia) {
        this.referencia = referencia;
    }

    public int[] getValor() {
        return valor;
    }

    private void setValor(int[] valor) {
        this.valor = valor;
    }

    public void Estatisticas(String estastiticas){
        String[] ReferenciaValor = estastiticas.split(",");
        String[] referencia = new String[ReferenciaValor.length];
        int[] valor = new int[ReferenciaValor.length];
        int cont = 0;
        for (String aReferenciaValor : ReferenciaValor) {
            System.out.println(aReferenciaValor);
            String[] RV = aReferenciaValor.split(":");
            referencia[cont] = RV[0];
            valor[cont] = Integer.parseInt(RV[1]);
            cont++;
        }
        setReferencia(referencia);
        setValor(valor);
    }
}
