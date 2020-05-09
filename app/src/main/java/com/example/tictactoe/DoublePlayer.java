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
import java.util.List;

public class DoublePlayer extends AppCompatActivity {
    int[] board_cells_array = new int[100];
    List<Integer> already_used_cells = new ArrayList<>();
    private boolean playerPinkTurn;
    private int roundCount = 0; //based on round count we will start with pink or blue in turns - pair pink, odd blue
    private EmptyBoardGridAdapter boardAdapter;
    //data needed to create matrix
    private int numberOfColumns = 10;
    private int x;
    private int y;
    public DoublePlayer() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_player);

        final GridView gameBoard = (GridView) findViewById(R.id.board_grid_view);
        Button restartGameButton = (Button) findViewById(R.id.restartGame);



        System.out.println("saved instance " + savedInstanceState);
        if (savedInstanceState != null){
            board_cells_array = savedInstanceState.getIntArray("Board cells");
        }
        else{
            System.out.println(" yes it was " + null);
            cleanGameBoard();
        }
        System.out.println(Arrays.toString(board_cells_array));
        boardAdapter = new EmptyBoardGridAdapter(this, board_cells_array, already_used_cells);
        gameBoard.setAdapter(boardAdapter);

        playerPinkTurn = roundCount % 2 == 0; //for pair counted rounds pink player will start


        //On Click event for Single GridView Item

        gameBoard.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView single_cell_image = (ImageView) view;
                //single_cell_image.setImageResource(R.drawable.pink);

                //we need row and column instead of index to write down to matrix
                // x : horizontal position in range [0; columns-1]
                // y : vertical position in range [0; rows-1]

                x = position % numberOfColumns;
                y = position / numberOfColumns;


                if(playerPinkTurn){
                    board_cells_array[position] = R.drawable.pink;}
                else{
                    board_cells_array[position] = R.drawable.blue;}
                already_used_cells.add(position);
                boardAdapter.notifyDataSetChanged();
                playerPinkTurn = !playerPinkTurn;
                System.out.println(Arrays.toString(board_cells_array));
            }

        });

        restartGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                restartGame();
            }
        });

    }
    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putIntArray("Board cells", board_cells_array);
        System.out.println("Saved" + Arrays.toString(board_cells_array));
    }

    protected void cleanGameBoard(){
        Arrays.fill(board_cells_array,R.drawable.empty_ring);
        already_used_cells.clear();
    }
    protected void restartGame(){
        cleanGameBoard();
        boardAdapter.notifyDataSetChanged();
        roundCount++;
    }

}
