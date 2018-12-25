package br.com.vinicius.rpg;

public class DadosTable {

    private int id;
    private String nome;
    private int level;
    private int experiencia;
    private int pontosExp;
    private int LoadId;
    private String classe;
    private String rank;
    private int rankExp;
    private int vida;
    private int vidaMax;
    private int mp;
    private int mpMax;
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
        int expMax = getExpMax();
        while(experiencia>=expMax){
            System.out.println(experiencia);
            expMax = getExpMax();
            experiencia = (experiencia-expMax);
            this.level++;
            this.pontosExp = this.pontosExp+5;
            this.vidaMax = this.vidaMax*2;
            this.vida = this.vidaMax;
        }
        this.experiencia = experiencia;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public int getExpMax(){
        double i = Math.pow(2,(level-1));//potencia 2^level
        return (int) (100*i);
    }

    public void setPontosExp(int pontosExp) {
        this.pontosExp = pontosExp;
    }

    public int getPontosExp() {
        return pontosExp;
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

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setRankExp(int rankExp) {
        this.rankExp = rankExp;
    }

    public int getRankExp() {
        return rankExp;
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

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMp() {
        return mp;
    }

    public void setMpMax(int mpMax) {
        this.mpMax = mpMax;
    }

    public int getMpMax() {
        return mpMax;
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
