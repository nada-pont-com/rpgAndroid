package br.com.vinicius.rpg.jogo.inicio;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.vinicius.rpg.R;
import br.com.vinicius.rpg.dados.Classes;
import br.com.vinicius.rpg.jogo.informacoes.Sessao;
import br.com.vinicius.rpg.banco.Loads;
import br.com.vinicius.rpg.inicio.MainActivity;
import br.com.vinicius.rpg.objetosTabelas.PersoTable;
import br.com.vinicius.rpg.objetosTabelas.LoadTable;

public class NovoJogo extends AppCompatActivity {

    private Button Criar;
    private Button Cancelar;
    private Button Excluir;
    private Button Voltar;
    private Button avancar;
    private Button avancar2;
    private Button Voltar2;
    private Button Voltar3;
    private TextView textView;
    private ListView lista;
//    private RadioButton classGue;
//    private RadioButton classExpl;
    private EditText nomePerso;
    private EditText Nome;
    private Classes classe;
    private int loadId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_jogo);

        Cancelar = (Button) findViewById(R.id.Cancelar);
        avancar = (Button) findViewById(R.id.Avancar);
        avancar2 = (Button) findViewById(R.id.Avancar2);
        Voltar2 = (Button) findViewById(R.id.Voltar2);
        Voltar3 = (Button) findViewById(R.id.Voltar3);
        nomePerso = (EditText) findViewById(R.id.NomePerso);

        RadioGroup radio = findViewById(R.id.radioGrupoClasse);

        final ArrayList<RadioButton> classes = new ArrayList<>();
        for (Classes classe : Classes.values()){
            RadioButton classeButton = new RadioButton(getBaseContext());
            classeButton.setText(classe.getClasse());
            classes.add(classeButton);
            radio.addView(classeButton);
        }

        Nome = (EditText) findViewById(R.id.Nome);
        textView = (TextView) findViewById(R.id.Selecionado);
        lista = (ListView) findViewById(R.id.lista);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dados = parent.getItemAtPosition(position).toString();
                textView.setText(dados);
            }
        });
        Excluir = (Button) findViewById(R.id.Excluir);
        Voltar = (Button) findViewById(R.id.Voltar);
        Criar = (Button) findViewById(R.id.Criar);
        Criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((Nome.getText().toString()).equals("")){
                    Nome.setError("Prenecha o campo");
                }else{
                    if(validador(new Loads.comandos().buscaLoad(getBaseContext()))){
                        VisibleInviseble2(1);
                    }
                }
            }
        });

        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(NovoJogo.this,MainActivity.class);
                startActivity(it);
            }
        });

        Excluir.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String texto = textView.getText().toString();
                if(!texto.equals("Selecionado")){
                    String[] t = texto.split(",");
                    t = t[0].split(": ");
                    new Loads.comandos().deletaSave(getBaseContext(), Long.parseLong(t[1]));
                    textView.setText("Selecionado");
                    geraLista();
                }else{
                    visualizar("Selecione um salve para excluir","Alert");
                }
            }
        });

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cont = 0;
                for (RadioButton radioClasse : classes){
                    if(radioClasse.isChecked()){
                        classe = Classes.values()[cont];
                        VisibleInviseble2(2);
                        return;
                    }
                    cont++;
                }
                visualizar("Selecione um!","Alerta!");
            }
        });

        avancar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nomePerso.getText().toString();
                if(nome.equals("")){
                    nomePerso.setError("Preencha o campo");
                }else {
                    Loads.comandos comandos = new Loads.comandos();
                    LoadTable load = new LoadTable();
                    load.setNome(Nome.getText().toString());
                    load.setId(loadId);
                    load.setTempo("00:00:01");
                    load.setMoeda(0,2,0);
                    boolean valor = comandos.Inserir(load,getBaseContext());
                    if(valor){
                        if((loadId!=0) || (classe!=null)){
                            PersoTable perso = classe.getStatus();
                            perso.setId(0);
                            perso.setLoadId(loadId);
                            perso.setLevel(1);
                            perso.setNome(nome);
                            perso.setExperiencia(0);
                            perso.setVida(100);
                            perso.setVidaMax(100);
                            perso.setMp(10);
                            perso.setMpMax(10);
                            perso.setRank("G");
                            perso.setRankExp(0);
                            perso.setPontosHab(0);
                            perso.setPontosExp(0);
                            boolean retorno = comandos.InserirDados(perso,getBaseContext());
                            if(retorno){
                                List<PersoTable> dado = new ArrayList<PersoTable>();
                                dado.add(perso);
                                Sessao.setDadosPerso(dado);
                                Sessao.setLoad(load);
                                Sessao.setHabilidades();
                                Intent it =  new Intent(NovoJogo.this, Jogo.class);
                                startActivity(it);
                            }else{
                                comandos.deletaLoad(getBaseContext(),load);
                                visualizar("Erro ao cadastrar novo salve","Erro");
                            }
                        }else{
                            visualizar("Erro ao cadastrar novo salve","Erro");
                            Intent it =  new Intent(NovoJogo.this,MainActivity.class);
                            startActivity(it);
                        }
                    }else {
                        visualizar("Erro ao cadastrar novo salve","Erro");
                        Intent it =  new Intent(NovoJogo.this,MainActivity.class);
                        startActivity(it);
                    }
                }
            }
        });

        Voltar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisibleInviseble2(3);
            }
        });

        Voltar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisibleInviseble2(1);
            }
        });

        Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisibleInviseble(false);
            }
        });
    }

    private void visualizar(Object item,String titulo){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(titulo);
        alert.setMessage(item.toString());
        alert.show();
    }

    private void geraLista(){
        Loads.comandos comandos = new Loads.comandos();
        List<LoadTable> loads = comandos.buscaLoad(getBaseContext());;

        ArrayAdapter<LoadTable> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, loads);
        lista.setAdapter(adapter);
    }

    private boolean validador(List<LoadTable> loads){
        int cont = 0;
        boolean nomeRepetido = false;
//        LoadTable load;
//        List<LoadTable> loads = new ArrayList<>();
        for (LoadTable load : loads){
            if(Nome.getText().toString().equals(load.getNome())){
                nomeRepetido = true;
            }
            cont++;
        }

        if(cont>=3){
            ArrayAdapter<LoadTable> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, loads);
            lista.setAdapter(adapter);
            lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Titulo");
            alert.setMessage("Numero maximo de Salves alcansado");
            alert.setNeutralButton("Deletar Salve", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    VisibleInviseble(true);
                    lista.setVisibility(View.VISIBLE);
                }
            });
            alert.setNegativeButton("Voltar ao Inicio", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent it =  new Intent(NovoJogo.this, MainActivity.class);
                    startActivity(it);
                }
            });
            alert.setCancelable(false);
            alert.show();

        }else if(nomeRepetido){
            visualizar("Nome de salve Repetido","Alerta");
        }else{
            int cont2 = 1;
            if(loads.size()==2){
                cont2 = 6;
                for (int i=0;i<loads.size();i++){
                    cont2 = cont2-loads.get(i).getId();
                }
            }else if(loads.size()!=0){
                if(loads.get(0).getId()==1){
                    cont2++;
                }
            }
            loadId = cont2;
            return true;
        }
        return false;
    }

    //1 para tornar layoutJogo2 invisivel, layout invisivel e layoutJogo visivel
    //2 para tornar layoutJogo  invisivel e layoutJogo2 visivel
    //3 para tornar layoutJogo ninvisivel e layout visivel
    private void VisibleInviseble2(int UmOuDois){
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutJogo);
        LinearLayout layout2 = (LinearLayout) findViewById(R.id.layoutJogo2);
        LinearLayout layout3 = (LinearLayout) findViewById(R.id.layout);
        switch (UmOuDois){
            case 1://TODO adicionar lista com os status base dos personagens easy
                layout.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.INVISIBLE);
                layout3.setVisibility(View.INVISIBLE);
                break;
            case 2:
                layout2.setVisibility(View.VISIBLE);
                layout.setVisibility(View.INVISIBLE);
                break;

            case 3:
                layout.setVisibility(View.INVISIBLE);
                layout3.setVisibility(View.VISIBLE);
        }
    }

    // True para tornar layout invisivel e layout2 visivel // False para tornar layout2 invisivel e layout visivel
    private void VisibleInviseble(Boolean UmOuDois){
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        LinearLayout layout2 = (LinearLayout) findViewById(R.id.layout2);
        if(UmOuDois){
            layout2.setVisibility(View.VISIBLE);
            layout.setVisibility(View.INVISIBLE);
        }else {
            layout.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.INVISIBLE);
        }

    }
}
