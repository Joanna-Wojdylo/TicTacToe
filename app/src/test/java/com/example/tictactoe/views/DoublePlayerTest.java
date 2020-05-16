package com.example.tictactoe.views;

import android.widget.ImageView;

import com.example.tictactoe.R;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

import static org.junit.Assert.*;

public class DoublePlayerTest {
    private DoublePlayer doublePlayer = new DoublePlayer();
    private int gridSize = 10;
    private int full = (int) Math.pow(gridSize, 2);



    @Test
    public void cleanGameBoard() {
        int[] test_board_cells = new int[100];
        Arrays.fill(test_board_cells, R.drawable.empty_ring1);
        doublePlayer.cleanGameBoard();
        assertArrayEquals(test_board_cells,doublePlayer.board_cells_array);
    }

    @Test
    public void isBoardFull() {
        for (int i=0; i<full; i++){
        doublePlayer.already_used_cells.add(i);}
        assertTrue(doublePlayer.isBoardFull());
    }



}