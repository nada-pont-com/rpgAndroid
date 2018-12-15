package br.com.vinicius.rpg;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class Battle extends AppCompatActivity {

    private String NomeDungeon;
    private String rank;
    private String andares;
    private ListView Perso;
    private TextView atacar;
    private TextView ataque;
    private TextView defender;
    private TextView nome;
    private TextView level;
    private TextView mp;
    private TextView vida;
    private ProgressBar vidaBar;
    private ProgressBar mpBar;
    private int PersoNunber = 0;
    private List<DadosTable> dados;
    private MonstroUni monstro;
    private AdapterBattlePersoPersonalizado adapter;
    private Random random = new Random();

    @Override
    public void onBackPressed() {
        // não chame o super desse método //Impede o botão de voltar do celular voltar para Activy anterior.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        assert bundle != null;
        NomeDungeon = bundle.getString("nomeDungeon");
        rank = bundle.getString("rank");
        andares = bundle.getString("andares");
        //Pega os dados do monstro enviados da activy anterior
        monstro  = (MonstroUni) bundle.getSerializable("monstro");
        assert monstro != null;

        //Buscar dados do jogador

        Perso = findViewById(R.id.listaDePerso);
        dados =  Sessao.getDadosPerso();
        adapter = new AdapterBattlePersoPersonalizado(dados,this);
        Perso.setAdapter(adapter);

        atacar = findViewById(R.id.Atacar);
        ataque = findViewById(R.id.Ataque);
        defender = findViewById(R.id.Defender);

        atacar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout acoes = findViewById(R.id.Acoes);
                adapter.select(PersoNunber,1);
                acoes.setMinimumWidth(115);
            }
        });

        ataque.setOnClickListener(batalha(0,0));
        //defender.setOnClickListener();

        nome = findViewById(R.id.Nome);
        level = findViewById(R.id.Level);
        vida = findViewById(R.id.NunberVida);
        mp = findViewById(R.id.NunberMp);

        vidaBar = findViewById(R.id.BarVida);
        mpBar = findViewById(R.id.BarMp);

        nome.setText(monstro.getNome());
        System.out.println(nome.getLayout());
        level.setText("Lv:"+monstro.getLevel());
        vida.setText(monstro.getVida()+"/"+monstro.getVida());
        mp.setText(monstro.getMp()+"/"+monstro.getMp());
        System.out.println(monstro.getVida());

        vidaBar.setMax(monstro.getVida());
        vidaBar.setProgress(monstro.getVida());

        mpBar.setMax(monstro.getMp());
        mpBar.setProgress(monstro.getMp());
    }

    //Referencia se refere se é um atk comum, habilidade ou magia
    //0 - atk
    //1 - habilidade
    //2 - magia
    //Posicao se refere a posicao da magia ou habilidade
    private View.OnClickListener batalha(final int referencia, int posicao){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (referencia){
                    case 0: //Atk Comum
                        DadosTable dado = dados.get(PersoNunber);
                        int atk = dado.getAtk();
                        System.out.println(atk);
                        double atkReal = atk - (monstro.getStatus()/5);
                        System.out.println(atkReal);
                        int dano = (int) atkReal;
                        System.out.println(dano);
                        int validador = monstro.getVida()-dano;
                        if (validador<=0){
                            dado.setExperiencia(monstro.getEx()+dado.getExperiencia());
                            PersoNunber = 0;
                            validador = 0;
                            Salvar();
                            AlertDialog.Builder alert = new AlertDialog.Builder(Battle.this);
                            alert.setCancelable(false);
                            alert.setMessage("Deseja continuar a jornada na Dongeon ou voltar para a superfice!");
                            alert.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent it = new Intent(Battle.this, Dungeon.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("nomeDungeon", NomeDungeon);
                                    bundle.putString("rank",rank);
                                    System.out.println(andares);
                                    bundle.putString("andares",andares);
                                    it.putExtras(bundle);
                                    startActivity(it);

                                }
                            });
                            alert.setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent it = new Intent(Battle.this, Jogo.class);
                                    it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(it);
                                }
                            });
                            alert.show();
                        }else {
                            if (dados.size()<(PersoNunber+1)){
                                PersoNunber++;
                            }else{
                                PersoNunber = 0;
                                monstroAtk();
                            }
                        }
                        monstro.setVida(validador);
                        vidaBar.setProgress(monstro.getVida());
                        vida.setText(monstro.getVida()+"/"+vidaBar.getMax());
                        LinearLayout acoes = findViewById(R.id.Acoes);
                        adapter.select(PersoNunber,0);
                        acoes.setMinimumWidth(0);
                        break;
                    case 1:
                        break;
                }
            }
        };
    }
    private void monstroAtk(){
        int personagens = dados.size()-1;
        if (personagens!=0){
            personagens = random.nextInt(personagens);
        }
        DadosTable dado = dados.get(personagens);
        int atk = monstro.getStatus();
        System.out.println(atk);
        double atkReal = atk - (dado.getDef()/5);
        System.out.println(atkReal);
        int dano = (int) atkReal;
        int validador = dado.getVida()-dano;
        if(validador<=0){
            validador = 0;
            dado.setVida(validador);
            Salvar();
            AlertDialog.Builder alert = new AlertDialog.Builder(Battle.this);
            alert.setCancelable(false);
            alert.setMessage("Você fio derrotado por um "+monstro.getNome()+"!");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent it = new Intent(Battle.this,Jogo.class);
                    startActivity(it);
                }
            });
        }
        System.out.println(dano);
        dado.setVida(validador);
    }
    private void Salvar(){
        Bd banco = new Bd(getBaseContext());
        SQLiteDatabase db = banco.getWritableDatabase();
        Loads.comandos comandos = new Loads.comandos();
        for (int i = 0;i<dados.size();i++){
            comandos.atulizarDados(db,dados.get(i),dados.get(i).getLoadId(),dados.get(i).getId());
        }

    }
}
