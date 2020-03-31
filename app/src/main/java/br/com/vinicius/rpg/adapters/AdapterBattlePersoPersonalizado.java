package br.com.vinicius.rpg.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import br.com.vinicius.rpg.objetosTabelas.PersoTable;
import br.com.vinicius.rpg.R;


// Adapter da lista de status da batalha
public class AdapterBattlePersoPersonalizado extends BaseAdapter {
    private final List<PersoTable> dados;
    private final Activity activity;
    private int selected = -1;
    private int estado = -1;

    public AdapterBattlePersoPersonalizado(List<PersoTable> dados, Activity activity) {
        this.dados = dados;
        this.activity = activity;
    }

    public void select(int position,int estado) {
        this.selected = position;
        this.estado = estado;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dados.size();
    }

    @Override
    public Object getItem(int position) {
        return dados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dados.get(position).getId();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.perso_battle,parent,false);
        TextView nome = view.findViewById(R.id.Nome);
        TextView level = view.findViewById(R.id.Level);
        TextView vida  = view.findViewById(R.id.NunberVida);
        TextView mp  = view.findViewById(R.id.NunberMp);
        ProgressBar vidaBar = view.findViewById(R.id.BarVida);
        ProgressBar mpBar = view.findViewById(R.id.BarMp);
        nome.setText(dados.get(position).getNome());
        level.setText("Lv "+dados.get(position).getLevel());
        vida.setText(dados.get(position).getVida()+"/"+dados.get(position).getVidaMax());
        vidaBar.setMax(dados.get(position).getVidaMax());
        vidaBar.setProgress(dados.get(position).getVida());
        mp.setText(dados.get(position).getMp()+"/"+dados.get(position).getMpMax());
        mpBar.setMax(dados.get(position).getMpMax());
        mpBar.setProgress(dados.get(position).getMp());
        if(selected!=-1 && selected==position){
            LinearLayout fundo = view.findViewById(R.id.Fundo);
            if (estado==1){
                fundo.setBackgroundColor(Color.YELLOW);
            }else if (estado==0){
                fundo.setBackgroundColor(Color.TRANSPARENT);
            }
        }
        return view;
    }
}
