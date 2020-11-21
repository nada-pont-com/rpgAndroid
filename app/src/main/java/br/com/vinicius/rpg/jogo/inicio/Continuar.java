package br.com.vinicius.rpg.jogo.inicio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.vinicius.rpg.R;
import br.com.vinicius.rpg.jogo.informacoes.Sessao;
import br.com.vinicius.rpg.adapters.AdapterSalvesPersonalizado;
import br.com.vinicius.rpg.banco.Loads;
import br.com.vinicius.rpg.inicio.MainActivity;
import br.com.vinicius.rpg.objetosTabelas.LoadTable;

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
                if(Deletar){
                    Loads.comandos comandos = new Loads.comandos();
                    comandos.deletaSave(getBaseContext(),id);
                    System.out.println(id);

                    listaDeLoad = comandos.buscaLoad(getBaseContext());
                    lista();
                }else{
                    Loads.comandos comandos = new Loads.comandos();
                    List<LoadTable> listaDeLoad = comandos.buscaLoad(getBaseContext());
                    Sessao.setHabilidades();
                    System.out.println(position);
                    Sessao.setLoad(listaDeLoad.get(position));
                    Intent it =  new Intent(Continuar.this, Jogo.class);
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
                    Intent it =  new Intent(Continuar.this, MainActivity.class);
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
        Loads.comandos comandos = new Loads.comandos();
        listaDeLoad = comandos.buscaLoad(getBaseContext());
        AdapterSalvesPersonalizado adapter = new AdapterSalvesPersonalizado(listaDeLoad,this);
        Salves.setAdapter(adapter);
    }
}
