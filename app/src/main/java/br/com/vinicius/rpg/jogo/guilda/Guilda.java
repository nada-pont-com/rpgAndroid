package br.com.vinicius.rpg.jogo.guilda;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.vinicius.rpg.R;
import br.com.vinicius.rpg.dados.Missao;
import br.com.vinicius.rpg.jogo.informacoes.Sessao;
import br.com.vinicius.rpg.jogo.inicio.Jogo;
import br.com.vinicius.rpg.objetosTabelas.MissoesTable;

public class Guilda extends AppCompatActivity { //TODO Fazer um explorador automático no jogo. Anda, encontra, luta e avança.

    private ListView missoesList;
    private  int valida = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guilda);
        System.out.println("guild");

        Button voltar = findViewById(R.id.Voltar);
        Button baucao = findViewById(R.id.Bauconista);
        Button missoesG = findViewById(R.id.MissoesG);

        Button grupo = findViewById(R.id.Grupo);
        Button missoesB = findViewById(R.id.MissoesB);
        Button mercado = findViewById(R.id.Mercado);

        missoesList = findViewById(R.id.MissoesList);



        missoesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final MissoesTable missao = Sessao.getListaDeMissoes().get(position);
                AlertDialog.Builder alert = new AlertDialog.Builder(Guilda.this);
                alert.setMessage("Acitar missão?");
                alert.setPositiveButton("Aceitar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        List<MissoesTable> listaDeMissoes = Sessao.getListaDeMissoesAceitas();
                        if(listaDeMissoes==null){
                            listaDeMissoes = new ArrayList<>();
                        }
                        listaDeMissoes.add(missao);
                        Sessao.setListaDeMissoesAceitas(listaDeMissoes);
                    }
                });
                alert.setNeutralButton("Voltar",null);
                alert.show();
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (valida){
                    case -1:
                        Intent it = new Intent(Guilda.this, Jogo.class);
                        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(it);
                        break;
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        alteraLayout(valida);
                        break;
                }
            }
        });

        grupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alteraLayout(1);
            }
        });

        mercado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alteraLayout(2);
            }
        });

        baucao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alteraLayout(5);
            }
        });

        missoesG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alteraLayout(3);
            }
        });

        missoesB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alteraLayout(4);
            }
        });
        /*
        Loads.comandos comandos = new Loads.comandos();
        Bd banco = new Bd(this);
        SQLiteDatabase db = banco.getWritableDatabase();
        comandos.buscaMissoes(db, Sessao.getLoad().getId());
        banco.close();
        */
        ListaMissoes();
    }

    private void alteraLayout(int opcao){
        LinearLayout grupo = findViewById(R.id.grupo);
        LinearLayout bauconista = findViewById(R.id.LayoutBaucao);
        LinearLayout missoes = findViewById(R.id.LayoutMissoes);
        LinearLayout mercado = findViewById(R.id.LayoutMercado);

        switch (opcao){
            case 1: //5
                grupo.setVisibility(View.VISIBLE);
                bauconista.setVisibility(View.INVISIBLE);
                valida = 5;
                break;
            case 2: //6
                mercado.setVisibility(View.VISIBLE);
                bauconista.setVisibility(View.INVISIBLE);
                valida = 6;
                break;
            case 3: //7
                missoes.setVisibility(View.VISIBLE);
                grupo.setVisibility(View.INVISIBLE);
                valida = 7;
                break;
            case 4://8
                missoes.setVisibility(View.VISIBLE);
                bauconista.setVisibility(View.INVISIBLE);
                valida = 8;
                break;
            case 5:
                bauconista.setVisibility(View.VISIBLE);
                grupo.setVisibility(View.INVISIBLE);
                valida = -1;
                break;
            case 6:
                bauconista.setVisibility(View.VISIBLE);
                mercado.setVisibility(View.INVISIBLE);
                valida = -1;
                break;
            case 7:
                grupo.setVisibility(View.VISIBLE);
                missoes.setVisibility(View.INVISIBLE);
                valida = 5;
                break;
            case 8:
                bauconista.setVisibility(View.VISIBLE);
                missoes.setVisibility(View.INVISIBLE);
                valida = -1;
                break;
        }
    }



    private void ListaMissoes() {
        List<MissoesTable> listaDeMissoes = new ArrayList<>();
        if(Sessao.getListaDeMissoes()==null){
            Missao missao  = new Missao();
            for (int i = 0;i<5;i++){
                System.out.println("test for"+i);
                MissoesTable missoesTable = missao.geraMissao();
                listaDeMissoes.add(missoesTable);
            }
            Sessao.setListaDeMissoes(listaDeMissoes);
        }else{
            listaDeMissoes = Sessao.getListaDeMissoes();
        }
        ArrayAdapter<MissoesTable> missoes = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listaDeMissoes);
        missoesList.setAdapter(missoes);
    }
}
