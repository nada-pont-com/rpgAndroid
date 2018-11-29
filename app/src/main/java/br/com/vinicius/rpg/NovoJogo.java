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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NovoJogo extends AppCompatActivity {

    private Button Criar;
    private Button Excluir;
    private Button Voltar;
    private EditText Nome;
    private TextView textView;
    private ListView lista;
    private Bd banco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_jogo);
        banco = new  Bd(getBaseContext());
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
                        String valor = Loads.comandos.Inserir(Nome.getText().toString(),"00:00:01",getBaseContext());
                        if(valor.equals("Tudo certo")){
                            Intent it =  new Intent(NovoJogo.this,Jogo.class);
                            startActivity(it);
                            banco.close();
                        }
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
                    visualizar("Selecione um salve para excluir");
                }
            }
        });

        Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisibleInviseble(false);
            }
        });
    }

    private void visualizar(Object item){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Titulo");
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
        LoadTable load;
        List<LoadTable> loads = new ArrayList<LoadTable>();
        while (cursor.moveToNext()){
            load = new LoadTable();
            load.setId(cursor.getInt(0));
            load.setNome(cursor.getString(1));
            load.setTempo(cursor.getString(2));
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
                    Intent it =  new Intent(NovoJogo.this,MainActivity.class);
                    startActivity(it);
                    banco.close();
                }
            });
            alert.create();
            alert.show();

        }else{
            return true;
        }
        return false;
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
