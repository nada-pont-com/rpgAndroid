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
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "RPG.db";

    public Bd(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_LOADS);
        db.execSQL(Loads.perso.SQL_CREATE_PERSO);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_LOADS);
        db.execSQL(SQL_DELETE_PERSO);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}