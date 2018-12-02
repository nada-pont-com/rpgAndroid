package br.com.vinicius.rpg;

import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        perso = findViewById(R.id.perso);
        home =  findViewById(R.id.Inicio);
        config =  findViewById(R.id.Config);
        battle =  findViewById(R.id.Battle);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisibleInvisible(1);
            }
        });

        Bd banco = new Bd(getBaseContext());
        SQLiteDatabase db = banco.getWritableDatabase();
        Loads.comandos comandos = new Loads.comandos();
        List<DadosTable> dados = comandos.buscaDados(db);
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
}
