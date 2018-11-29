package br.com.vinicius.rpg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public final class Loads {
    private Loads(){}

    public static class load implements BaseColumns{
        public static final String TABLE_NAME = "load";
        public static final String SQL_CREATE_LOADS = " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome VARCHAR(45) NOT NULL," +
                "tempo TIME NOT NULL" +
                ")";
    }
    public static class classes implements BaseColumns{
        public static final String TABLE_NAME = "classes";
        public static final String SQL_CREATE_CLASSES = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "classe VARCHAR(20) NOT NULL," +
                "atk INT UNSIGNED NOT NULL," +
                "def INT UNSIGNED NOT NULL," +
                "agi INT UNSIGNED NOT NULL," +
                "atkM INT UNSIGNED NOT NULL," +
                "defM INT UNSIGNED NOT NULL)";
        public static final List<JogoTable> SQL_LIST_DADOS = new ArrayList<JogoTable>();
        public static boolean dados(){
            String classe[] = {"Guerreiro","Aventureiro"};
            String atk[] =  {"10"       ,"7"};
            String def[] =  {"10"       ,"6"};
            String agi[] =  {"5"        ,"7"};
            String atkM[] = {"2"        ,"5"};
            String defM[] = {"2"        ,"4"};

            JogoTable dado = null;
            for (int i=0;i<classe.length;i++){
                dado = new JogoTable();
                dado.setClasse(classe[i]);
                dado.setAgi(agi[i]);
                dado.setAtk(atk[i]);
                dado.setAtkM(atkM[i]);
                dado.setDef(def[i]);
                dado.setDefM(defM[i]);

                SQL_LIST_DADOS.add(dado);
            }
            return true;
        }
    }
    public static class dados implements BaseColumns{

        public static final String TABLE_NAME = "dados";
        public static final String SQL_CREATE_DADOS = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome VARCHAR(45) NOT NULL," +
                "level INT NOT NULL," +
                "classes_id INTEGER NOT NULL," +
                "load_id INTEGER NOT NULL," +
                "FOREIGN KEY (load_id) " +
                "REFERENCES load (id)," +
                "FOREIGN KEY (classes_id) " +
                "REFERENCES classes (id)" +
                ")";

    }

    public static class comandos{

        public String Inserir(String nome, String tempo, Context context){
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

        public boolean InserirClasses(SQLiteDatabase db) {
            String colunas[] = {
                    "id",
                    "classe"
            };
            Cursor cursor = db.query(
                    Loads.classes.TABLE_NAME,
                    colunas,
                    null,
                    null,
                    null,
                    null,
                    null);
            List<JogoTable> listaDeClasses = Loads.classes.SQL_LIST_DADOS;
            String texto[] =  new String[listaDeClasses.size()];
            int cont = 0;
            while (cursor.moveToNext()){
                texto[cont] = cursor.getString(1);
                cont++;
            }
            ContentValues values = new ContentValues();
            boolean teste = false;
            boolean validador = false;
            for (int i=0;i<listaDeClasses.size();i++){
                for (int i2=0;i2<listaDeClasses.size();i2++) {
                    System.out.println("Classe: "+listaDeClasses.get(i).getClasse());
                    if (texto[i2].equals(listaDeClasses.get(i).getClasse())) {
                        validador = true;
                    }
                }
                if (validador){
                    validador = false;
                }else{
                    values.put("atk",listaDeClasses.get(i).getAtk());
                    values.put("atkM",listaDeClasses.get(i).getAtkM());
                    values.put("def",listaDeClasses.get(i).getDef());
                    values.put("defM",listaDeClasses.get(i).getDefM());
                    values.put("agi",listaDeClasses.get(i).getAgi());
                    values.put("classe",listaDeClasses.get(i).getClasse());
                    long newRowId = db.insert(classes.TABLE_NAME,null,values);
                    if(newRowId==-1){
                        teste = true;
                    }
                }
            }
            return teste;


        }

        public List<JogoTable> buscaClasses(SQLiteDatabase db){
            List<JogoTable> listaDeClasses = new ArrayList<JogoTable>();
            String colunas[] = {
                    "id",
                    "classe",
                    "atk",
                    "def",
                    "agi",
                    "atkM",
                    "defM"
            };
            Cursor cursor = db.query(
                    Loads.classes.TABLE_NAME,
                    colunas,
                    null,
                    null,
                    null,
                    null,
                    null);
            JogoTable classe = null;
            while (cursor.moveToNext()){
                classe = new JogoTable();
                classe.setId(cursor.getInt(0));
                classe.setClasse(cursor.getString(1));
                classe.setAtk(cursor.getString(2));
                classe.setDef(cursor.getString(3));
                classe.setAgi(cursor.getString(4));
                classe.setAtkM(cursor.getString(5));
                classe.setDefM(cursor.getString(6));

                listaDeClasses.add(classe);
            }
            return listaDeClasses;
        }
    }

}
