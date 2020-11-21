package br.com.vinicius.rpg.jogo.inicio;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.vinicius.rpg.R;
import br.com.vinicius.rpg.jogo.informacoes.Sessao;
import br.com.vinicius.rpg.jogo.informacoes.Tempo;
import br.com.vinicius.rpg.banco.Loads;
import br.com.vinicius.rpg.objetosTabelas.PersoTable;
import br.com.vinicius.rpg.objetosTabelas.HabilidadesPersoTable;
import br.com.vinicius.rpg.objetosTabelas.HabilidadesTable;


public class PersonagemMenu extends AppCompatActivity {

    private TextView Nome;
    private TextView Level;
    private TextView Vida;
    private TextView Exp;
    private TextView Classe;
    private TextView Pontos;
    private TextView Atk;
    private TextView Def;
    private TextView AtkM;
    private TextView DefM;
    private TextView Agi;
    private TextView Mp;
    private TextView Vit;
    private TextView Int;
    private ProgressBar VidaBar;
    private ProgressBar ExpBar;

    private Button hab;
    private Button status;
    private Button mag;

    private ListView Habilidades;
    private TextView NomeHab;
    private TextView DescricaoHab;

    private PersoTable dado;
    private List<HabilidadesTable> habilidadeList;
    private int pontos;
    private int pontosHab;
    private int positionHab;
    private boolean pause = false;


    @Override
    protected void onPause(){
        super.onPause();
        if(!pause){
            Tempo.timer.cancel();
            Tempo.setTimer(null);
            Sessao.getTempo().setONOFF(false);
            Sessao.getAutoSalve().setONOFF(false);
            Loads.comandos comandos = new Loads.comandos();
            comandos.atulizarLoad(getBaseContext(),Sessao.getLoad());
            Sessao.setNull();
            this.finishAffinity();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        pause = false;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personagem_menu);

        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        assert bundle != null;
        int position = bundle.getInt("position");
        dado = Sessao.getDadosPerso().get(position);
        pontos = dado.getPontosExp();
        pontosHab = dado.getPontosHab();

        Nome = findViewById(R.id.Nome);
        Level = findViewById(R.id.Level);
        Vida = findViewById(R.id.Vida);
        Exp = findViewById(R.id.ExperienciaNunber);
        Classe = findViewById(R.id.Classe);
        Pontos = findViewById(R.id.Pontos);
        Atk = findViewById(R.id.atk);
        Def = findViewById(R.id.def);
        AtkM = findViewById(R.id.atkM);
        DefM = findViewById(R.id.defM);
        Agi = findViewById(R.id.agi);
        Mp = findViewById(R.id.mp);
        Vit = findViewById(R.id.Vit);
        Int = findViewById(R.id.Int);

        ExpBar = findViewById(R.id.progressExp);
        VidaBar = findViewById(R.id.progressVida);

        Nome.setText(dado.getNome());
        Level.setText("Lv: "+dado.getLevel());
        Vida.setText(dado.getVida()+"/"+dado.getVidaMax());
        Exp.setText(dado.getExperiencia()+"/"+dado.getExpMax());
        Classe.setText("Classe: "+dado.getClasse());
        Atk.setText("Atk: "+dado.getAtk());
        Def.setText("Def: "+dado.getDef());
        AtkM.setText("AtkM: "+dado.getAtkM());
        DefM.setText("DefM: "+dado.getDefM());
        Agi.setText("Agi: "+dado.getAgi());
        Vit.setText("Vit: "+dado.getVit());
        Int.setText("Int: "+dado.getIntl());
        Mp.setText("Mp: "+dado.getMp()+"/"+dado.getMpMax());
        Pontos.setText("Pontos: "+pontos);


        ExpBar.setProgress(dado.getExperiencia());
        ExpBar.setMax(dado.getExpMax());
        VidaBar.setProgress(dado.getVida());
        VidaBar.setMax(dado.getVidaMax());

        status = findViewById(R.id.Status);
        hab = findViewById(R.id.Habilidades);
        mag = findViewById(R.id.Magias);


        habilidadeList = Sessao.getHabilidades();
        System.out.println(habilidadeList.get(0).getNome());

        NomeHab = findViewById(R.id.NomeHab);
        DescricaoHab = findViewById(R.id.DescricaoHab);
        Button aprenderHab = findViewById(R.id.AprenderHab);
        Habilidades = findViewById(R.id.HabilidadeLista);

        ArrayAdapter<HabilidadesTable> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, habilidadeList);
        Habilidades.setAdapter(adapter);

        Button Voltar = findViewById(R.id.Voltar);
        Button Salvar = findViewById(R.id.Salvar);

        final List<TextView> valor = new ArrayList<>();
        List<ImageButton> mais = new ArrayList<>();
        List<ImageButton> menos = new ArrayList<>();

        View layout;
        int[] layouts =  {R.id.um,R.id.dois,R.id.tres,R.id.quatro,R.id.cinco,R.id.seis,R.id.sete};



