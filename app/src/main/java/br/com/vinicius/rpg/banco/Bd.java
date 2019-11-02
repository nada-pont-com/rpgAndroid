package br.com.vinicius.rpg.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.vinicius.rpg.banco.Loads.*;

public class Bd extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_LOADS = "CREATE TABLE IF NOT EXISTS "+load.TABLE_NAME+ load.SQL_CREATE_LOADS;
    private static final String SQL_DELETE_LOADS =
            "DROP TABLE IF EXISTS "+load.TABLE_NAME;
    private static final String SQL_DELETE_PERSO =
            "DROP TABLE IF EXISTS "+perso.TABLE_NAME;
    private static final String SQL_DELETE_ITENS =
            "DROP TABLE IF EXISTS "+itensPerso.TABLE_NAME;
    private static final String SQL_DELETE_PERSO_TEM_HABILIDADES =
            "DROP TABLE IF EXISTS "+perso_tem_habilidades.TABLE_NAME;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RPG.db";

    public Bd(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_LOADS);
        db.execSQL(perso.SQL_CREATE_PERSO);
        db.execSQL(itensPerso.SQL_CREATE_ITENS_PERSO);
        db.execSQL(perso_tem_habilidades.SQL_CREATE_PERSO_TEM_HABILIDADES);
        db.execSQL(misseosLoad.SQL_CREATE_MISSOES_LOAD);
        db.execSQL(dungions_tem_loads.SQL_CREATE_DUNGEONS_TEM_LOADS);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ITENS);
        db.execSQL(SQL_DELETE_PERSO_TEM_HABILIDADES);
        db.execSQL(SQL_DELETE_PERSO);
        db.execSQL(SQL_DELETE_LOADS);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}