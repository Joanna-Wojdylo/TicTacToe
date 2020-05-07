package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class DoublePlayer extends AppCompatActivity {
    int[] board_cells_array = new int[100];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_player);
        final GridView gameBoard = (GridView) findViewById(R.id.board_grid_view);
        Button restartGameButton = (Button) findViewById(R.id.restartGame);
        final EmptyBoardGridAdapter boardAdapter;


        System.out.println("saved instance " + savedInstanceState);
        if (savedInstanceState != null){
            board_cells_array = savedInstanceState.getIntArray("Board cells");
        }
        else{
            System.out.println(" yes it was " + null);
            cleanGameBoard(board_cells_array);
        }
        System.out.println(Arrays.toString(board_cells_array));
        boardAdapter = new EmptyBoardGridAdapter(this, board_cells_array);
        gameBoard.setAdapter(boardAdapter);


        //On Click event for Single GridView Item

        gameBoard.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageView = (ImageView) view;
                imageView.setImageResource(R.drawable.pink);
                board_cells_array[position] = R.drawable.pink;
                System.out.println(Arrays.toString(board_cells_array));
            }

        });

        restartGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cleanGameBoard(board_cells_array);
                boardAdapter.notifyDataSetChanged();
            }
        });

    }
    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putIntArray("Board cells", board_cells_array);
        System.out.println("Saved" + Arrays.toString(board_cells_array));

    }

    protected void cleanGameBoard(int[] board_cells_array){
        Arrays.fill(board_cells_array,R.drawable.empty_ring);
    }

}
