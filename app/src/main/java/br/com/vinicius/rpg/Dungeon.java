package br.com.vinicius.rpg;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

//Dungeon
public class Dungeon extends AppCompatActivity {

    private Button frente;
    private Button direita;
    private Button esquerda;
    private Button sobe;
    private Button desce;
    private ListView listaDeAcoes;
    private Random gerador = new Random();
    private TextView Andar;
    private TextView MonstroRank;
    private int andar;
    private int andarMax;
    private String NomeDungeon;
    private String rank;
    private List<DadosTable> dados;

    @Override
    public void onBackPressed() {
        // não chame o super desse método //Impede o botão de voltar do celular voltar para Activy anterior.
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon);

        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        assert bundle != null;
        NomeDungeon = bundle.getString("nomeDungeon");
        rank = bundle.getString("rank");
        dados = Sessao.getDadosPerso();
        String[] andares = bundle.getString("andares").split("-");
        andarMax = Integer.parseInt(andares[1]);
        andar = Integer.parseInt(andares[0]);
        Andar = findViewById(R.id.Andar);
        MonstroRank = findViewById(R.id.MontrosRank);
        listaDeAcoes = findViewById(R.id.ListaAcoes);
        frente = findViewById(R.id.Frente);
        direita = findViewById(R.id.Direita);
        esquerda = findViewById(R.id.Esquerda);
        sobe = findViewById(R.id.Subir);
        desce = findViewById(R.id.Descer);

        frente.setClickable(true);
        direita.setClickable(false);
        direita.setAlpha(0.2f);
        esquerda.setClickable(false);
        esquerda.setAlpha(0.2f);
        desce.setClickable(false);
        desce.setAlpha(0.2f);
        frente.setOnClickListener(caminho());
        esquerda.setOnClickListener(caminho());
        direita.setOnClickListener(caminho());
        desce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                andar++;
                Andar.setText("Andar: "+andar);
            }
        });
        Andar.setText("Andar: "+andar);
        MonstroRank.setText("Montros Rank: "+rank);

    }

    private View.OnClickListener caminho(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caminhos();
                monstro();
                desceCaminho();
            }
        };
    }

    private void desceCaminho(){
        switch (gerador.nextInt(25)){
            case 0:
                sobe.setClickable(true);
                sobe.setAlpha(1);
                break;
        }
    }

    private void caminhos(){
        switch (gerador.nextInt(6)){
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
        RegHp();
    }

    private void RegHp(){
        for (int i =0;i<dados.size();i++){
            DadosTable dado = dados.get(i);
            int vida = dado.getVida();
            if (vida<dado.getVidaMax()){
                int reg = dado.getVidaMax()/100;
                System.out.println("Reg: "+reg);
                if((vida+reg)>dado.getVidaMax()){
                    dado.setVida(dado.getVidaMax());
                }else{
                    dado.setVida(vida+reg);
                }
            }
        }
    }

    private void monstro(){
        int rand = gerador.nextInt(100);
        if (rand<10){
            final Monstros monstros = Monstros.G;
            final MonstroUni monstro =  monstros.monstro(andar,andarMax);
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
                    bundle.putString("andares",andar+"-"+andarMax);
                    bundle.putSerializable("monstro", monstro);
                    it.putExtras(bundle);
                    startActivity(it);
                }
            }).setNegativeButton("Fugir", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int agiT = 0;
                    for (int i = 0;i<dados.size();i++ ){
                        agiT+=  dados.get(i).getAgi();
                    }
                    int agiM = agiT/dados.size();
                    int fuga = (agiM/monstro.getStatus())*100;
                    int valor = gerador.nextInt(100);
                    System.out.println("valor: "+valor);
                    System.out.println("Fuga: "+fuga);
                    if(valor<=fuga){
                        visualizar("Conseguiu fugir","Alerta");
                    }else{
                        visualizar("Falha ao tentar fugir fugir","Alerta");
                    }
                }
            });
            alert.setCancelable(false);
            alert.show();
        }
    }

    private void visualizar(Object item,String titulo){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(titulo);
        alert.setMessage(item.toString());
        alert.show();
    }
}
