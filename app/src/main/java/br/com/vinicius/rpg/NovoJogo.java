package br.com.vinicius.rpg;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NovoJogo extends AppCompatActivity {

    private Button Criar;
    private Button Excluir;
    private Button Voltar;
    private Button avancar;
    private Button avancar2;
    private Button Voltar2;
    private Button Voltar3;
    private TextView textView;
    private ListView lista;
    private RadioButton classGue;
    private RadioButton classAven;
    private EditText nomePerso;
    private EditText Nome;
    private String classe;
    private Bd banco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_jogo);
        banco = new  Bd(getBaseContext());
        avancar = (Button) findViewById(R.id.Avancar);
        avancar2 = (Button) findViewById(R.id.Avancar2);
        Voltar2 = (Button) findViewById(R.id.Voltar2);
        Voltar3 = (Button) findViewById(R.id.Voltar3);
        nomePerso = (EditText) findViewById(R.id.NomePerso);
        classGue = (RadioButton) findViewById(R.id.ClasseGue);
        classAven = (RadioButton) findViewById(R.id.ClasseAven);
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
                    SQLiteDatabase db = banco.getReadableDatabase();
                    String[] projection = {
                            "id",
                            "nome",
                            "tempo"
                    };
                    Cursor cursor = db.query(
                            Loads.load.TABLE_NAME,
                            projection,
                            null,
                            null,
                            null,
                            null,
                            null);
                    if(validador(cursor)){
                        VisibleInviseble2(1);
                    }
                }
            }
        });

        Excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = textView.getText().toString();
                if(!texto.equals("Selecionado")){
                    String[] t = texto.split(",");
                    t = t[0].split(": ");
                    String where = "id="+t[1];
                    SQLiteDatabase db = banco.getReadableDatabase();
                    db.delete(Loads.load.TABLE_NAME,where,null);
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
                if(classAven.isChecked()){
                    classe = "Guerreiro";
                }else if(classGue.isChecked()){
                    classe = "Aventureiro";

                }else{
                    visualizar("Selecione um!","Alerta!");
                    return;
                }
                VisibleInviseble2(2);
            }
        });

        avancar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nomePerso.getText().toString();
                if(nome.equals("")){
                    nomePerso.setError("Preencha o campo");
                }else {
                    SQLiteDatabase db = banco.getWritableDatabase();
                    Loads.comandos comandos = new Loads.comandos();
                    String valor = comandos.Inserir(Nome.getText().toString(),"00:00:01",getBaseContext());
                    List<LoadTable> listaDeLoads = comandos.buscaLoad(db);
                    List<JogoTable> listaDeClasses = comandos.buscaClasses(db);
                    int load_id = -1;
                    for (int i = 0;i<listaDeLoads.size();i++){
                        if((Nome.getText().toString()).equals(listaDeLoads.get(i).getNome())){
                            load_id = listaDeLoads.get(i).getId();
                            break;
                        }
                    }
                    if((load_id!=-1)){
                        comandos.InserirDados(nome,classe,load_id,db);
                    }else{
                        visualizar("Erro ao cadastrar novo salve","Erro");
                        Intent it =  new Intent(NovoJogo.this,MainActivity.class);
                        startActivity(it);
                    }
                    banco.close();
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
        alert.create();
        alert.show();
    }

    private void geraLista(){
        SQLiteDatabase db = banco.getReadableDatabase();
        String[] projection = {
                "id",
                "nome",
                "tempo"
        };
        Cursor cursor = db.query(
                Loads.load.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        LoadTable load;
        List<LoadTable> loads = new ArrayList<LoadTable>();
        while (cursor.moveToNext()){
            load = new LoadTable();
            load.setId(cursor.getInt(0));
            load.setNome(cursor.getString(1));
            load.setTempo(cursor.getString(2));
            loads.add(load);
        }
        ArrayAdapter<LoadTable> adapter = new ArrayAdapter<LoadTable>(this,
                android.R.layout.simple_list_item_1, loads);
        lista.setAdapter(adapter);
    }

    private boolean validador(Cursor cursor){
        int cont = 0;
        boolean nomeRepetido = false;
        LoadTable load;
        List<LoadTable> loads = new ArrayList<LoadTable>();
        while (cursor.moveToNext()){
            load = new LoadTable();
            load.setId(cursor.getInt(0));
            load.setNome(cursor.getString(1));
            load.setTempo(cursor.getString(2));
            if(Nome.getText().toString().equals(cursor.getString(1))){
                nomeRepetido = true;
            }
            loads.add(load);
            cont++;
        }

        if(cont>=3){
            ArrayAdapter<LoadTable> adapter = new ArrayAdapter<LoadTable>(this,
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
                    banco.close();
                    Intent it =  new Intent(NovoJogo.this,MainActivity.class);
                    startActivity(it);
                }
            });
            alert.create();
            alert.show();

        }else if(nomeRepetido){
            visualizar("Nome de salve Repetido","Alerta");
        }else{
            return true;
        }
        return false;
    }

    //1 para tornar layoutJogo2 invisivel, layout invisivel e layoutJogo visivel// 2 para tornar layoutJogo invisivel e layoutJogo2 visivel
    //3 para tornar layoutJogo2 invisivel, layout visivel e layoutJogo invisivel
    private void VisibleInviseble2(int UmOuDois){
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutJogo);
        LinearLayout layout2 = (LinearLayout) findViewById(R.id.layoutJogo2);
        LinearLayout layout3 = (LinearLayout) findViewById(R.id.layout);
        switch (UmOuDois){
            case 1:
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
                layout2.setVisibility(View.INVISIBLE);
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
