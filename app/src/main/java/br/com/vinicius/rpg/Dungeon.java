package br.com.vinicius.rpg;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.Random;

public class Dungeon extends AppCompatActivity {

    private Button frente;
    private Button direita;
    private Button esquerda;
    private Button sobe;
    private Button desce;
    private ListView listaDeAcoes;
    private Random gerador = new Random();
    private int andar;
    private String NomeDungeon;
    private String rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon);

        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        assert bundle != null;
        NomeDungeon = bundle.getString("nomeDungeon");
        rank = bundle.getString("rank");
        andar = 1;
        listaDeAcoes = findViewById(R.id.ListaAcoes);
        frente = findViewById(R.id.Frente);
        direita = findViewById(R.id.Direita);
        esquerda = findViewById(R.id.Esquerda);
        sobe = findViewById(R.id.Subir);
        desce = findViewById(R.id.Descer);
        frente.setOnClickListener(caminho());
        esquerda.setOnClickListener(caminho());
        direita.setOnClickListener(caminho());
        switch (gerador.nextInt(10)){
            case 0:
                //subir ou descer
                break;
        }
    }

    private View.OnClickListener caminho(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caminhos();
                monstro();
            }
        };
    }

    private void caminhos(){
        int teste = gerador.nextInt(6);
        System.out.println(teste);
        switch (teste){
            case 0://Frente
                frente.setClickable(true);
                frente.setAlpha(1);
                direita.setClickable(false);
                direita.setAlpha(0.2f);
                esquerda.setClickable(false);
                esquerda.setAlpha(0.2f);
                break;
            case 1://Direita
                frente.setClickable(false);
                frente.setAlpha(0.2f);
                direita.setClickable(true);
                direita.setAlpha(1);
                esquerda.setClickable(false);
                esquerda.setAlpha(0.2f);
                break;
            case 2://Esquerda
                frente.setClickable(false);
                frente.setAlpha(0.2f);
                direita.setClickable(false);
                direita.setAlpha(0.2f);
                esquerda.setClickable(true);
                esquerda.setAlpha(1);
                break;
            case 3://Frente/Direita
                frente.setClickable(true);
                frente.setAlpha(1);
                direita.setClickable(true);
                direita.setAlpha(1);
                esquerda.setClickable(false);
                esquerda.setAlpha(0.2f);
                break;
            case 4://Frente/Esquerda
                frente.setClickable(true);
                frente.setAlpha(1);
                direita.setClickable(false);
                direita.setAlpha(0.2f);
                esquerda.setClickable(true);
                esquerda.setAlpha(0.2f);
                break;
            case 5://Direita/Esquerda
                frente.setClickable(false);
                frente.setAlpha(0.2f);
                direita.setClickable(true);
                direita.setAlpha(1);
                esquerda.setClickable(true);
                esquerda.setAlpha(1);
                break;
        }
    }

    private void monstro(){
        switch (gerador.nextInt(10)){
            case 0:
                final Monstros monstros = Monstros.G;
                final MonstroUni monstro =  monstros.monstro();
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Aviso");
                alert.setMessage("Você encontrou um "+monstro.getNome());
                alert.setNeutralButton("Lutar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent it = new Intent(Dungeon.this, Battle.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("nomeDungeon", NomeDungeon);
                        bundle.putString("rank", rank);
                        bundle.putSerializable("teste", (Serializable) monstro);
                        it.putExtras(bundle);
                        startActivity(it);
                    }
                }).setNegativeButton("Fugir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.create();
                alert.show();
                break;
        }
    }
}
