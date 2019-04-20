package br.com.vinicius.rpg;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

//Menu de salves
public class Continuar extends AppCompatActivity {

    private Button voltar;
    private Button deletar;
    private ListView Salves;
    private TextView Mensagem;
    private Boolean Deletar = false;
    private List<LoadTable> listaDeLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuar);
        Mensagem = findViewById(R.id.Mensagem);
        Salves = findViewById(R.id.Salves);
        Salves.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bd banco = new Bd(Continuar.this);
                SQLiteDatabase db = banco.getReadableDatabase();
                if(Deletar){
                    String where2 = "load_id="+id;
                    String where = "id="+id;
                    System.out.println(id);
                    db.delete(Loads.perso.TABLE_NAME,where2,null);
                    db.delete(Loads.load.TABLE_NAME,where,null);
                    Loads.comandos comandos = new Loads.comandos();
                    listaDeLoad = comandos.buscaLoad(db);
                    //lista();
                }else{
                    Loads.comandos comandos = new Loads.comandos();
                    List<LoadTable> listaDeLoad = comandos.buscaLoad(db);
                    Sessao.setHabilidades(comandos.buscaHabilidades(db));
                    db.close();
                    System.out.println(position);
                    Sessao.setLoad(listaDeLoad.get(position));
                    Intent it =  new Intent(Continuar.this,Jogo.class);
                    startActivity(it);
                }
            }
        });

        voltar = findViewById(R.id.Voltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Deletar){
                    Mensagem.setVisibility(View.INVISIBLE);
                    Deletar = false;
                }else{
                    Intent it =  new Intent(Continuar.this,MainActivity.class);
                    startActivity(it);
                }
            }
        });

        deletar = findViewById(R.id.Deletar);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mensagem.setVisibility(View.VISIBLE);
                Deletar = true;
            }
        });
        lista();
    }
    private void lista(){
        Bd banco = new Bd(this);
        SQLiteDatabase db = banco.getWritableDatabase();
        Loads.comandos comandos = new Loads.comandos();
        listaDeLoad = comandos.buscaLoad(db);
        AdapterSalvesPersonalizado adapter = new AdapterSalvesPersonalizado(listaDeLoad,this);
        Salves.setAdapter(adapter);
        db.close();
    }
}
