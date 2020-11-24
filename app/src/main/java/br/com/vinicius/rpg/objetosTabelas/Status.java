package br.com.vinicius.rpg.objetosTabelas;

public class Status {
    protected int vida;
    protected int vidaMax;
    protected int mp;
    protected int mpMax;
    protected int atk;
    protected int def;
    protected int agi;
    protected int atkM;
    protected int defM;
    protected int vit;
    protected int intl;

    public void setStatus(int atk,int def,int agi,int atkM,int defM){
        this.atk = atk;
        this.def = def;
        this.agi = agi;
        this.atkM = atkM;
        this.defM = defM;
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
        return (vidaMax+(vit*10));
    }

    public int getVidaM(){
        return this.vidaMax;
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

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getIntl() {
        return intl;
    }

    public void setIntl(int intl) {
        this.intl = intl;
    }


}
