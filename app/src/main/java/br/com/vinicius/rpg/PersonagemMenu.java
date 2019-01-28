package br.com.vinicius.rpg;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.List;


public class
PersonagemMenu extends AppCompatActivity {

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

    private TextView valor;
    private TextView valor2;
    private TextView valor3;
    private TextView valor4;
    private TextView valor5;
    private TextView valor6;
    private TextView valor7;

    private ImageButton mais;
    private ImageButton mais2;
    private ImageButton mais3;
    private ImageButton mais4;
    private ImageButton mais5;
    private ImageButton mais6;
    private ImageButton mais7;

    private ImageButton menos;
    private ImageButton menos2;
    private ImageButton menos3;
    private ImageButton menos4;
    private ImageButton menos5;
    private ImageButton menos6;
    private ImageButton menos7;

    private Button hab;
    private Button status;
    private Button mag;

    private ListView Habilidades;
    private TextView NomeHab;
    private TextView DescricaoHab;
    private Button AprenderHab;

    private DadosTable dado;
    private List<HabilidadesTable> habilidadeList;
    private int pontos;
    private int pontosHab;
    private int positionHab;


    public PersonagemMenu() {
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

        Loads.comandos comandos = new Loads.comandos();
        Bd banco = new Bd(this);
        SQLiteDatabase db = banco.getWritableDatabase();
        habilidadeList = comandos.buscaHabilidades(db);
        db.close();
        NomeHab = findViewById(R.id.NomeHab);
        DescricaoHab = findViewById(R.id.DescricaoHab);
        AprenderHab = findViewById(R.id.AprenderHab);
        Habilidades = findViewById(R.id.HabilidadeLista);

        ArrayAdapter<HabilidadesTable> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, habilidadeList);
        Habilidades.setAdapter(adapter);

        Button Voltar = findViewById(R.id.Voltar);
        Button Salvar = findViewById(R.id.Salvar);

        View layout;
        final TextView[] valores = {valor,valor2,valor3,valor4,valor5,valor6,valor7};
        ImageButton[] maises = {mais,mais2,mais3,mais4,mais5,mais6,mais7};
        ImageButton[] menoes = {menos,menos2,menos3,menos4,menos5,mais6,mais7};
        int[] layouts =  {R.id.um,R.id.dois,R.id.tres,R.id.quatro,R.id.cinco,R.id.seis,R.id.sete};
        for(int i = 0;i<layouts.length;i++){
            layout = findViewById(layouts[i]);
            valores[i] = layout.findViewById(R.id.valor);
            maises[i] = layout.findViewById(R.id.mais);
            menoes[i] = layout.findViewById(R.id.menos);

            final int finalI = i;
            maises[i].setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    int valorT = Integer.parseInt(valores[finalI].getText().toString());
                    if(pontos>0){
                        valorT++;
                        valores[finalI].setText(valorT+"");
                        pontos--;
                        Pontos.setText("Pontos: "+pontos);
                    }
                }
            });
            menoes[i].setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    int valorT = Integer.parseInt(valores[finalI].getText().toString());
                    if(valorT>0){
                        valorT--;
                        valores[finalI].setText(valorT+"");
                        pontos++;
                        Pontos.setText("Pontos: "+pontos);
                    }
                }
            });
        }

        Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(PersonagemMenu.this,Jogo.class);
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(it);
            }
        });

        Salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int atk = Integer.parseInt(valores[0].getText().toString());
                int def = Integer.parseInt(valores[1].getText().toString());
                int agi = Integer.parseInt(valores[2].getText().toString());
                int atkM = Integer.parseInt(valores[3].getText().toString());
                int defM = Integer.parseInt(valores[4].getText().toString());
                int intl = Integer.parseInt(valores[5].getText().toString());
                int vit = Integer.parseInt(valores[6].getText().toString());

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
                Bd Banco = new Bd(PersonagemMenu.this);
                SQLiteDatabase db = Banco.getWritableDatabase();
                comandos.atulizarDados(db,dado,dado.getLoadId(),dado.getId());

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

        AprenderHab.setOnClickListener(new View.OnClickListener() {
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
                            Bd banco = new Bd(PersonagemMenu.this);
                            SQLiteDatabase db = banco.getWritableDatabase();
                            Loads.comandos comandos = new Loads.comandos();
                            List<HabilidadesPersoTable> habilidadesPerso = comandos.buscaHabilidadesDoPerso(db,habilidade.getId(),dado.getId());
                            if(habilidadesPerso.size()==0){
                                comandos.InserirHabilidadePerso(habilidade.getId(),dado.getId(),db);
                                alert.setMessage("Habilidade Aprendida: "+ habilidade.getNome());
                                pontosHab = valida;
                                dado.setPontosHab(pontosHab);
                                comandos.atulizarDados(db,dado,dado.getLoadId(),dado.getId());
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
