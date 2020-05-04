package com.example.tictactoe;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class EmptyBoardGridAdapter extends BaseAdapter {

    private Context mContext;
    public EmptyBoardGridAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView single_cell_image = new ImageView(mContext);
        single_cell_image.setImageResource(R.drawable.empty_ring);
        single_cell_image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        single_cell_image.setLayoutParams(new GridView.LayoutParams(80,80));

        return single_cell_image;
    }
}
