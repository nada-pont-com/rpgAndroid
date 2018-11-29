package br.com.vinicius.rpg;

import android.app.AlertDialog;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

public class Jogo extends AppCompatActivity {

    private String load_id;
    private Button avancar;
    private Button avancar2;
    private Button voltar;
    private RadioButton classGue;
    private RadioButton classAven;
    private EditText nomePerso;
    private String classe;
    private Bd banco = new  Bd(getBaseContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        avancar = (Button) findViewById(R.id.Avancar);
        avancar2 = (Button) findViewById(R.id.Avancar2);
        voltar = (Button) findViewById(R.id.Voltar2);
        nomePerso = (EditText) findViewById(R.id.NomePerso);
        classGue = (RadioButton) findViewById(R.id.ClasseGue);
        classAven = (RadioButton) findViewById(R.id.ClasseAven);

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
                VisibleInviseble(true);
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
                    List<JogoTable> listaDeClasses = comandos.buscaClasses(db);
                }
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
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

    // True para tornar layout invisivel e layout2 visivel // False para tornar layout2 invisivel e layout visivel
    private void VisibleInviseble(Boolean UmOuDois){
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutJogo);
        LinearLayout layout2 = (LinearLayout) findViewById(R.id.layoutJogo2);
        if(UmOuDois){
            layout2.setVisibility(View.VISIBLE);
            layout.setVisibility(View.INVISIBLE);
        }else {
            layout.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.INVISIBLE);
        }
    }
}
