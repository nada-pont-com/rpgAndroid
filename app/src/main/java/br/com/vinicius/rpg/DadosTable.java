package br.com.vinicius.rpg;

public class DadosTable {

    private int id;
    private String nome;
    private int level;
    private int experiencia;
    private int LoadId;
    private String classe;
    private int vida;
    private int vidaMax;
    private int atk;
    private int def;
    private int agi;
    private int atkM;
    private int defM;


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

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setLoadId(int loadId) {
        LoadId = loadId;
    }

    public int getLoadId() {
        return LoadId;
    }

    public void setClasse(String classesClasse) {
        this.classe = classesClasse;
    }

    public String getClasse() {
        return classe;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVida() {
        return vida;
    }

    public void setVidaMax(int vidaMax) {
        this.vidaMax = vidaMax;
    }

    public int getVidaMax() {
        return vidaMax;
    }

    public void setAtk(int atk){
        this.atk = atk;
    }

    public int getAtk(){
        return this.atk;
    }

    public void setDef(int def){
        this.def = def;
    }

    public int getDef(){
        return this.def;
    }

    public void setAgi(int agi){
        this.agi = agi;
    }

    public int getAgi(){
        return this.agi;
    }

    public void setAtkM(int atkM){
        this.atkM = atkM;
    }

    public int getAtkM(){
        return this.atkM;
    }

    public void setDefM(int defM){
        this.defM = defM;
    }

    public int getDefM(){
        return this.defM;
    }

    @Override
    public String toString() {
        return "Id: " + id+ ", Nome: " + nome +" Level: "+level +" Classe: "+classe+" atk: "+atk +" def: "+def+" agi: "+agi+" atkM: "+atkM+" defM: "+defM;
    }
}
