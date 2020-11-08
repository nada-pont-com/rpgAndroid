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
    private final List<PersoTable> perso;
    private final Activity activity;
    private int selected = -1;
    private int estado = -1;

    public AdapterBattlePersoPersonalizado(List<PersoTable> perso, Activity activity) {
        this.perso = perso;
        this.activity = activity;
    }

    public void select(int position,int estado) {
        this.selected = position;
        this.estado = estado;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return perso.size();
    }

    @Override
    public Object getItem(int position) {
        return perso.get(position);
    }

    @Override
    public long getItemId(int position) {
        return perso.get(position).getId();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = activity.getLayoutInflater().inflate(R.layout.perso_battle,parent,false);
        TextView nome = view.findViewById(R.id.Nome);
        TextView level = view.findViewById(R.id.Level);
        TextView vida  = view.findViewById(R.id.NunberVida);
        TextView mp  = view.findViewById(R.id.NunberMp);
        ProgressBar vidaBar = view.findViewById(R.id.BarVida);
        ProgressBar mpBar = view.findViewById(R.id.BarMp);
        nome.setText(perso.get(position).getNome());
        level.setText("Lv "+perso.get(position).getLevel());
        vida.setText(perso.get(position).getVida()+"/"+perso.get(position).getVidaMax());
        vidaBar.setMax(perso.get(position).getVidaMax());
        vidaBar.setProgress(perso.get(position).getVida());
        mp.setText(perso.get(position).getMp()+"/"+perso.get(position).getMpMax());
        mpBar.setMax(perso.get(position).getMpMax());
        mpBar.setProgress(perso.get(position).getMp());
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
