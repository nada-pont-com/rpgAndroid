package br.com.vinicius.rpg.objetosTabelas;

public class HabilidadesTable {
    private int id;
    private String nome;
    private String tipo;//1-Ataque, 2-fortalecimento
    private int nuberAtk;//numero de ataques que habilidade fara;
    private int valor;// dano, aumento // porcentagem;
    private int aumento;
    private int nocalte;
    private String extra;//tipo de fortalecimento;
    private int pontos;//pontos necessarios para adquirir a habilidade;
    private String descricao;

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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
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

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getExtra() {
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

    public void descricao(String descricao){
        this.descricao = descricao+
                "\n"+valor+
                "\nAumento por level "+aumento+
                "\n";
        if(!extra.equals("")){
            this.descricao = this.descricao+"Efeito:"+extra;
        }
    }

    @Override
    public String toString() {
        return nome;
    }
}

