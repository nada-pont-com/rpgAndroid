package br.com.vinicius.rpg;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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
                "tempo String NOT NULL" +
                ")";
    }
    static class perso implements BaseColumns{
        static final String TABLE_NAME = "perso";
        static final String SQL_CREATE_PERSO = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (id INTEGER NOT NULL," +
                "nome VARCHAR(40) NOT NULL," +
                "level INT UNSIGNED NOT NULL," +
                "experiencia INT UNSIGNED NOT NULL," +
                "pontosExp INT UNSIGNED NOT NULL," +
                "load_id INTEGER NOT NULL," +
                "classe VARCHAR(20) NOT NULL," +
                "rank CHAR(1) NOT NULL," +
                "rankExp INT UNSIGNED  NOT NULL," +
                "vida INT UNSIGNED NOT NULL," +
                "vidaMax INT UNSIGNED NOT NULL," +
                "mp INT UNSIGNED NOT NULL," +
                "mpMax INT UNSIGNED NOT NULL," +
                "atk INT UNSIGNED NOT NULL," +
                "def INT UNSIGNED NOT NULL," +
                "agi INT UNSIGNED NOT NULL," +
                "atkM INT UNSIGNED NOT NULL," +
                "defM INT UNSIGNED NOT NULL," +
                "vit INT UNSIGNED NOT NULL," +
                "inteli INT UNSIGNED NOT NULL," +
                "FOREIGN KEY (load_id) " +
                "REFERENCES load (id)," +
                "PRIMARY KEY (load_id,id)" +
                ")";
        static final List<DadosTable> SQL_LIST_DADOS = new ArrayList<DadosTable>();
        static void dados(){
            String classe[] = {"Guerreiro","Aventureiro"};
            String atk[] =  {"10"       ,"7"};
            String def[] =  {"10"       ,"6"};
            String agi[] =  {"5"        ,"9"};
            String atkM[] = {"2"        ,"5"};
            String defM[] = {"2"        ,"6"};
            String vit[] =  {"0"        ,"0"};
            String intl[] =  {"0"        ,"0"};

            DadosTable dado;
            for (int i=0;i<classe.length;i++){
                dado = new DadosTable();
                dado.setClasse(classe[i]);
                dado.setAgi(Integer.parseInt(agi[i]));
                dado.setAtk(Integer.parseInt(atk[i]));
                dado.setAtkM(Integer.parseInt(atkM[i]));
                dado.setDef(Integer.parseInt(def[i]));
                dado.setDefM(Integer.parseInt(defM[i]));
                dado.setVit(Integer.parseInt(vit[i]));
                dado.setIntl(Integer.parseInt(intl[i]));

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
            values.put("pontosExp",0);
            values.put("classe",dados.getClasse());
            values.put("rank",dados.getRank());
            values.put("rankExp",dados.getRankExp());
            values.put("load_id",dados.getLoadId());
            values.put("vida",dados.getVida());
            values.put("vidaMax",dados.getVidaMax());
            values.put("mp",dados.getMp());
            values.put("mpMax",dados.getMpMax());
            values.put("atk",dados.getAtk());
            values.put("atkM",dados.getAtkM());
            values.put("def",dados.getDef());
            values.put("defM",dados.getDefM());
            values.put("agi",dados.getAgi());
            values.put("vit",dados.getVit());
            values.put("inteli",dados.getIntl());

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
                    "rank",
                    "rankExp",
                    "vida",
                    "vidaMax",
                    "mp",
                    "mpMax",
                    "atk",
                    "def",
                    "agi",
                    "atkM",
                    "defM",
                    "pontosExp",
                    "vit",
                    "inteli"
            };
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
                dados.setRank(cursor.getString(6));
                dados.setRankExp(cursor.getInt(7));
                dados.setVida(cursor.getInt(8));
                dados.setVidaMax(cursor.getInt(9));
                dados.setMp(cursor.getInt(10));
                dados.setMpMax(cursor.getInt(11));
                dados.setAtk(cursor.getInt(12));
                dados.setDef(cursor.getInt(13));
                dados.setAgi(cursor.getInt(14));
                dados.setAtkM(cursor.getInt(15));
                dados.setDefM(cursor.getInt(16));
                dados.setPontosExp(cursor.getInt(17));
                dados.setVit(cursor.getInt(18));
                dados.setIntl(cursor.getInt(19));

                listaDeDados.add(dados);
            }
            return listaDeDados;
        }

        List<DadosTable> buscaDadosPorLoadId(SQLiteDatabase db, int loadId,int id){
            DadosTable dados;
            List<DadosTable> listaDeDados = new ArrayList<>();
            String[] colunas = {"id",
                    "nome",
                    "level",
                    "experiencia",
                    "load_id",
                    "classe",
                    "rank",
                    "rankExp",
                    "vida",
                    "vidaMax",
                    "mp",
                    "mpMax",
                    "atk",
                    "def",
                    "agi",
                    "atkM",
                    "defM",
                    "pontosExp",
                    "vit",
                    "inteli"
            };
            String selection = "load_id=?";
            String load_id = loadId+"";
            String[] arg = {load_id};
            if(id!=-1){
                selection+="AND id=?";
                arg = new String[]{load_id, id+""};
            }
            Cursor cursor = db.query(
                    Loads.perso.TABLE_NAME,
                    colunas,
                    selection,
                    arg,
                    null,
                    null,
                    "id DESC");
            while (cursor.moveToNext()){
                dados = new DadosTable();
                dados.setId(cursor.getInt(0));
                dados.setNome(cursor.getString(1));
                dados.setLevel(cursor.getInt(2));
                dados.setExperiencia(cursor.getInt(3));
                dados.setLoadId(cursor.getInt(4));
                dados.setClasse(cursor.getString(5));
                dados.setRank(cursor.getString(6));
                dados.setRankExp(cursor.getInt(7));
                dados.setVida(cursor.getInt(8));
                dados.setVidaMax(cursor.getInt(9));
                dados.setMp(cursor.getInt(10));
                dados.setMpMax(cursor.getInt(11));
                dados.setAtk(cursor.getInt(12));
                dados.setDef(cursor.getInt(13));
                dados.setAgi(cursor.getInt(14));
                dados.setAtkM(cursor.getInt(15));
                dados.setDefM(cursor.getInt(16));
                dados.setPontosExp(cursor.getInt(17));
                dados.setVit(cursor.getInt(18));
                dados.setIntl(cursor.getInt(19));


                listaDeDados.add(dados);
            }
            return listaDeDados;
        }

        public void atulizarDados(SQLiteDatabase db,DadosTable dados,int loadId,int id){
            System.out.println(dados);
            System.out.println(dados.getRank());
            String where = "load_id="+loadId+" AND id="+id;
            ContentValues values = new ContentValues();
            values.put("nome",dados.getNome());
            values.put("level",dados.getLevel());
            values.put("experiencia",dados.getExperiencia());
            values.put("pontosExp",dados.getPontosExp());
            values.put("rank",dados.getRank());
            values.put("rankExp",dados.getRankExp());
            values.put("vida",dados.getVida());
            values.put("vidaMax",dados.getVidaMax());
            values.put("mp",dados.getMp());
            values.put("mpMax",dados.getMpMax());
            values.put("atk",dados.getAtk());
            values.put("atkM",dados.getAtkM());
            values.put("def",dados.getDef());
            values.put("defM",dados.getDefM());
            values.put("agi",dados.getAgi());
            values.put("vit",dados.getVit());
            values.put("inteli",dados.getIntl());

            db.update(Loads.perso.TABLE_NAME,values,where,null);
        }

        public void atulizarLoad(SQLiteDatabase db,LoadTable load){

            String where = "id="+load.getId();
            ContentValues values = new ContentValues();
            values.put("nome", load.getNome());
            values.put("tempo", load.getTempo());
            db.update(Loads.load.TABLE_NAME,values,where,null);

        }
    }

}
