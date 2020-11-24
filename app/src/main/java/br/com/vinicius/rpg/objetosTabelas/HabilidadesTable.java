package br.com.vinicius.rpg.objetosTabelas;

import br.com.vinicius.rpg.dados.HabilidadesDados;

public class HabilidadesTable {
    private int id;
    private String nome;
    private int tipo;//1-Ataque, 2-fortalecimento
    private int nuberAtk;//numero de ataques que habilidade fara;
    private int valor;// dano, aumento // porcentagem;
    private int aumento;
    private int nocalte;
    private Status extra;//tipo de fortalecimento;
    private int pontos;//pontos necessarios para adquirir a habilidade;
    private String descricao;
    private int custoMp; //custo de mp

    public HabilidadesTable(HabilidadesDados habilidade){
        this.id = habilidade.getId();
        this.nome = habilidade.getNome();
        this.tipo = habilidade.getTipo();
        this.nuberAtk = habilidade.getNumberAtk();
        this.valor = habilidade.getValor();
        this.aumento = habilidade.getAumento();
        this.nocalte = habilidade.getNocalte();
        this.extra = habilidade.getExtra();
        this.pontos = habilidade.getCusto();
        this.descricao = habilidade.getDescricao();
        this.custoMp = habilidade.getMpCusto();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setNuberAtk(int nuberAtk) {
        this.nuberAtk = nuberAtk;
    }

    public int getNuberAtk() {
        return nuberAtk;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public void setAumento(int aumento) {
        this.aumento = aumento;
    }

    public int getAumento() {
        return aumento;
    }

    public void setNocalte(int nocalte) {
        this.nocalte = nocalte;
    }

    public int getNocalte() {
        return nocalte;
    }

    public void setExtra(Status extra) {
        this.extra = extra;
    }

    public Status getExtra() {
        return extra;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getPontos() {
        return pontos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setCusto(int custoMp) {
        this.custoMp = custoMp;
    }

    public int getCusto() {
        return custoMp;
    }

    public void descricao(String descricao){
        this.descricao = descricao+
                "\n"+valor+"%   Aumento por level "+aumento+
                "\n"+custoMp+" MP";
        if(!extra.equals("")){
            this.descricao = this.descricao+"\nEfeito:"+extra;
        }
    }

    @Override
    public String toString() {
        return nome;
    }
}

