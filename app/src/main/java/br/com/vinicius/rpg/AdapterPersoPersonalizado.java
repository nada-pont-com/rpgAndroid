package br.com.vinicius.rpg;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class AdapterPersoPersonalizado extends BaseAdapter {
    private final List<DadosTable> dados;
    private final Activity activity;

    public AdapterPersoPersonalizado(List<DadosTable> dados, Activity activity) {
        this.dados = dados;
        this.activity = activity;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.perso_layout,parent,false);
        DadosTable dado = dados.get(position);

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
        ProgressBar ExpBar = view.findViewById(R.id.progressExp);
        ProgressBar VidaBar = view.findViewById(R.id.progressVida);

        int level = dado.getLevel();
        System.out.println(Math.pow(2,(level-1)));
        double i = Math.pow(2,(level-1));
        int expMax = (int) (100*i);
        System.out.println("VidaMax: "+dado.getVidaMax());
        System.out.println("Vida: "+dado.getVida());
        Nome.setText(dado.getNome());
        Exp.setText(dado.getExperiencia()+"/"+expMax);
        Level.setText("Level: "+dado.getLevel());
        Classe.setText("Classe: "+dado.getClasse());
        Vida.setText(dado.getVida()+"/"+dado.getVidaMax());
        Atk.setText("Atk: "+dado.getAtk());
        Def.setText("Def: "+dado.getDef());
        AtkM.setText("AtkM: "+dado.getAtkM());
        DefM.setText("DefM: "+dado.getDefM());
        Agi.setText("Agi: "+dado.getAgi());
        Mp.setText("Mp: "+dado.getMp()+"/"+dado.getMpMax());
        ExpBar.setProgress(dado.getExperiencia());
        ExpBar.setMax(dado.getVidaMax());
        VidaBar.setProgress(dado.getVida());
        VidaBar.setMax(expMax);

        return view;
    }
}