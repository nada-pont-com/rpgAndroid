package br.com.vinicius.rpg;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class Loads {
    private Loads(){}

    public static class load implements BaseColumns{
        public static final String TABLE_NAME = "loads";
        public static final String SQL_CREATE_LOADS = " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome VARCHAR(45) NOT NULL," +
                "tempo TIME NOT NULL)";
    }

    public static class a implements BaseColumns{

        public static final String TABLE_NAME = "";
        public static final String SQL_CREATE_ = "";

    }

    public static class comandos{

        public static String Inserir(String nome, String tempo, Context context){
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nome", nome);
            values.put("tempo", tempo);
            long newRowId = db.insert(load.TABLE_NAME, null, values);
            banco.close();
            if(newRowId==-1){
                return "Tudo Errado";
            }else {
                return "Tudo certo";
            }
        }
    }

}
