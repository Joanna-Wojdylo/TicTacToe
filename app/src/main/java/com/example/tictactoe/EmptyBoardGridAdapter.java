package com.example.tictactoe;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmptyBoardGridAdapter extends BaseAdapter {

    private Context mContext;
    public int[] board_cells_array = new int[100];
    //public int[] already_used_cells = new int[100];
    public List<Integer> already_used_cells = new ArrayList<>(); // !we work on interface not class

    public EmptyBoardGridAdapter(Context mContext, int[] board_cells_array, List<Integer> already_used_cells) {
        this.mContext = mContext;
        this.board_cells_array = board_cells_array;
        this.already_used_cells = already_used_cells;
    }

    @Override
    public int getCount() {
        return board_cells_array.length;
    }

    @Override
    public Object getItem(int position) {
        return board_cells_array[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView single_cell_image = new ImageView(mContext);
        single_cell_image.setImageResource(board_cells_array[position]);
        single_cell_image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        single_cell_image.setLayoutParams(new GridView.LayoutParams(80,80));

        return single_cell_image;
    }
    public void setEmptyBoardArray(int[] board_cells_array){
        Arrays.fill(board_cells_array,R.drawable.empty_ring);
    };
    @Override
    public boolean isEnabled(int position)
    {
        if(already_used_cells.contains(position))
            return false;
        else
            return true;
    }
}
