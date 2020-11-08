package br.com.vinicius.rpg.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import br.com.vinicius.rpg.objetosTabelas.PersoTable;
import br.com.vinicius.rpg.R;

//Adapter da lista de status do personagem
public class AdapterPersoPersonalizado extends BaseAdapter {
    private final List<PersoTable> persos;
    private final Activity activity;

    public AdapterPersoPersonalizado(List<PersoTable> persos, Activity activity) {
        this.persos = persos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return persos.size();
    }

    @Override
    public Object getItem(int position) {
        return persos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return persos.get(position).getId();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.perso_layout,parent,false);
        PersoTable perso = persos.get(position);

        TextView Nome = view.findViewById(R.id.Nome);
        TextView Exp = view.findViewById(R.id.ExperienciaNunber);
        TextView Level = view.findViewById(R.id.Level);
        TextView Classe = view.findViewById(R.id.Classe);
        TextView Vida = view.findViewById(R.id.Vida);

        TextView Atk = view.findViewById(R.id.atk);
        TextView Def = view.findViewById(R.id.def);
        TextView AtkM = view.findViewById(R.id.atkM);
        TextView DefM = view.findViewById(R.id.defM);
        TextView Agi = view.findViewById(R.id.agi);
        TextView Mp = view.findViewById(R.id.mp);
        TextView Int = view.findViewById(R.id.Int);
        TextView Vit = view.findViewById(R.id.Vit);
        ProgressBar ExpBar = view.findViewById(R.id.progressExp);
        ProgressBar VidaBar = view.findViewById(R.id.progressVida);

        Nome.setText(perso.getNome());
        Exp.setText(perso.getExperiencia()+"/"+perso.getExpMax());
        Level.setText("Level: "+perso.getLevel());
        Classe.setText("Classe: "+perso.getClasse());
        Vida.setText(perso.getVida()+"/"+perso.getVidaMax());
        Atk.setText("Atk: "+perso.getAtk());
        Def.setText("Def: "+perso.getDef());
        AtkM.setText("AtkM: "+perso.getAtkM());
        DefM.setText("DefM: "+perso.getDefM());
        Agi.setText("Agi: "+perso.getAgi());
        Mp.setText("Mp: "+perso.getMp()+"/"+perso.getMpMax());
        Int.setText("Int: "+perso.getIntl());
        Vit.setText("Vit: "+perso.getVit());
        ExpBar.setMax(perso.getExpMax());
        ExpBar.setProgress(perso.getExperiencia());
        VidaBar.setMax(perso.getVidaMax());
        VidaBar.setProgress(perso.getVida());

        return view;
    }
}
