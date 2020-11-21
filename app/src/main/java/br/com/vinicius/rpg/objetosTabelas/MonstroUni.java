package br.com.vinicius.rpg.objetosTabelas;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.Random;

import br.com.vinicius.rpg.dados.ItensDados;
import br.com.vinicius.rpg.dados.Monstros;
import br.com.vinicius.rpg.jogo.informacoes.Sessao;

public class MonstroUni implements Serializable {
    private String rank;
    private int level;
    private String nome;
    private int ex;
    private String[] itemQuant;
    private ItensDados[] itens;
    private int vida;
    private int mp;
    private int mpMax;
    private int atk;
    private int def;
    private int agi;
    private int atkM;
    private int defM;

    public MonstroUni(int andar,int andarMax,int tipo,String rank){
        Random random = new Random();

        Monstros monstro = GeraMontro(rank,andar,andarMax,random);

        int vida = monstro.getvida();
        int mp = monstro.getMp();
        int atk = monstro.getAtk();
        int atkM = monstro.getAtkM();
        int agi = monstro.getAgi();
        int def = monstro.getDef();
        int defM = monstro.getDefM();
        rank = monstro.getRank();

        /*System.out.println(valor);
        System.out.println(valorInt);*/

        double valor;
        int valorInt;
        int level = ( ((random.nextInt(1) == 0) ? -1 : 1) * (random.nextInt(2)) ) + Sessao.getDadosPerso().get(0).getLevel();

        double modLevel =  ((double)(level) - 1) / 10;

        double status = 1;
        status *= mod();
        double mod3 = 1;
        switch (tipo){
            case 2: // alfa
                mod3 = 2;
                break;
            case 3: // boss
                mod3 = random.nextInt(4)+4;// 4x a 7x
                double valorMin = 1;
                double valorMax = 3.5;
                double valorF = valorMax-valorMin;
                valor = valorF;
                valor = valor/andarMax;
                valor = valor*andar;
                valorInt = (int) valor;
                if (valorInt==0){
                    valorF = valorMax;
                }else{
                    valorF = valorF/valor;
                }
                mod3 = mod3/valorF;
                break;
        }
        System.out.print("Aqui ---------------------------------------");

        System.out.print(status);

        status = (mod3*status);

        System.out.print(status);
        int ex = (int) (monstro.getExp()*status + monstro.getExp());

        Monstro(rank,(int) (vida+vida*status),(int) (mp+mp*status/2),(int) (atk+atk*status),(int) (def+def*status),(int) (agi+agi*status),(int) (atkM+atkM*status),(int) (defM+defM*status),level,monstro.getNome(),(int) (ex+ex*status),monstro.getItemQuant(),monstro.getItens());
    }

    private void Monstro(String rank, int vida, int mp, int atk, int def, int agi, int atkM, int defM, int level, String nome, int ex, String[] item,ItensDados[] itens){

        this.rank = rank;
        this.vida = (int) (vida  * mod());
        this.mpMax = this.mp = (int) (mp  * mod());
        this.atk = (int) (atk * mod());
        this.def = (int) (def * mod());
        this.agi = (int) (agi * mod());
        this.atkM = (int) (atkM * mod());
        this.defM = (int) (defM * mod());
        this.level = level;
        this.nome = nome;
        this.ex = (int) (ex * mod());
        this.itemQuant = item;// (randon(max-min)) + min
        this.itens = itens;
    }

    private Monstros GeraMontro(String rank, int andar, int andarMax, Random random){
        Monstros montro = Monstros.values()[random.nextInt(Monstros.values().length)];
        double valor = (int) ConverteRank(rank)+1;
        valor = valor/andarMax;
        valor = valor*andar;

        int valorInt = (int) valor;
        valorInt = Math.min(valorInt, 8);

        String rank2 = (String) ConverteRank(valorInt);
        int level = montro.getLevel();
        /*System.out.print("-----------------------   ola mundo --------------------------");
        System.out.print(level);
        System.out.print(Sessao.getDadosPerso().get(0).getLevel());*/

        if(level>Sessao.getDadosPerso().get(0).getLevel()){
            return GeraMontro(rank,andar,andarMax,random);
        }

        if(validadorRank(montro.getRank(),rank2)){
           return montro;
        }
        return GeraMontro(rank,andar,andarMax,random);
    }

    private Object ConverteRank(Object rank){
        if(TextUtils.isDigitsOnly( rank+"")){
            switch ((int) rank){
                case 8:
                    return "S";
                case 7:
                    return "A";
                case 6:
                    return "B";
                case 5:
                    return "C";
                case 4:
                    return "D";
                case 3:
                    return "E";
                case 2:
                    return "F";
                case 1:
                default:
                    return "G";
            }
        }else{
            switch ((String) (rank)){
                case "S":
                    return 8;
                case "A":
                    return 7;
                case "B":
                    return 6;
                case "C":
                    return 5;
                case "D":
                    return 4;
                case "E":
                    return 3;
                case "F":
                    return 2;
                case "G":
                default:
                    return 1;
            }
        }
    }

    private boolean validadorRank(String rank,String rank2){
        switch (rank){
            case "S":
                return (rank2.equals("S"));
            case "A":
                return (rank2.equals("S")) || (rank2.equals("A"));
            case "B":
                return (rank2.equals("S")) || (rank2.equals("A")) || (rank2.equals("B"));
            case "C":
                return (!rank2.equals("G")) && (!rank2.equals("F")) && (!rank2.equals("E")) && (!rank2.equals("D"));
            case "D":
                return (!rank2.equals("G")) && (!rank2.equals("F")) && (!rank2.equals("E"));
            case "E":
                return (!rank2.equals("G")) && (!rank2.equals("F"));
            case "F":
                return !rank2.equals("G");
            case "G":
                return true;
            default:
                return false;
        }
    }

    private double mod(){
        Random random = new Random();
        double mod  = random.nextDouble()+random.nextInt(1);//{1.5,0.5,1,2};
//        System.out.println("ola mundo: "+mod);
        if(mod<0.4){
            return mod();
        }else{
            return mod;
        }
    }
   /* void setRank(String rank) {
        this.rank = rank;
    }*/

    public String getRank() {
        return rank;
    }

    public void setRank(String rank){
        this.rank = rank;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVida() {
        return vida;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMp() {
        return mp;
    }

    public int getEx() {
        return ex;
    }

    public String[] getItemQuant() {
        return itemQuant;
    }

    public ItensDados[] getItens() {
        return itens;
    }

    public int getMpMax() {
        return mpMax;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getAgi() {
        return agi;
    }

    public int getAtkM() {
        return atkM;
    }

    public int getDefM() {
        return defM;
    }
}
