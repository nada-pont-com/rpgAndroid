package br.com.vinicius.rpg.jogo.guilda;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import br.com.vinicius.rpg.R;
import br.com.vinicius.rpg.banco.Bd;
import br.com.vinicius.rpg.banco.Loads;
import br.com.vinicius.rpg.jogo.informacoes.Sessao;
import br.com.vinicius.rpg.jogo.inicio.Jogo;

public class Guilda extends AppCompatActivity { //TODO Fazer um explorador automático no jogo. Anda, encontra, luta e avança.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guilda);

        Button voltar = findViewById(R.id.Voltar);
        Button baucao = findViewById(R.id.Bauconista);
        Button grupoLayout = findViewById(R.id.Grupo);


        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Guilda.this, Jogo.class);
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(it);
            }
        });

        baucao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alteraLayout(3);
            }
        });

        grupoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alteraLayout(1);
            }
        });
        Loads.comandos comandos = new Loads.comandos();
        Bd banco = new Bd(this);
        SQLiteDatabase db = banco.getWritableDatabase();
        comandos.buscaMissoes(db, Sessao.getLoad().getId());
    }

    private void alteraLayout(int opcao){
        LinearLayout grupo = findViewById(R.id.grupo);
        LinearLayout bauconista = findViewById(R.id.LayoutBaucao);
        LinearLayout missoes = findViewById(R.id.LayoutMissoes);
        LinearLayout mercado = findViewById(R.id.LayoutMercado);

        switch (opcao){
            case 1:
                grupo.setVisibility(View.VISIBLE);
                bauconista.setVisibility(View.INVISIBLE);
                break;
            case 2:
                mercado.setVisibility(View.VISIBLE);
                bauconista.setVisibility(View.INVISIBLE);
                break;
            case 3:
                bauconista.setVisibility(View.VISIBLE);
                grupo.setVisibility(View.INVISIBLE);
                break;
            case 4:
                missoes.setVisibility(View.VISIBLE);
                grupo.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
