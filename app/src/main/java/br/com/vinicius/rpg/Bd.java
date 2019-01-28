package br.com.vinicius.rpg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.vinicius.rpg.Loads.*;

public class Bd extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_LOADS = "CREATE TABLE IF NOT EXISTS "+load.TABLE_NAME+ load.SQL_CREATE_LOADS;
    private static final String SQL_DELETE_LOADS =
            "DROP TABLE IF EXISTS "+load.TABLE_NAME;
    private static final String SQL_DELETE_PERSO =
            "DROP TABLE IF EXISTS "+perso.TABLE_NAME;
    private static final String SQL_DELETE_ITENS =
            "DROP TABLE IF EXISTS "+itens.TABLE_NAME;
    private static final String SQL_DELETE_HABILIDADES =
            "DROP TABLE IF EXISTS "+habilidades.TABLE_NAME;
    private static final String SQL_DELETE_PERSO_TEM_HABILIDADES =
            "DROP TABLE IF EXISTS "+perso_tem_habilidades.TABLE_NAME;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RPG.db";

    Bd(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_LOADS);
        db.execSQL(perso.SQL_CREATE_PERSO);
        db.execSQL(itens.SQL_CREATE_ITENS);
        db.execSQL(habilidades.SQL_CREATE_HABILIDADES);
        habilidades.Habilidades(db);
        db.execSQL(perso_tem_habilidades.SQL_CREATE_PERSO_TEM_HABILIDADES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ITENS);
        db.execSQL(SQL_DELETE_PERSO_TEM_HABILIDADES);
        db.execSQL(SQL_DELETE_HABILIDADES);
        db.execSQL(SQL_DELETE_PERSO);
        db.execSQL(SQL_DELETE_LOADS);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}