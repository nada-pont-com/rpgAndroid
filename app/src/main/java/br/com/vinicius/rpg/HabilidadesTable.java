package br.com.vinicius.rpg;

public class HabilidadesTable {
    private int id;
    private String nome;
    private String tipo;//Ataque, fortalecimento
    private int nuberAtk;//numero de ataques que habilidade fara;
    private int valor;
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

    void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    void setNuberAtk(int nuberAtk) {
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

    void setAumento(int aumento) {
        this.aumento = aumento;
    }

    public int getAumento() {
        return aumento;
    }

    void setNocalte(int nocalte) {
        this.nocalte = nocalte;
    }

    public int getNocalte() {
        return nocalte;
    }

    void setExtra(String extra) {
        this.extra = extra;
    }

    public String getExtra() {
        return extra;
    }

    void setPontos(int pontos) {
        this.pontos = pontos;
    }

    int getPontos() {
        return pontos;
    }

    String getDescricao() {
        return descricao;
    }

    void descricao(String descricao){
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

