package br.com.vinicius.rpg;

public class JogoTable {
    private int id;
    private String classe;
    private String atk;
    private String def;
    private String agi;
    private String atkM;
    private String defM;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setClasse(String classe){
        this.classe = classe;
    }

    public String getClasse(){
        return this.classe;
    }

    public void setAtk(String atk){
        this.atk = atk;
    }


    public String getAtk(){
        return this.atk;
    }

    public void setDef(String def){
        this.def = def;
    }

    public String getDef(){
        return this.def;
    }

    public void setAgi(String agi){
        this.agi = agi;
    }

    public String getAgi(){
        return this.agi;
    }

    public void setAtkM(String atkM){
        this.atkM = atkM;
    }

    public String getAtkM(){
        return this.atkM;
    }

    public void setDefM(String defM){
        this.defM = defM;
    }

    public String getDefM(){
        return this.defM;
    }

    @Override
    public String toString() {
        return "classe: "+classe+" atk: "+atk +" def: "+def+" agi: "+agi+" atkM: "+atkM+" defM: "+defM;
    }
}
