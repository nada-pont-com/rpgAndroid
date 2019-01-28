package br.com.vinicius.rpg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterDungeonsPersonalizado extends BaseAdapter {
    private List<DungeonTable> listaDeDungeons;
    private Activity activity;

    public AdapterDungeonsPersonalizado(List<DungeonTable> listaDeDungeons, Activity activity) {
        this.listaDeDungeons = listaDeDungeons;
        this.activity = activity;

    }

    @Override
    public int getCount() {
        return listaDeDungeons.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDeDungeons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = activity.getLayoutInflater().inflate(R.layout.dungeons_layout,parent,false);
        DungeonTable dungeonT = listaDeDungeons.get(position);
        TextView andares = view.findViewById(R.id.Andares);
        TextView dungeon = view.findViewById(R.id.Dungeon);

        andares.setText("Andares: "+dungeonT.getAndares());
        dungeon.setText(dungeonT.getNome());
        return view;
    }
}
