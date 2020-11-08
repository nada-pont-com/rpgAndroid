package br.com.vinicius.rpg.jogo.batalha;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.vinicius.rpg.dados.Itens;
import br.com.vinicius.rpg.jogo.dungeon.Dungeon;
import br.com.vinicius.rpg.jogo.inicio.Jogo;
import br.com.vinicius.rpg.R;
import br.com.vinicius.rpg.jogo.informacoes.Sessao;
import br.com.vinicius.rpg.jogo.informacoes.Tempo;
import br.com.vinicius.rpg.adapters.AdapterBattlePersoPersonalizado;
import br.com.vinicius.rpg.banco.Bd;
import br.com.vinicius.rpg.banco.Loads;
import br.com.vinicius.rpg.objetosTabelas.ItensTable;
import br.com.vinicius.rpg.objetosTabelas.MonstroUni;
import br.com.vinicius.rpg.objetosTabelas.PersoTable;
import br.com.vinicius.rpg.objetosTabelas.HabilidadesPersoTable;
import br.com.vinicius.rpg.objetosTabelas.HabilidadesTable;


//Batalhas
public class Battle extends AppCompatActivity {

    private String NomeDungeon;
    private String rank;
    private String andares;
    private ListView Perso;
    private TextView atacar;
    private TextView ataque;
    private TextView habilidades;
    private TextView defender;
    private TextView nome;
    private TextView level;
    private TextView mp;
    private TextView vida;
    private TextView descricao;
    private ListView listaHabilidadesEMagia;
    private ListView ListaAcoes;
    private Button confirmar;
    private Button voltar;
    private ProgressBar vidaBar;
    private ProgressBar mpBar;
    private int PersoNunber = 0;
    private MonstroUni monstro;
    private AdapterBattlePersoPersonalizado adapter;
    private Random random = new Random();
    private List<PersoTable> persos;
    private List<String> listaAcoes = new ArrayList<>();
    private List<HabilidadesPersoTable> listaHabilidadesPerso;
    private List<ItensTable> ListaDeItens = new ArrayList<>();
    private int nocalte = 0;
    private boolean pause = false;

    @Override
    public void onBackPressed() {
        // não chame o super desse método //Impede o botão de voltar do celular voltar para Activy anterior.
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(!pause){
            Tempo.timer.cancel();
            Tempo.setTimer(null);
            Sessao.getTempo().setONOFF(false);
            Sessao.getAutoSalve().setONOFF(false);
            Loads.comandos comandos = new Loads.comandos();
            Bd banco = new Bd(Battle.this);
            SQLiteDatabase db = banco.getWritableDatabase();
            comandos.atulizarLoad(db,Sessao.getLoad());
            db.close();
            Sessao.setNull();
            this.finishAffinity();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        pause = false;
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
        //Pega os persos do monstro enviados da activy anterior
        monstro  = (MonstroUni) bundle.getSerializable("monstro");
        assert monstro != null;

        //Buscar persos do jogador

        Perso = findViewById(R.id.listaDePerso);
        persos =  Sessao.getDadosPerso();
        adapter = new AdapterBattlePersoPersonalizado(persos,this);
        Perso.setAdapter(adapter);

        atacar = findViewById(R.id.Atacar);
        ataque = findViewById(R.id.Ataque);
        defender = findViewById(R.id.Defender);
        habilidades = findViewById(R.id.Habilidades);
        listaHabilidadesEMagia = findViewById(R.id.ListaHabilidades);
        descricao = findViewById(R.id.Descrisao);
        confirmar = findViewById(R.id.Confirmar);
        voltar = findViewById(R.id.Voltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout habilidadesMagias  = findViewById(R.id.HabilidadeMagia);
                habilidadesMagias.setVisibility(View.INVISIBLE);
            }
        });

        Loads.comandos comandos = new Loads.comandos();
        Bd banco = new Bd(this);
        SQLiteDatabase db = banco.getWritableDatabase();

        // buscando habilidades do jogador
        listaHabilidadesPerso = comandos.buscaHabilidadesDoPerso(db,-1,persos.get(PersoNunber).getId());

