package com.example.tictactoe.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tictactoe.R;
import com.example.tictactoe.database.Items;

import java.util.List;

public class ScoreListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<Items> itemsList;

    public ScoreListAdapter(Context context, List<Items> itemsList) {
        this.mContext = context;
        this.itemsList = itemsList;

    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View scoreView, ViewGroup parent) {
        ViewHolder holder;
        if (inflater == null) {
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (scoreView == null) {

            scoreView = inflater.inflate(R.layout.score_list_row, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) scoreView.findViewById(R.id.name);
            holder.score = (TextView) scoreView.findViewById(R.id.score);

            scoreView.setTag(holder);

        } else {
            holder = (ViewHolder) scoreView.getTag();
        }

        final Items m = itemsList.get(position);
        holder.name.setText(m.getName());
        holder.score.setText(m.getScore());

        return scoreView;
    }

    static class ViewHolder {

        TextView name;
        TextView score;
    }
}
