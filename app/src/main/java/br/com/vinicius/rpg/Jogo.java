package br.com.vinicius.rpg;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;
import java.util.Timer;

public class Jogo extends AppCompatActivity{

    private ImageView home;
    private ImageView battle;
    private ImageView config;
    private ListView perso;
    private Button voltar;
    private Button voltar2;
    private Button dungeon;
    private Button guild;
    private Timer timer;
    private Tempo.tempo tempo;
    private Tempo.autoSalve autoSalve;
    private ListView dungeons;
    private LoadTable load;
    private Dungeons dungeonsClass = new Dungeons();

    @Override
    public void onBackPressed() {
        // não chame o super desse método //Impede o botão de voltar do celular voltar para Activy anterior.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        if(Tempo.timer==null){
            Tempo.setTimer(new Timer());
        }
        timer = Tempo.timer;
        System.out.println("TIMER ---------------------------------------------");
        System.out.println(timer);

        perso = findViewById(R.id.perso);
        home =  findViewById(R.id.Inicio);
        config =  findViewById(R.id.Config);
        battle =  findViewById(R.id.Battle);
        dungeon = findViewById(R.id.Dungeon);
        guild = findViewById(R.id.Guild);
        voltar = findViewById(R.id.Voltar);
        voltar2 = findViewById(R.id.Voltar2);
        dungeons = findViewById(R.id.dungeons);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                Tempo.setTimer(null);
                tempo.setONOFF(false);
                autoSalve.setONOFF(false);
                Sessao.setNull();
                Loads.comandos comandos = new Loads.comandos();
                Bd banco = new Bd(Jogo.this);
                SQLiteDatabase db = banco.getWritableDatabase();
                comandos.atulizarLoad(db,load);
                db.close();
                Intent it = new Intent(Jogo.this,MainActivity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(it);
            }
        });
        guild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Jogo.this,Guilda.class);
                startActivity(it);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisibleInvisible(1);
            }
        });
        battle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisibleInvisible(2);
            }
        });
        final int[] dungeonT = {0};
        dungeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisibleInvisibleDungeon(2);
                dungeonT[0] = 1;
            }
        });

        voltar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dungeonT[0] == 0){
                    VisibleInvisible(1);
                }else{
                    dungeonT[0] = 0;
                    VisibleInvisibleDungeon(1);
                }
            }
        });

        dungeons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(Jogo.this,Dungeon.class);
                DungeonTable dungeonTable = dungeonsClass.listaDeDungeons.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("nomeDungeon", dungeonTable.getNome());
                bundle.putString("rank",dungeonTable.getRank());
                bundle.putString("andares",dungeonTable.getAndares());
                it.putExtras(bundle);
                startActivity(it);
            }
        });
        List<DadosTable> dados;
        load = Sessao.getLoad();
        if(Sessao.getDadosPerso()==null){
            Bd banco = new Bd(this);
            SQLiteDatabase db = banco.getWritableDatabase();
            Loads.comandos comandos = new Loads.comandos();
            dados = comandos.buscaDadosPorLoadId(db,load.getId(),-1);
            Sessao.setDadosPerso(dados);
            Sessao.HabilidadesPersoDados(db);
            db.close();
        }else{
            dados = Sessao.getDadosPerso();
        }
        dungeonsClass.list();
        AdapterDungeonsPersonalizado adpter = new AdapterDungeonsPersonalizado(dungeonsClass.listaDeDungeons,this);
        dungeons.setAdapter(adpter);

        System.out.println("Dados Jogo: "+dados);
        AdapterPersoPersonalizado adapter = new AdapterPersoPersonalizado(dados,this);
        perso.setAdapter(adapter);
        perso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(Jogo.this,PersonagemMenu.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                it.putExtras(bundle);
                startActivity(it);
            }
        });
        if(Sessao.getAutoSalve()==null){
            autoSalve = new Tempo.autoSalve(load,Jogo.this);
        }else{
            autoSalve = Sessao.getAutoSalve();
        }
        if(Sessao.getTempo()==null){
            tempo = new Tempo.tempo(load);
        }else{
            tempo = Sessao.getTempo();
        }
        System.out.println("+++++++++++++++++++++");
        System.out.println(tempo.getONOFF());
        System.out.println(autoSalve.getONOFF());
        if(!tempo.getONOFF()){
            timer.schedule(tempo,0,1000);
            tempo.setONOFF(true);
            Sessao.setTempo(tempo);
        }
        if(!autoSalve.getONOFF()){
            timer.schedule(autoSalve,0,10000);
            autoSalve.setONOFF(true);
            Sessao.setAutoSalve(autoSalve);
        }
    }

    private void VisibleInvisible(int referencia){
        LinearLayout home = findViewById(R.id.Home);
        LinearLayout battle = findViewById(R.id.batalha);
        //LinearLayout config = findViewById(R.id.);
        switch (referencia){
            case 1:
                home.setVisibility(View.VISIBLE);
                battle.setVisibility(View.INVISIBLE);
                //config.setVisibility(View.INVISIBLE);
                break;
            case 2:
                home.setVisibility(View.INVISIBLE);
                battle.setVisibility(View.VISIBLE);
                //config.setVisibility(View.INVISIBLE);
                break;
            case 3:
                home.setVisibility(View.INVISIBLE);
                battle.setVisibility(View.INVISIBLE);
                //config.setVisibility(View.VISIBLE);
                break;
        }
    }
    private void VisibleInvisibleDungeon(int referencia){
        LinearLayout dungeon = findViewById(R.id.LayoutDungeon);
        LinearLayout dungeons = findViewById(R.id.LayoutDungeons);

        switch (referencia){
            case 1:
                dungeon.setVisibility(View.VISIBLE);
                dungeons.setVisibility(View.INVISIBLE);
                break;
            case 2:
                dungeon.setVisibility(View.INVISIBLE);
                dungeons.setVisibility(View.VISIBLE);
                break;
        }
    }
}
