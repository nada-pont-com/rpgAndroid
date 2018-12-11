package br.com.vinicius.rpg;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterSalvesPersonalizado extends BaseAdapter {

    private final List<LoadTable> listaDeLoad;
    private final Activity activity;

    public AdapterSalvesPersonalizado(List<LoadTable> listaDeLoad, Activity activity) {
        this.listaDeLoad = listaDeLoad;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listaDeLoad.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDeLoad.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaDeLoad.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.salve_layout,parent,false);
        LoadTable load = listaDeLoad.get(position);
        Bd banco = new Bd(activity.getBaseContext());
        SQLiteDatabase db = banco.getWritableDatabase();
        List<DadosTable> dados = new Loads.comandos().buscaDadosPorLoadId(db,load.getId(),0);
        DadosTable dado = dados.get(0);
        TextView nome = (TextView)
                view.findViewById(R.id.Nome);
        TextView tempo = (TextView)
                view.findViewById(R.id.Tempo);
        TextView classe = (TextView)
                view.findViewById(R.id.Classe);
        //populando as Views
        nome.setText(load.getNome());
        tempo.setText(load.getTempo());
        classe.setText(dado.getClasse());

        return view;
    }
}