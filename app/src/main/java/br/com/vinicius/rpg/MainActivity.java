package br.com.vinicius.rpg;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button NovoJogo;
    private Button Continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bd banco = new Bd(getBaseContext());
        SQLiteDatabase db = banco.getWritableDatabase();
        boolean retorno = Loads.classes.dados();
        if(retorno){
            Loads.comandos comandos = new Loads.comandos();
            boolean teste = comandos.InserirClasses(db);
            visualizar(teste,"Teste");
            banco.close();
        }
        //System.exit(-1);
        NovoJogo = (Button) findViewById(R.id.NovoJogo);
        Continuar = (Button) findViewById(R.id.Continuar);

        NovoJogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it  = new Intent(MainActivity.this,NovoJogo.class);
                startActivity(it);
            }
        });
        Continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}
