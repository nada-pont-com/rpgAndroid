package br.com.vinicius.rpg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class Battle extends AppCompatActivity {

    private String NomeDungeon;
    private String rank;
    private ListView Perso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        assert bundle != null;
        NomeDungeon = bundle.getString("nomeDungeon");
        rank = bundle.getString("rank");
        MonstroUni monstro  = (MonstroUni) bundle.getSerializable("monstro");
        //Buscar dados do jogador

        Perso = findViewById(R.id.listaDePerso);
        List<DadosTable> dados =  Sessao.getDadosPerso();
        AdapterBattlePersoPersonalizado adapter = new AdapterBattlePersoPersonalizado(dados,this);
        Perso.setAdapter(adapter);
    }
}
