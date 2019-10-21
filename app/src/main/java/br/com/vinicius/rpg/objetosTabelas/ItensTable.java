
package br.com.vinicius.rpg.objetosTabelas;

public class ItensTable {
    private String nome;
    private String[] referencia;
    private int[] valor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
