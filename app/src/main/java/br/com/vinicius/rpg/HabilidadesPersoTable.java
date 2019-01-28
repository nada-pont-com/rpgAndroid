package br.com.vinicius.rpg;

public class HabilidadesPersoTable {
    private int HabilidadesId;
    private int persoId;
    private int level;

    void setHabilidadesId(int habilidadesId) {
        HabilidadesId = habilidadesId;
    }

    public int getHabilidadesId() {
        return HabilidadesId;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    void setPersoId(int persoId) {
        this.persoId = persoId;
    }

    public int getPersoId() {
        return persoId;
    }

}
