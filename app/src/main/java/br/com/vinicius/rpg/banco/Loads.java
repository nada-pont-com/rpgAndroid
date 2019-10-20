package br.com.vinicius.rpg.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.vinicius.rpg.objetosTabelas.DadosTable;
import br.com.vinicius.rpg.objetosTabelas.HabilidadesPersoTable;
import br.com.vinicius.rpg.objetosTabelas.HabilidadesTable;
import br.com.vinicius.rpg.objetosTabelas.ItensTable;
import br.com.vinicius.rpg.objetosTabelas.LoadTable;

//Todos os comando do banco de dados e tabelas

public final class Loads {
    private Loads(){}//TODO criar um tabela para os itens equipados no personagem! Tabela multiplas para os itens.

    public static class load implements BaseColumns{
        public static final String TABLE_NAME = "load";
        public static final String SQL_CREATE_LOADS = " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome VARCHAR(45) NOT NULL UNIQUE," +
                "tempo VARCHAR(255) NOT NULL" +
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
        public static List<DadosTable> SQL_LIST_DADOS = null;
        public static void dados(){
            SQL_LIST_DADOS = new ArrayList<DadosTable>();
            String classe[] = {"Guerreiro","Explorador"};
            String atk[] =  {"10"       ,"7"};
            String def[] =  {"10"       ,"6"};
            String agi[] =  {"5"        ,"9"};
            String atkM[] = {"2"        ,"5"};
            String defM[] = {"2"        ,"6"};
            String vit[] =  {"0"        ,"0"};
            String intl[] = {"0"        ,"0"};

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
    public static class misseosLoad implements BaseColumns{
        public static final String TABLE_NAME = "missoes_load";
        public static final String SQL_CREATE_MISSOES_LOAD = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (id INT UNSIGNED NOT NULL," +
                "load_id INT UNSIGNED NOT NULL," +
                "tipo TINYINT UNSIGNED NOT NULL," +
                "quant INT UNSIGNED NOT NULL)";
    }
    public static class itensPerso implements BaseColumns{
        public static final String TABLE_NAME = "itens_perso";
        public static final String SQL_CREATE_ITENS_PERSO = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (nome VARCHAR(40) NOT NULL ," +
                "id INT UNSIGNED NOT NULL" +
                "load_id INT UNSIGNED NOT NULL," +
                "quantidade INT UNSIGNED NOT NULL," +
                "FOREIGN KEY (load_id) " +
                "PRIMARY KEY (id,load_id)" +
                ")";
        public static final List<ItensTable> SQL_LIST_ITENS = new ArrayList<ItensTable>();

        //TODO criar uma classe separada, e comparar os valores quando o jogador ganhar os itens presentes nos monstros e nas quests(fazer uma lista lá nas quests)
        public static void Itens(){

            String[] nome = {"Gosma","Osso","pocaoHP","pocaoMP","Couro","","","","","","",""};
            String[] estatistica = {"","","HP:100","MP:10","","","","","","","",""};
            ItensTable itensTable;
            for (int i = 0;i<nome.length;i++){
                if(!nome[i].equals("")){
                    itensTable = new ItensTable();
                    itensTable.setNome(nome[i]);
                    if(!estatistica[i].equals("")){
                        itensTable.Estatisticas(estatistica[i]);
                    }
                    SQL_LIST_ITENS.add(itensTable);
                }
            }
        }
    }
    public static class habilidades implements BaseColumns{
        public static final String TABLE_NAME = "habilidades";
        public static final String SQL_CREATE_HABILIDADES= "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (" +
                "id INT UNSIGNED NOT NULL," +
                "nome VARCHAR(100) NOT NULL," +
                "tipo CHAR(1) NOT NULL," +
                "valor INT UNSIGNED NOT NULL," +
                "nunberAtk INT UNSIGNED NOT NULL," +
                "aumento INT UNSIGNED NOT NULL," +
                "nocalte INT UNSIGNED NOT NULL," +
                "extra VARCHAR(20)," +
                "pontos INT UNSIGNED NOT NULL," +
                "descricao VARCHAR(255) NOT NULL," +
                "PRIMARY KEY (id))";
        public static void Habilidades(SQLiteDatabase db){
            String[] nome = {"Ataque Forte","Nocaltear","Consentrasão","Ataque UP"};
            String[] tipo = {"1",           "1",        "2",           "2"};
            String[] valor = {"50",          "25",       "10",          "10"};
            String[] nunberAtk = {"1",      "1",        "0",           "0"};
            String[] aumento = {"3",        "1",        "2",           "2"};
            String[] nocalte = {"5",        "55",       "0",           "0"};
            String[] extra = {"",           "",         "def,agi,defM","atk"};
            String[] pontos = {"1",         "5",        "12",          "8"};
            String[] descricao = {"Um ataque forte","Um ataque que tem chance de nocaltear","Aumanta a concentração do personagem","Aumanta o ataque do personagem no proximo ataque"};
            ContentValues values = new ContentValues();
            for(int i = 0;i<nome.length;i++){
                values.put("id",i+1);
                values.put("nome",nome[i]);
                values.put("tipo",tipo[i]);
                values.put("valor",Integer.parseInt(valor[i]));
                values.put("nunberAtk",Integer.parseInt(nunberAtk[i]));
                values.put("aumento",Integer.parseInt(aumento[i]));
                values.put("nocalte",Integer.parseInt(nocalte[i]));
                values.put("extra",extra[i]);
                values.put("pontos",Integer.parseInt(pontos[i]));
                values.put("descricao",descricao[i]);
                db.insert(Loads.habilidades.TABLE_NAME, null, values);
            }
            //db.close();
        }
    }
    public static class perso_tem_habilidades implements BaseColumns{

        public static final String TABLE_NAME = "perso_tem_habilidades";//não é necessario salvar as habilidades.
        public static final String SQL_CREATE_PERSO_TEM_HABILIDADES= "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (" +
                "habilidades_id INT UNSIGNED NOT NULL," +
                "perso_id INT UNSIGNED NOT NULL," +
                "level INT UNSIGNED  NOT NULL," +
                "FOREIGN KEY (perso_id) " +
                "REFERENCES perso (id)," +
                "FOREIGN KEY (habilidades_id) " +
                "REFERENCES habilidades (id)," +
                "PRIMARY KEY (habilidades_id,perso_id))";
        //static final List<HabilidadesTable> SQL_LIST_HABILIDADES = new ArrayList<HabilidadesTable>();
    }
    public static class comandos{

        public boolean Inserir(LoadTable load, Context context){
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

        public boolean InserirDados(DadosTable dados,SQLiteDatabase db) {
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
            return newRowId != -1;
        }

        public void InserirHabilidadePerso(int habilidadeId, int perso_id, SQLiteDatabase db){

            ContentValues values = new ContentValues();
            values.put("habilidades_id",habilidadeId);
            values.put("perso_id",perso_id);
            values.put("level",0);
            db.insert(perso_tem_habilidades.TABLE_NAME, null, values);
        }

        public List<LoadTable> buscaLoad(SQLiteDatabase db){
            String[] colunas = {"id",
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

        public List<DadosTable> buscaDados(SQLiteDatabase db){
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
                dados.setPontosHab(cursor.getInt(18));
                dados.setVit(cursor.getInt(19));
                dados.setIntl(cursor.getInt(20));

                listaDeDados.add(dados);
            }
            return listaDeDados;
        }

        public List<DadosTable> buscaDadosPorLoadId(SQLiteDatabase db, int loadId, int id){
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
                dados.setPontosHab(cursor.getInt(18));
                dados.setVit(cursor.getInt(19));
                dados.setIntl(cursor.getInt(20));


                listaDeDados.add(dados);
            }
            return listaDeDados;
        }

        public List<HabilidadesPersoTable> buscaHabilidadesDoPerso(SQLiteDatabase db, int idHab, int perso_id){
            String[] colunas = {"habilidades_id","perso_id","level"};
            String selection = "perso_id=?";
            String[] arg;
            if(idHab>0){
                selection ="habilidades_id=? AND "+selection;
                arg = new String[]{idHab + "", perso_id + ""};
            }else{
                arg = new String[]{perso_id + ""};
            }
            System.out.print(Arrays.toString(arg));
            Cursor cursor = db.query(
                    Loads.perso_tem_habilidades.TABLE_NAME,
                    colunas,
                    selection,
                    arg,
                    null,
                    null,
                    "habilidades_id DESC");
            List<HabilidadesPersoTable> listHabilidades = new ArrayList<>();
            HabilidadesPersoTable habilidades;
            while (cursor.moveToNext()){
                habilidades = new HabilidadesPersoTable();
                habilidades.setHabilidadesId(cursor.getInt(0));
                habilidades.setPersoId(cursor.getInt(1));
                habilidades.setLevel(cursor.getInt(2));

                listHabilidades.add(habilidades);
            }
            return listHabilidades;
        }
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

        public void atulizarDados(SQLiteDatabase db,DadosTable dados,int loadId,int id){
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
        }

        public void atulizarLoad(SQLiteDatabase db,LoadTable load){

            String where = "id="+load.getId();
            ContentValues values = new ContentValues();
            values.put("nome", load.getNome());
            values.put("tempo", load.getTempo());
            db.update(Loads.load.TABLE_NAME,values,where,null);

        }

        public void deletaLoad(SQLiteDatabase db,LoadTable load){
            String where = "id="+load.getId();
            db.delete(Loads.load.TABLE_NAME,where,null);
        }

        public boolean deletaSave(SQLiteDatabase db,long id){

            String where2 = "load_id="+id;
            String where = "id="+id;
            List<DadosTable> listaDeDados= buscaDadosPorLoadId(db,(int)id,-1);
            for (int i = 0;i<listaDeDados.size();i++){
                int idPerso = listaDeDados.get(i).getId();
                String comando = "perso_id="+idPerso;
                db.delete(Loads.itensPerso.TABLE_NAME,comando,null);
                db.delete(Loads.perso_tem_habilidades.TABLE_NAME,comando,null);
            }
            db.delete(Loads.perso.TABLE_NAME,where2,null);
            db.delete(Loads.load.TABLE_NAME,where,null);
            return false;
        }
    }

}
