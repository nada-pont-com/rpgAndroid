package br.com.vinicius.rpg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Loads.itensPerso.Itens();
        //System.exit(-1);
        Button NovoJogo = findViewById(R.id.NovoJogo);
        Button Continuar = findViewById(R.id.Continuar);

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
                Intent it  = new Intent(MainActivity.this,Continuar.class);
                startActivity(it);
            }
        });

    }
   /*
    private void visualizar(Object item,String titulo){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(titulo);
        alert.setMessage(item.toString());
        alert.create();
        alert.show();
    }
    */
}
