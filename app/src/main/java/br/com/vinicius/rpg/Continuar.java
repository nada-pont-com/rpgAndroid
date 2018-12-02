package br.com.vinicius.rpg;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class Continuar extends AppCompatActivity {

    private Button voltar;
    private ListView Salves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuar);

        Salves = (ListView) findViewById(R.id.Salves);
        Salves.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                System.out.println(item);
                String[] dados = item.toString().split(",");
                String[] loadId = dados[0].split(" ");
                String[] nome = dados[1].split(" ");
                String[] tempo = dados[2].split(" ");
                LoadTable load = new LoadTable();
                load.setId(Integer.parseInt(loadId[1]));
                load.setNome(nome[1]);
                load.setTempo(tempo[1]);
                Sessao.setLoad(load);
                Intent it =  new Intent(Continuar.this,Jogo.class);
                startActivity(it);
            }
        });
        voltar = (Button) findViewById(R.id.Voltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =  new Intent(Continuar.this,MainActivity.class);
                startActivity(it);
            }
        });
        Bd banco = new Bd(this);
        SQLiteDatabase db = banco.getWritableDatabase();
        Loads.comandos comandos = new Loads.comandos();
        List<LoadTable> listaDeLoad = comandos.buscaLoad(db);
        AdapterSalvesPersonalizado adapter = new AdapterSalvesPersonalizado(listaDeLoad,this);
        Salves.setAdapter(adapter);
    }
}
