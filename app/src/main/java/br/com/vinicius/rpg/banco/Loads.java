package br.com.vinicius.rpg.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.vinicius.rpg.objetosTabelas.PersoTable;
import br.com.vinicius.rpg.objetosTabelas.DungeonTable;
import br.com.vinicius.rpg.objetosTabelas.HabilidadesPersoTable;
import br.com.vinicius.rpg.objetosTabelas.LoadTable;
import br.com.vinicius.rpg.objetosTabelas.MissoesTable;

//Todos os comando do banco de dados e tabelas

public final class Loads {
    private Loads(){}//TODO criar um tabela para os itens equipados no personagem! Tabela multiplas para os itens.

    public static class load implements BaseColumns{
        public static final String TABLE_NAME = "load";
        public static final String SQL_CREATE_LOADS = " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome VARCHAR(45) NOT NULL UNIQUE," +
                "tempo VARCHAR(255) NOT NULL," +
                "cobre INT UNSIGNED NOT NULL," +
                "prata INT UNSIGNED NOT NULL," +
                "ouro INT UNSIGNED NOT NULL" +
                ")";
    }
    public static class perso implements BaseColumns{
        public static final String TABLE_NAME = "perso";
        public static final String SQL_CREATE_PERSO = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (id INTEGER NOT NULL," +
                "nome VARCHAR(40) NOT NULL," +
                "level INT UNSIGNED NOT NULL," +
                "experiencia INT UNSIGNED NOT NULL," +
                "pontosExp INT UNSIGNED NOT NULL," +
                "pontosHab INT UNSIGNED NOT NULL," +
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
        public static List<PersoTable> SQL_LIST_DADOS = null;
        public static void dados(){
            SQL_LIST_DADOS = new ArrayList<PersoTable>();
            String classe[] = {"Guerreiro","Explorador"};
            String atk[] =  {"10"       ,"7"};
            String def[] =  {"10"       ,"6"};
            String agi[] =  {"5"        ,"9"};
            String atkM[] = {"2"        ,"5"};
            String defM[] = {"2"        ,"6"};
            String vit[] =  {"0"        ,"0"};
            String intl[] = {"0"        ,"0"};

            PersoTable dado;
            for (int i=0;i<classe.length;i++){
                dado = new PersoTable();
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
    public static class misseosLoad implements BaseColumns{
        public static final String TABLE_NAME = "missoes_load";
        public static final String SQL_CREATE_MISSOES_LOAD = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (id INT UNSIGNED NOT NULL," +
                "load_id INT UNSIGNED NOT NULL," +
                "itens_id INT UNSIGNED," +
                "monstro_id INT UNSIGNED," +
                "rank CHAR," +
                "dungeon_nome CHAR," +
                "tipo TINYINT UNSIGNED NOT NULL," +
                "quant INT UNSIGNED)";
    }
    public static class itensLoad implements BaseColumns{
        public static final String TABLE_NAME = "itens_perso";
        public static final String SQL_CREATE_ITENS_PERSO = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (" +
                "id INT NOT NULL," + // Id referente a posição do item na lista;
                "load_id INT UNSIGNED NOT NULL," +
                "quantidade INT UNSIGNED NOT NULL," +
                "FOREIGN KEY (load_id)" +
                "REFERENCES load (id), " +
                "PRIMARY KEY (id,load_id)" +
                ")";
    }
    public static class perso_tem_habilidades implements BaseColumns{

        public static final String TABLE_NAME = "perso_tem_habilidades";//não é necessario salvar as habilidades.
        public static final String SQL_CREATE_PERSO_TEM_HABILIDADES= "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (" +
                "id INT UNSIGNED NOT NULL," +
                "perso_id INT UNSIGNED NOT NULL," +
                "level INT UNSIGNED  NOT NULL," +
                "FOREIGN KEY (perso_id) " +
                "REFERENCES perso (id)," +
                "PRIMARY KEY (id,perso_id))";
        //static final List<HabilidadesTable> SQL_LIST_HABILIDADES = new ArrayList<HabilidadesTable>();
    }
    public static class dungions_tem_loads implements BaseColumns{
        public static final String TABLE_NAME = "dungeons_tem_loads";
        public static final String SQL_CREATE_DUNGEONS_TEM_LOADS = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nome VARCHAR(20) NOT NULL," +
                "andares VARCHAR(7) NOT NULL," +
                "load_id INT UNSIGNED NOT NULL," +
                "rank CHAR(1) NOT NULL," +
                "FOREIGN KEY (load_id)" +
                "REFERENCES load (id)" +
                ")";
    }

    public static class comandos{

        public boolean Inserir(LoadTable load, Context context){
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id", load.getId());
            values.put("nome", load.getNome());
            values.put("tempo", load.getTempo());
            System.out.print(load.getPrata());
            values.put("cobre", load.getCobre());
            values.put("prata", load.getPrata());
            values.put("ouro", load.getOuro());
            long newRowId = db.insert(Loads.load.TABLE_NAME, null, values);
            banco.close();
            return newRowId != -1;
        }

        public boolean InserirDados(PersoTable dados, Context context) {
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id", dados.getId());
            values.put("nome",dados.getNome());
            values.put("level",dados.getLevel());
            values.put("experiencia",dados.getExperiencia());
            values.put("pontosExp",0);
            values.put("pontosHab",0);
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
            db.close();
            return newRowId != -1;
        }

        public void InserirHabilidadePerso(int id, int perso_id, Context context){
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id",id);
            values.put("perso_id",perso_id);
            values.put("level",0);
            db.insert(perso_tem_habilidades.TABLE_NAME, null, values);
            db.close();
        }

        public void InserirDungeon(int loadId,DungeonTable dungeon, Context context){
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("load_id", loadId);
            values.put("nome", dungeon.getNome());
            values.put("andares", dungeon.getAndares());
            values.put("rank", dungeon.getRank());
            db.insert(Loads.dungions_tem_loads.TABLE_NAME, null, values);
            banco.close();
        }

        public void NewItemLoad(Context context,int load_id,int id,int quant){
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            String[] colunas = {"quantidade"};
            String[] args = { load_id+"", id+""};

            Cursor cursor = db.query(
                itensLoad.TABLE_NAME,
                colunas,
                "load_id=? AND id=?",
                args,
                null,
                null,
                null);
            boolean valida = false;
            if(cursor.moveToNext()){
                quant += cursor.getInt(0);
                valida = true;
            }
            if (valida) {
                AtualizaItens(context, load_id, id, quant);
            } else {
                InserirItensLoad(context, load_id, id, quant);
            }
            db.close();
        }

        public boolean InserirItensLoad(Context context, int load_id,int id,int quant){
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id",id);
            values.put("load_id",load_id);
            values.put("quantidade",quant);
            long newRowId = db.insert(Loads.itensLoad.TABLE_NAME, null, values);
            db.close();
            return newRowId != 1;
        }

        public boolean AtualizaItens(Context context, int load_id,int id,int quant){
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            String where = "id="+id+" AND load_id="+load_id;
            ContentValues values = new ContentValues();
            values.put("quantidade", quant);
            boolean retorno =  (1 == db.update(Loads.itensLoad.TABLE_NAME,values,where,null));
            db.close();
            return retorno;

        }

        public List<LoadTable> buscaLoad(Context context){
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            String[] colunas = {"id",
                    "nome",
                    "tempo",
                    "cobre",
                    "prata",
                    "ouro"
                    };
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
                load.setMoeda(cursor.getInt(3),cursor.getInt(4),cursor.getInt(5));
                listaDeLoads.add(load);
            }

            db.close();
            return listaDeLoads;
        }

        public List<PersoTable> buscaDados(Context context){
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            List<PersoTable> listaDeDados = new ArrayList<>();
            PersoTable dados;
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
                    "pontosHab",
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
                dados = new PersoTable();
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
                dados.setPontosHab(cursor.getInt(18));
                dados.setVit(cursor.getInt(19));
                dados.setIntl(cursor.getInt(20));

                listaDeDados.add(dados);
            }
            db.close();
            return listaDeDados;
        }

        public List<PersoTable> buscaDadosPorLoadId(Context context, int loadId, int id){
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            PersoTable dados;
            List<PersoTable> listaDeDados = new ArrayList<>();
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
                    "pontosHab",
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
                dados = new PersoTable();
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
                dados.setPontosHab(cursor.getInt(18));
                dados.setVit(cursor.getInt(19));
                dados.setIntl(cursor.getInt(20));


                listaDeDados.add(dados);
            }
            db.close();
            return listaDeDados;
        }

        public List<HabilidadesPersoTable> buscaHabilidadesDoPerso(Context context, int id, int perso_id){
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            String[] colunas = {"id","perso_id","level"};
            String selection = "perso_id=?";
            String[] arg;
            if(id>0){
                selection ="id=? AND "+selection;
                arg = new String[]{id + "", perso_id + ""};
            }else{
                arg = new String[]{perso_id + ""};
            }
//            System.out.print(Arrays.toString(arg));
            Cursor cursor = db.query(
                    Loads.perso_tem_habilidades.TABLE_NAME,
                    colunas,
                    selection,
                    arg,
                    null,
                    null,
                    "id DESC");
            List<HabilidadesPersoTable> listHabilidades = new ArrayList<>();
            HabilidadesPersoTable habilidades;
            while (cursor.moveToNext()){
                habilidades = new HabilidadesPersoTable();
                habilidades.setHabilidadesId(cursor.getInt(0));
                habilidades.setPersoId(cursor.getInt(1));
                habilidades.setLevel(cursor.getInt(2));

                listHabilidades.add(habilidades);
            }
            db.close();
            return listHabilidades;
        }

        public List<DungeonTable> buscaDungeons(Context context,int load_id){
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            String[] colunas = {"andares",
                    "nome",
                    "rank"};
            String selection = "load_id="+load_id;
            Cursor cursor = db.query(
                    Loads.dungions_tem_loads.TABLE_NAME,
                    colunas,
                    selection,
                    null,
                    null,
                    null,
                    null);
            List<DungeonTable> listaDeDungeons = new ArrayList<>();
            DungeonTable dungeons;
            while (cursor.moveToNext()){
                dungeons = new DungeonTable();
                dungeons.setAndares(cursor.getString(0));
                dungeons.setNome(cursor.getString(1));
                dungeons.setRank(cursor.getString(2));
                listaDeDungeons.add(dungeons);
            }
            db.close();
            return listaDeDungeons;
        }

        public List<MissoesTable> buscaMissoes(Context context,int load_id){
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            System.out.println("Load:"+load_id);
            String selection = "load_id="+load_id;
            Cursor cursor = db.query(
                    Loads.misseosLoad.TABLE_NAME,
                    null,
                    selection,
                    null,
                    null,
                    null,
                    null);
            List<MissoesTable> listaDeMissoes = new ArrayList<>();
            MissoesTable missao;
            while (cursor.moveToNext()){
                missao = new MissoesTable();
                missao.setTipo(cursor.getInt(6));
                missao.setQuant(cursor.getInt(7));
                missao.setDados(cursor.getInt(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5));
                listaDeMissoes.add(missao);
            }
            db.close();
            return listaDeMissoes;
        }
        /*
        public List<HabilidadesTable> buscaHabilidades(SQLiteDatabase db){
            //String[] colunas = {"id","nome","tipo","valor","nuberAtk","aumento","nocalte","extra","pontos","descricao"};
            String[] colunas = {"*"};
            Cursor cursor = db.query(
                    Loads.habilidades.TABLE_NAME,
                    colunas,
                    null,
                    null,
                    null,
                    null,
                    "id ASC");
            List<HabilidadesTable> listHabilidades = new ArrayList<>();
            HabilidadesTable habilidades;
            while(cursor.moveToNext()){
                habilidades = new HabilidadesTable();
                habilidades.setId(cursor.getInt(0));
                habilidades.setNome(cursor.getString(1));
                habilidades.setTipo(cursor.getString(2));
                habilidades.setValor(cursor.getInt(3));
                habilidades.setNuberAtk(cursor.getInt(4));
                habilidades.setAumento(cursor.getInt(5));
                habilidades.setNocalte(cursor.getInt(6));
                habilidades.setExtra(cursor.getString(7));
                habilidades.setPontos(cursor.getInt(8));
                habilidades.descricao(cursor.getString(9));
                listHabilidades.add(habilidades);
            }
            return listHabilidades;
        }
        */
        public void atulizarDados(Context context, PersoTable dados, int loadId, int id){
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            String where = "load_id="+loadId+" AND id="+id;
            ContentValues values = new ContentValues();
            values.put("nome",dados.getNome());
            values.put("level",dados.getLevel());
            values.put("experiencia",dados.getExperiencia());
            values.put("pontosExp",dados.getPontosExp());
            values.put("pontosHab",dados.getPontosHab());
            values.put("rank",dados.getRank());
            values.put("rankExp",dados.getRankExp());
            values.put("vida",dados.getVida());
            values.put("vidaMax",dados.getVidaM());
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
            db.close();
        }

        public void atulizarLoad(Context context,LoadTable load){
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            String where = "id="+load.getId();
            ContentValues values = new ContentValues();
            values.put("nome", load.getNome());
            values.put("tempo", load.getTempo());
            db.update(Loads.load.TABLE_NAME,values,where,null);
            db.close();
        }

        public void deletaLoad(Context context,LoadTable load){
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            String where = "id="+load.getId();
            db.delete(Loads.load.TABLE_NAME,where,null);
            db.close();

        }

        public boolean deletaSave(Context context,long id){
            Bd banco = new Bd(context);
            SQLiteDatabase db = banco.getWritableDatabase();
            String where2 = "load_id="+id;
            String where = "id="+id;
            List<PersoTable> listaDeDados= buscaDadosPorLoadId(context,(int)id,-1);
            for (int i = 0;i<listaDeDados.size();i++){
                int idPerso = listaDeDados.get(i).getId();
                String comando2 = "perso_id="+idPerso;
                db.delete(Loads.perso_tem_habilidades.TABLE_NAME,comando2,null);
            }
            db.delete(Loads.itensLoad.TABLE_NAME,where2,null);
            db.delete(Loads.perso.TABLE_NAME,where2,null);
            db.delete(Loads.load.TABLE_NAME,where,null);
            db.close();
            return false;
        }
    }

}