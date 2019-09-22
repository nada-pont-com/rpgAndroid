package br.com.vinicius.rpg.objetosTabelas;

public class HabilidadesPersoTable {
    private int HabilidadesId;
    private int persoId;
    private int level;

    public void setHabilidadesId(int habilidadesId) {
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

    public void setPersoId(int persoId) {
        this.persoId = persoId;
    }

    public int getPersoId() {
        return persoId;
    }

    @Override
    public String toString() {
        return null;
    }
}
