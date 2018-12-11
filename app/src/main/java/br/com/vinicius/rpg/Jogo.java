package br.com.vinicius.rpg;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

public class Jogo extends AppCompatActivity {

    private ImageView home;
    private ImageView battle;
    private ImageView config;
    private ListView perso;
    private Button voltar;
    private Button voltar2;
    private Button dungeon;
    private Button guild;
    private Button floresta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        perso = findViewById(R.id.perso);
        home =  findViewById(R.id.Inicio);
        config =  findViewById(R.id.Config);
        battle =  findViewById(R.id.Battle);
        dungeon = findViewById(R.id.Dungeon);
        guild = findViewById(R.id.Guild);
        voltar = findViewById(R.id.Voltar);
        voltar2 = findViewById(R.id.Voltar2);
        floresta = findViewById(R.id.Floresta);
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

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dungeonT[0] == 0){
                    VisibleInvisible(1);
                }else{
                    VisibleInvisibleDungeon(1);
                }
            }
        });

        floresta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Jogo.this,Dungeon.class);
                Bundle bundle = new Bundle();
                bundle.putString("nomeDungeon", "floresta");
                bundle.putString("rank","G");
                it.putExtras(bundle);
                startActivity(it);
            }
        });

        Bd banco = new Bd(getBaseContext());
        SQLiteDatabase db = banco.getWritableDatabase();
        Loads.comandos comandos = new Loads.comandos();
        LoadTable load = Sessao.getLoad();
        List<DadosTable> dados = comandos.buscaDadosPorLoadId(db,load.getId(),-1);
        Sessao.setDadosPerso(dados);
        AdapterPersoPersonalizado adapter = new AdapterPersoPersonalizado(dados,this);
        perso.setAdapter(adapter);
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
