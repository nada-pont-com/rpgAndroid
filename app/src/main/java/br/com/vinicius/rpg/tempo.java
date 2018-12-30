package br.com.vinicius.rpg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.TimerTask;

public class Tempo extends TimerTask {

    private LoadTable load;

    public Tempo(LoadTable load){
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

class AutoSalve extends TimerTask{

    private LoadTable load;
    private Context context;

    AutoSalve(LoadTable load, Context context){
        this.load = load;
        this.context = context;
    }
    @Override
    public void run() {
        Bd Banco = new Bd(context);
        SQLiteDatabase db = Banco.getWritableDatabase();
        Loads.comandos comandos = new Loads.comandos();
        System.out.println(load.getTempo());
        comandos.atulizarLoad(db,load);
        db.close();
    }
}
