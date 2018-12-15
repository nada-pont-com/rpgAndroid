package br.com.vinicius.rpg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PersonagemMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personagem_menu);

        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        int position = bundle.getInt("position");
        DadosTable dado = Sessao.getDadosPerso().get(position);
    }
}
