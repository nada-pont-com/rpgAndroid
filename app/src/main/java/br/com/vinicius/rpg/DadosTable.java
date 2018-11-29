package br.com.vinicius.rpg;

public class DadosTable {

    private int id;
    private String nome;
    private int level;
    private String classesClasse;
    private int LoadId;

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

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setClassesClasse(String classesClasse) {
        this.classesClasse = classesClasse;
    }

    public String getClassesClasse() {
        return classesClasse;
    }

    public void setLoadId(int loadId) {
        LoadId = loadId;
    }

    public int getLoadId() {
        return LoadId;
    }

    @Override
    public String toString() {
        return "Id: " + id+ ", Nome: " + nome +" Level: "+level +" Classe: "+classesClasse;
    }
}
