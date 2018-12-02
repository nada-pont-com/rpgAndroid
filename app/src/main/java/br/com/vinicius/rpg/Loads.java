package br.com.vinicius.rpg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

final class Loads {
    private Loads(){}

    static class load implements BaseColumns{
        static final String TABLE_NAME = "load";
        static final String SQL_CREATE_LOADS = " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome VARCHAR(45) NOT NULL UNIQUE," +
                "tempo TIME NOT NULL" +
                ")";
    }
    static class perso implements BaseColumns{
        static final String TABLE_NAME = "perso";
        static final String SQL_CREATE_CLASSES = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (id INTEGER PRIMARY KEY," +
                "nome VARCHAR(40) NOT NULL," +
                "level INT UNSIGNED NOT NULL," +
                "experiencia INT UNSIGNED NOT NULL," +
                "load_id INTEGER NOT NULL," +
                "classe VARCHAR(20) NOT NULL," +
                "vida INT UNSIGNED NOT NULL," +
                "vidaMax INT UNSIGNED NOT NULL," +
                "atk INT UNSIGNED NOT NULL," +
                "def INT UNSIGNED NOT NULL," +
                "agi INT UNSIGNED NOT NULL," +
                "atkM INT UNSIGNED NOT NULL," +
                "defM INT UNSIGNED NOT NULL," +
                "FOREIGN KEY (load_id) " +
                "REFERENCES load (id)" +
                ")";
        static final List<DadosTable> SQL_LIST_DADOS = new ArrayList<DadosTable>();
        static void dados(){
            String classe[] = {"Guerreiro","Aventureiro"};
            String atk[] =  {"10"       ,"7"};
            String def[] =  {"10"       ,"6"};
            String agi[] =  {"5"        ,"7"};
            String atkM[] = {"2"        ,"5"};
            String defM[] = {"2"        ,"4"};

            DadosTable dado;
            for (int i=0;i<classe.length;i++){
                dado = new DadosTable();
                dado.setClasse(classe[i]);
                dado.setAgi(Integer.parseInt(agi[i]));
                dado.setAtk(Integer.parseInt(atk[i]));
                dado.setAtkM(Integer.parseInt(atkM[i]));
                dado.setDef(Integer.parseInt(def[i]));
                dado.setDefM(Integer.parseInt(defM[i]));

                SQL_LIST_DADOS.add(dado);
            }
        }
    }

    static class comandos{

        boolean Inserir(LoadTable load, Context context){
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id", load.getId());
            values.put("nome", load.getNome());
            values.put("tempo", load.getTempo());
            long newRowId = db.insert(Loads.load.TABLE_NAME, null, values);
            banco.close();
            return newRowId != -1;
        }

        boolean InserirDados(DadosTable dados,SQLiteDatabase db) {
            ContentValues values = new ContentValues();
            values.put("id", dados.getId());
            values.put("nome",dados.getNome());
            values.put("level",dados.getLevel());
            values.put("experiencia",dados.getExperiencia());
            values.put("classe",dados.getClasse());
            values.put("load_id",dados.getLoadId());
            values.put("vida",dados.getVida());
            values.put("vidaMax",dados.getVidaMax());
            values.put("atk",dados.getAtk());
            values.put("atkM",dados.getAtkM());
            values.put("def",dados.getDef());
            values.put("defM",dados.getDefM());
            values.put("agi",dados.getAgi());
            long newRowId = db.insert(perso.TABLE_NAME, null, values);
            return newRowId != -1;
        }

        List<LoadTable> buscaLoad(SQLiteDatabase db){
            String colunas[] = {"id",
                    "nome",
                    "tempo"};
            Cursor cursor = db.query(
                    Loads.load.TABLE_NAME,
                    colunas,
                    null,
                    null,
                    null,
                    null,
                    null);
            List<LoadTable> listaDeLoads = new ArrayList<>();
            LoadTable load;
            while (cursor.moveToNext()){
                load = new LoadTable();
                load.setId(cursor.getInt(0));
                load.setNome(cursor.getString(1));
                load.setTempo(cursor.getString(2));
                listaDeLoads.add(load);
            }
            return listaDeLoads;
        }

        List<DadosTable> buscaDados(SQLiteDatabase db){
            List<DadosTable> listaDeDados = new ArrayList<>();
            DadosTable dados;
            String[] colunas = {
                    "id",
                    "nome",
                    "level",
                    "experiencia",
                    "load_id",
                    "classe",
                    "vida",
                    "vidaMax",
                    "atk",
                    "def",
                    "agi",
                    "atkM",
                    "defM"};
            Cursor cursor = db.query(
                    Loads.perso.TABLE_NAME,
                    colunas,
                    null,
                    null,
                    null,
                    null,
                    null);
            while (cursor.moveToNext()){
                dados = new DadosTable();
                dados.setId(cursor.getInt(0));
                dados.setNome(cursor.getString(1));
                dados.setLevel(cursor.getInt(2));
                dados.setExperiencia(cursor.getInt(3));
                dados.setLoadId(cursor.getInt(4));
                dados.setClasse(cursor.getString(5));
                dados.setVida(cursor.getInt(6));
                dados.setVidaMax(cursor.getInt(7));
                dados.setAgi(cursor.getInt(8));
                dados.setAtk(cursor.getInt(9));
                dados.setAtkM(cursor.getInt(10));
                dados.setDef(cursor.getInt(11));
                dados.setDefM(cursor.getInt(12));

                listaDeDados.add(dados);
            }
            return listaDeDados;
        }

        DadosTable buscaDadosPorLoadId(SQLiteDatabase db, int loadId){
            DadosTable dados = new DadosTable();
            String[] colunas = {"id",
                    "nome",
                    "level",
                    "experiencia",
                    "load_id",
                    "classe",
                    "vida",
                    "vidaMax",
                    "atk",
                    "def",
                    "agi",
                    "atkM",
                    "defM"
            };
            String selection = "load_id=?";
            String load_id = loadId+"";
            String[] arg = {load_id};
            Cursor cursor = db.query(
                    Loads.perso.TABLE_NAME,
                    colunas,
                    selection,
                    arg,
                    null,
                    null,
                    "id DESC");
            if(cursor.moveToNext()){
                dados.setId(cursor.getInt(0));
                dados.setNome(cursor.getString(1));
                dados.setLevel(cursor.getInt(2));
                dados.setExperiencia(cursor.getInt(3));
                dados.setLoadId(cursor.getInt(4));
                dados.setClasse(cursor.getString(5));
                dados.setVida(cursor.getInt(6));
                dados.setVidaMax(cursor.getInt(7));
                dados.setAgi(cursor.getInt(8));
                dados.setAtk(cursor.getInt(9));
                dados.setAtkM(cursor.getInt(10));
                dados.setDef(cursor.getInt(11));
                dados.setDefM(cursor.getInt(12));
            }else {
                return null;
            }
            return dados;
        }
    }

}
