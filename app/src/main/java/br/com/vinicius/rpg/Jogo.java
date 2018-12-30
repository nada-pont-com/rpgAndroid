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
import android.widget.TextView;

import java.util.List;
import java.util.Timer;

public class Jogo extends AppCompatActivity{

    private TextView andarFlo;
    private ImageView home;
    private ImageView battle;
    private ImageView config;
    private ListView perso;
    private Button voltar;
    private Button voltar2;
    private Button dungeon;
    private Button guild;
    private Button floresta;

    private LoadTable load;

    @Override
    public void onBackPressed() {
        // não chame o super desse método //Impede o botão de voltar do celular voltar para Activy anterior.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        andarFlo = findViewById(R.id.AndaresFlores);
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
                bundle.putString("andares",andarFlo.getText().toString().split("Andares: ")[1]);

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
        }else{
            dados = Sessao.getDadosPerso();
        }
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

        Timer timer = new Timer();
        Tempo tempo = new Tempo(load);
        AutoSalve autoSalve = new AutoSalve(load,Jogo.this);
        timer.schedule(tempo,0,1000);
        timer.schedule(autoSalve,0,10000);
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
