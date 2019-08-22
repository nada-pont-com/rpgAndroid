package br.com.vinicius.rpg;

public class DadosTable { //Dados do jogador

    private int id;
    private String nome;
    private int level;
    private int experiencia;
    private int pontosExp;
    private int pontosHab;
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
    private int vit;
    private int intl;

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

    void setExperiencia(int experiencia) {
        int expMax = getExpMax();
        while(experiencia>=expMax){
            System.out.println(experiencia);
            expMax = getExpMax();
            experiencia = (experiencia-expMax);
            this.level++;
            this.pontosExp = this.pontosExp+5;
            this.pontosHab = this.pontosHab+2;
            this.vidaMax = (int) (this.vidaMax*1.25);
            this.vida = this.vidaMax;
            this.atk++;
            this.def++;
            this.atkM++;
            this.defM++;
            this.agi++;
            this.mp = this.mpMax = this.mpMax+5;
            System.out.print(mp);
        }
        this.experiencia = experiencia;
    }

    int getExperiencia() {
        return experiencia;
    }

    int getExpMax(){
        double i = Math.pow(2,(level-1));//potencia 2^(level-1)
        double d = Math.pow(1.5,(level));//potencia 1.5^level
        return (int) ((100*i)/d);
    }

    void setPontosExp(int pontosExp) {
        this.pontosExp = pontosExp;
    }

    int getPontosExp() {
        return pontosExp;
    }

    public void setPontosHab(int pontosHab) {
        this.pontosHab = pontosHab;
    }

    public int getPontosHab() {
        return pontosHab;
    }

    void setLoadId(int loadId) {
        LoadId = loadId;
    }

    int getLoadId() {
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

    void setRankExp(int rankExp) {
        this.rankExp = rankExp;
    }

    int getRankExp() {
        return rankExp;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVida() {
        return vida;
    }

    void setVidaMax(int vidaMax) {
        this.vidaMax = vidaMax;
    }

    int getVidaMax() {
        return (vidaMax+(vit*10));
    }

    int getVidaM(){
        return this.vidaMax;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMp() {
        return mp;
    }

    void setMpMax(int mpMax) {
        this.mpMax = mpMax;
    }

    int getMpMax() {
        return (mpMax+(intl*2));
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

    int getVit() {
        return vit;
    }

    void setVit(int vit) {
        this.vit = vit;
    }

    int getIntl() {
        return intl;
    }

    void setIntl(int intl) {
        this.intl = intl;
    }

    public boolean validadorRank(String rank){
        switch (rank){
            case "S":
                return (this.rank.equals("S"));
            case "A":
                return (this.rank.equals("S")) || (this.rank.equals("A"));
            case "B":
                return (this.rank.equals("S")) || (this.rank.equals("A")) || (this.rank.equals("B"));
            case "C":
                return (!this.rank.equals("G")) && (!this.rank.equals("F")) && (!this.rank.equals("E")) && (!this.rank.equals("E"));
            case "D":
                return (!this.rank.equals("G")) && (!this.rank.equals("F")) && (!this.rank.equals("E"));
            case "E":
                return (!this.rank.equals("G")) && (!this.rank.equals("F"));
            case "F":
                return !this.rank.equals("G");
            case "G":
                return true;
            default:
                return false;
        }
    }
    @Override
    public String toString() {
        return "Id: " + id+ ", Nome: " + nome +" Level: "+level +" Classe: "+classe+" atk: "+atk +" def: "+def+" agi: "+agi+" atkM: "+atkM+" defM: "+defM;
    }
}