        atacar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout acoes = findViewById(R.id.Acoes);
                adapter.select(PersoNunber,1);
                acoes.setMinimumWidth(115);
            }
        });

        ataque.setOnClickListener(batalha(0));
        defender.setOnClickListener(batalha(1));
        habilidades.setOnClickListener(batalha(2));

        nome = findViewById(R.id.Nome);
        level = findViewById(R.id.Level);
        vida = findViewById(R.id.NunberVida);
        mp = findViewById(R.id.NunberMp);

        vidaBar = findViewById(R.id.BarVida);
        mpBar = findViewById(R.id.BarMp);

        nome.setText(monstro.getNome());
        System.out.println(nome.getLayout());
        String lv="Lv:"+monstro.getLevel(),
                hp = monstro.getVida()+"/"+monstro.getVida(),
                mP = monstro.getMp()+"/"+monstro.getMp();
        level.setText(lv);
        vida.setText(hp);
        mp.setText(mP);
        System.out.println(monstro.getVida());

        vidaBar.setMax(monstro.getVida());
        vidaBar.setProgress(monstro.getVida());

        mpBar.setMax(monstro.getMp());
        mpBar.setProgress(monstro.getMp());

        ListaAcoes = findViewById(R.id.ListaAcoes);

    }

    //Referencia se refere se é um atk comum ou defesa
    //0 - atk
    //1 - defesa
    //2 - Habilidades
    //3 - Magia
    //4 - fuga
    private View.OnClickListener batalha(final int referencia){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (referencia){
                    case 0: //Atk Comum
                        atk(1,null);
                        break;
                    case 1://Defender

                        break;
                    case 2:
                        //LinearLayout habilidadesMagias  = findViewById(R.id.HabilidadeMagia);
                        //habilidadesMagias.setVisibility(View.VISIBLE);
                        final List<HabilidadesTable> habiPerso = Sessao.getListaDeHabilidadesDosPersonagens().get(PersoNunber);

                        ArrayAdapter<HabilidadesTable> adapterHab = new ArrayAdapter<>(Battle.this,
                                android.R.layout.simple_list_item_1, habiPerso);

                        final AlertDialog.Builder builder = new AlertDialog.Builder(Battle.this);
                        builder.setCancelable(false);
                        builder.setTitle("Habilidades");
                        builder.setSingleChoiceItems(adapterHab,-1 ,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.dismiss();
                                System.out.println(habiPerso.get(which).getNome());
                                AlertDialog.Builder alert = new AlertDialog.Builder(Battle.this);
                                alert.setTitle("Deseja utilizar "+habiPerso.get(which).getNome());
                                alert.setMessage(habiPerso.get(which).getDescricao());
                                final HabilidadesTable habi = habiPerso.get(which);
                                alert.setPositiveButton("Confimar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Habilidade(habi);
                                    }
                                }).setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        builder.show();
                                    }
                                }).setCancelable(false);
                                alert.show();
                            }
                        });
                        builder.setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                        break;
                }
            }
        };
    }

    private void atk(int atks,HabilidadesTable habilidades){
        PersoTable perso = persos.get(PersoNunber);
        for (int i = 0;i<atks;i++){
            int valor = 1,nocalteValor = 0,custo = 0;
            if(habilidades!=null){
                valor = habilidades.getValor();
                nocalteValor = habilidades.getNocalte();
            }
            double atk = perso.getAtk()*valor*0.01+(perso.getAtk());
            System.out.println("atk:"+atk);
            double atkReal = atk - (double)(monstro.getStatus()/5);
            if(atkReal<0){
                atkReal = 0;
            }
            System.out.println("atkR:"+atkReal);
            int dano = (int) atkReal;
            int validador = monstro.getVida()-dano;
            String acao = "";
            if(habilidades!=null){
                acao = perso.getNome()+": usou a habilidade "+habilidades.getNome()+" e causou "+dano+" de dano com "+habilidades.getNocalte()+"% de nocalte";
            }else{
                acao = perso.getNome()+": Atacou e causou "+dano+" de dano";
            }
            listaAcoes.add(acao);
            if (validador<=0) {
                PersoNunber = 0;
                validador = 0;
                monsterMorto(perso);
            }else{
                if(nocalteValor!=0){
                    int rand = random.nextInt(100);
                    if((rand<=nocalteValor) && (nocalte==0)){
                        nocalte = 3;
                        acao = "Montro foi nocalteado por 3 rodadas";
                        listaAcoes.add(acao);
                    }
                }
                if (persos.size()<(PersoNunber+1)){
                    PersoNunber++;
                }else{
                    System.out.println("-----------Entrou----------------");
                    PersoNunber = 0;
                }
                if(nocalte!=0){
                    acao = "Monstro nocalteado por "+nocalte+" rodada(s)";
                    listaAcoes.add(acao);
                }else{
                    monstroAtk();
                }

            }
            ListaAcoes();
            monstro.setVida(validador);
            vidaBar.setProgress(monstro.getVida());
            vida.setText(monstro.getVida()+"/"+vidaBar.getMax());
            LinearLayout acoes = findViewById(R.id.Acoes);
            adapter.select(PersoNunber,0);
            acoes.setMinimumWidth(0);
        }
    }

    private void monstroAtk(){
        if(nocalte==0){
            int personagens = persos.size()-1;
            if (personagens!=0){
                personagens = random.nextInt(personagens);
            }
            PersoTable perso = persos.get(personagens);
            int atk = monstro.getStatus();
            double atkReal = atk - (perso.getDef()/5);
            if(atkReal<0){
                atkReal = 0;
            }
            int dano = (int) atkReal;
            String acao = monstro.getNome()+": Atacou e causou "+dano+" de dano";
            listaAcoes.add(acao);
            ListaAcoes();
            int validador = perso.getVida()-dano;
            if(validador<=0){
                validador = 0;
                perso.setVida(validador);
                Salvar();
                AlertDialog.Builder alert = new AlertDialog.Builder(Battle.this);
                alert.setCancelable(false);
                alert.setMessage("Você fio derrotado por um "+monstro.getNome()+"!");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pause = true;
                        Intent it = new Intent(Battle.this, Jogo.class);
                        startActivity(it);
                    }
                });
                alert.show();
            }
            perso.setVida(validador);
        }else{
            nocalte--;
        }
    }

    //Ação das habilidades
    private void Habilidade(HabilidadesTable habilidades){
        PersoTable perso = persos.get(PersoNunber);
        int tipo = Integer.parseInt(habilidades.getTipo());
        int mp = perso.getMp(),custo = habilidades.getCusto();
        if(mp>custo){
            perso.setMp(mp-custo);
            mpBar.setProgress(perso.getMp());
            if(tipo==1){
                int atks = habilidades.getNuberAtk();
                atk(atks,habilidades);
            }else{
                monstroAtk();
            }
        }
    }

    private void monsterMorto(PersoTable perso){
        perso.setExperiencia(monstro.getEx()+perso.getExperiencia());
        monstroItens();
        Salvar();
        AlertDialog.Builder alert = new AlertDialog.Builder(Battle.this);
        alert.setCancelable(false);
        alert.setMessage(monstro.getNome()+" derrotado(a) e ganhou "+monstro.getEx()+" de Exp."+
                "\nDeseja continuar a jornada na Dongeon ou voltar para a superfice!");
        alert.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pause = true;
                Intent it = new Intent(Battle.this, Dungeon.class);
                startActivity(it);
            }
        });

        alert.setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pause = true;
                Intent it = new Intent(Battle.this, Jogo.class);
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(it);
            }
        });
        alert.show();
    }

    private void monstroItens(){
        String[] itens =  monstro.getItem();
        String[] itens2;
        int cont = itens.length;
        int[][] minMax = new int[cont][2];
        int[] quant = new int[cont];
        for (int i = 0;i<cont;i++){
            itens2 = itens[i].split("_");
            itens[i] = itens2[0].split(":")[1];
            itens2 = itens2[1].split("-");
            minMax[i][0] = Integer.parseInt(itens2[0]);
            minMax[i][1] = Integer.parseInt(itens2[1]);
            quant[i] = random.nextInt(minMax[i][1])+minMax[i][0];
            if(quant[i]>0){
                ItensTable item = new Itens().getItensId(Integer.parseInt(itens[i]));
                item.setQuantidade(quant[i]);
                ListaDeItens.add(item);
            }
        }
    }

    private void ListaAcoes(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listaAcoes);
        ListaAcoes.setAdapter(adapter);
    }

    private void Salvar(){
        Bd banco = new Bd(getBaseContext());
        SQLiteDatabase db = banco.getWritableDatabase();
        Loads.comandos comandos = new Loads.comandos();
        for (int i = 0;i<ListaDeItens.size();i++){
            comandos.InserirItensLoad(db,ListaDeItens.get(i).getId(),persos.get(0).getLoadId(),ListaDeItens.get(i).getQuantidade());
        }
        for (int i = 0;i<persos.size();i++){
            comandos.atulizarDados(db,persos.get(i),persos.get(i).getLoadId(),persos.get(i).getId());
        }
        db.close();
    }
}
