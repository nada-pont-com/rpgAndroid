package br.com.vinicius.rpg.jogo.informacoes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Timer;
import java.util.TimerTask;

import br.com.vinicius.rpg.banco.Bd;
import br.com.vinicius.rpg.banco.Loads;
import br.com.vinicius.rpg.objetosTabelas.LoadTable;

public class Tempo {

    public static Timer timer;

    public static void setTimer(Timer timer) {
        Tempo.timer = timer;
    }

    public static class tempo extends TimerTask{
        private boolean ONOFF = false;

        public void setONOFF(boolean ONOFF) {
            this.ONOFF = ONOFF;
        }

        public boolean getONOFF() {
            return ONOFF;
        }

        private final LoadTable load;

        public tempo(LoadTable load){
            this.load = load;
        }
        @Override
        public void run() {
            String[] tempo = load.getTempo().split(":");

            int seg,min,hora;
            seg  = Integer.parseInt(tempo[2]);
            min  = Integer.parseInt(tempo[1]);
            hora = Integer.parseInt(tempo[0]);

            seg++;

            if(seg==60){
                seg = 0;
                min++;
            }
            if(min==60){
                min = 0;
                hora++;
            }
            String seg2 = "",min2 = "",hora2 = "";
            if(seg<10){
                seg2 = "0";
            }
            if(min<10){
                min2 = "0";
            }
            if(hora<10){
                hora2 = "0";
            }
            load.setTempo(hora2+hora+":"+min2+min+":"+seg2+seg);
        }
    }

    public static class autoSalve extends TimerTask{

        private boolean ONOFF = false;

        public void setONOFF(boolean ONOFF) {
            this.ONOFF = ONOFF;
        }

        public boolean getONOFF() {
            return ONOFF;
        }

        private final Context context;
        private final LoadTable load;

        public autoSalve(LoadTable load, Context context){
            this.context = context;
            this.load = load;
        }
        @Override
        public void run() {
            Loads.comandos comandos = new Loads.comandos();
            System.out.println(load.getTempo());
            comandos.atulizarLoad(context,load);
        }
    }
}

