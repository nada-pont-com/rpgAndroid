package br.com.vinicius.rpg.jogo.guilda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.vinicius.rpg.R;
import br.com.vinicius.rpg.jogo.inicio.Jogo;

public class Guilda extends AppCompatActivity { //TODO Fazer um explorador automático no jogo. Anda, encontra, luta e avança.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guilda);

        Button Voltar = findViewById(R.id.Voltar);
        Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Guilda.this, Jogo.class);
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(it);
            }
        });
    }
}