        for(int i = 0;i<layouts.length;i++){
            layout = findViewById(layouts[i]);
            valor.add((TextView) layout.findViewById(R.id.valor));

            mais.add( (ImageButton) layout.findViewById(R.id.mais));
            menos.add( (ImageButton) layout.findViewById(R.id.menos));

            final int finalI = i;
            mais.get(i).setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    if(pontos>0){
                        int valorT = Integer.parseInt(valor.get(finalI).getText().toString()) + 1;
                        valor.get(finalI).setText(valorT+"");
                        pontos--;
                        Pontos.setText("Pontos: "+pontos);
                    }
                }
            });
            menos.get(i).setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    int valorT = Integer.parseInt(valor.get(finalI).getText().toString());
                    if(valorT>0){
                        valorT--;
                        valor.get(finalI).setText(valorT+"");
                        pontos++;
                        Pontos.setText("Pontos: "+pontos);
                    }
                }
            });
        }

        Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause = true;
                Intent it = new Intent(PersonagemMenu.this, Jogo.class);
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(it);
            }
        });

        Salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int atk = Integer.parseInt(valor.get(0).getText().toString());
                int def = Integer.parseInt(valor.get(1).getText().toString());
                int agi = Integer.parseInt(valor.get(2).getText().toString());
                int atkM = Integer.parseInt(valor.get(3).getText().toString());
                int defM = Integer.parseInt(valor.get(4).getText().toString());
                int intl = Integer.parseInt(valor.get(5).getText().toString());
                int vit = Integer.parseInt(valor.get(6).getText().toString());

                //salvar pontos, zerar ou voltar para a home
                dado.setAtk(dado.getAtk()+atk);
                dado.setDef(dado.getDef()+def);
                dado.setAtkM(dado.getAtkM()+atkM);
                dado.setDefM(dado.getDefM()+defM);
                dado.setAgi(dado.getAgi()+agi);
                dado.setVit(dado.getVit()+vit);
                dado.setIntl(dado.getIntl()+intl);
                dado.setPontosExp(pontos);
                Loads.comandos comandos = new Loads.comandos();
//                Bd Banco = new Bd(PersonagemMenu.this);
//                SQLiteDatabase db = Banco.getWritableDatabase();
                comandos.atulizarDados(getBaseContext(),dado,dado.getLoadId(),dado.getId());

                pause = true;
                Intent it = new Intent(PersonagemMenu.this,Jogo.class);
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(it);
            }
        });

        status.setOnClickListener(Button(3));
        mag.setOnClickListener(Button(2));
        hab.setOnClickListener(Button(1));


        Habilidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HabilidadesTable habilidade = habilidadeList.get(position);
                NomeHab.setText(habilidade.getNome());
                DescricaoHab.setText(habilidade.getDescricao());
                positionHab = position;
            }
        });

        aprenderHab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final HabilidadesTable habilidade =habilidadeList.get(positionHab);
                AlertDialog.Builder alert = new AlertDialog.Builder(PersonagemMenu.this);
                alert.setMessage("Deseja aprender "+habilidade.getNome()+ " por "+habilidade.getPontos()+" pontos.");
                alert.setTitle("Aprender Habilidade");
                alert.setPositiveButton("Aprender", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int valida = pontosHab-habilidade.getPontos();
                        AlertDialog.Builder alert = new AlertDialog.Builder(PersonagemMenu.this);
                        if(valida>=0){
//                            Bd banco = new Bd(PersonagemMenu.this);
//                            SQLiteDatabase db = banco.getWritableDatabase();
                            Loads.comandos comandos = new Loads.comandos();
                            List<HabilidadesPersoTable> habilidadesPerso = comandos.buscaHabilidadesDoPerso(getBaseContext(),habilidade.getId(),dado.getId());
                            if(habilidadesPerso.size()==0){
                                comandos.InserirHabilidadePerso(habilidade.getId(),dado.getId(),getBaseContext());
                                alert.setMessage("Habilidade Aprendida: "+ habilidade.getNome());
                                pontosHab = valida;
                                dado.setPontosHab(pontosHab);
                                comandos.atulizarDados(getBaseContext(),dado,dado.getLoadId(),dado.getId());
                                Pontos.setText("Pontos: "+pontosHab);
                            }else{
                                alert.setTitle("Aviso");
                                alert.setMessage("Voce já tem esse Habilidade");
                            }
                        }else {
                            alert.setMessage("Pontos insuficientes");
                        }
                        alert.setNeutralButton("Ok", null);
                        alert.show();
                    }
                });
                alert.setNegativeButton("Canselar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
            }
        });
    }

    private View.OnClickListener Button(final int referencia){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisibleInvisible(referencia);
            }
        };
    }

    private void VisibleInvisible(int Referencia){
        LinearLayout magias = findViewById(R.id.MagiaLayout);
        LinearLayout habilidade = findViewById(R.id.HabilidadeLayout);
        LinearLayout status = findViewById(R.id.StatusLayout);
        switch (Referencia){
            case 1:
                magias.setVisibility(View.INVISIBLE);
                habilidade.setVisibility(View.VISIBLE);
                status.setVisibility(View.INVISIBLE);
                Pontos.setText("Pontos: "+pontosHab);
                break;
            case 2:
                magias.setVisibility(View.VISIBLE);
                habilidade.setVisibility(View.INVISIBLE);
                status.setVisibility(View.INVISIBLE);
                break;
            case 3:
                magias.setVisibility(View.INVISIBLE);
                habilidade.setVisibility(View.INVISIBLE);
                status.setVisibility(View.VISIBLE);
                Pontos.setText("Pontos: "+pontos);
                break;
        }
    }
}
