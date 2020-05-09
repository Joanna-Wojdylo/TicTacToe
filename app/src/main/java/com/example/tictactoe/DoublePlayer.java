package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoublePlayer extends AppCompatActivity {
    int[] board_cells_array = new int[100];
    List<Integer> already_used_cells = new ArrayList<>();
    private boolean playerPinkTurn;
    private int roundCount = 0; //based on round count we will start with pink or blue in turns - pair pink, odd blue
    private int pinkWins = 0;
    private int blueWins = 0;
    private EmptyBoardGridAdapter boardAdapter;
    //data needed to create matrix
    private int gridSize = 10;
    private BackendMatrix backendMatrix;

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
            backendMatrix = (BackendMatrix) savedInstanceState.getSerializable("Backend matrix");
            playerPinkTurn = savedInstanceState.getBoolean("Player turn");
            pinkWins = savedInstanceState.getInt("Pink wins");
            blueWins = savedInstanceState.getInt("Blue wins");
            already_used_cells = savedInstanceState.getIntegerArrayList("Already used cells");
            System.out.println("Saved backend matrix ");
            backendMatrix.print2D();
        }
        else{
            cleanGameBoard();
            backendMatrix = new BackendMatrix(gridSize);
            playerPinkTurn = roundCount % 2 == 0;
            System.out.println("First backend matrix ");
            backendMatrix.print2D();
        }
        System.out.println(Arrays.toString(board_cells_array));
        boardAdapter = new EmptyBoardGridAdapter(this, board_cells_array, already_used_cells);
        gameBoard.setAdapter(boardAdapter);

         //for pair counted rounds pink player will start


        //On Click event for Single GridView Item

        gameBoard.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView single_cell_image = (ImageView) view;
                //single_cell_image.setImageResource(R.drawable.pink);


                if(playerPinkTurn){
                    board_cells_array[position] = R.drawable.pink;
                    backendMatrix.setMatrixCell(position, 1);
                }
                else{
                    board_cells_array[position] = R.drawable.blue;
                    backendMatrix.setMatrixCell(position, 2);
                    }
                already_used_cells.add(position);
                boardAdapter.notifyDataSetChanged();

                if (backendMatrix.areFiveConnected(1)){
                    pinkHasWon();
                }
                else if (backendMatrix.areFiveConnected(2)){
                    blueHasWon();
                }
                else if (isBoardFull()){
                    itsDraw();
                }
                else{
                    playerPinkTurn = !playerPinkTurn;
                }

                System.out.println("Already used cells " + already_used_cells.size());
                System.out.println("Backend matrix after click ");
                backendMatrix.print2D();
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
        state.putSerializable("Backend matrix", backendMatrix);
        state.putIntegerArrayList("Already used cells", (ArrayList<Integer>) already_used_cells);
        state.putBoolean("Player turn", playerPinkTurn);
        state.putInt("Pink wins", pinkWins);
        state.putInt("Blue wins", blueWins);
        System.out.println("Saved");
        backendMatrix.print2D();
    }

    protected void cleanGameBoard(){
        Arrays.fill(board_cells_array,R.drawable.empty_ring);
        already_used_cells.clear();
    }
    protected void restartGame(){
        cleanGameBoard();
        boardAdapter.notifyDataSetChanged();
        roundCount++;
        backendMatrix.clearBackendMatrix();
    }

    protected boolean isBoardFull(){
        if (already_used_cells.size() == Math.pow(gridSize, 2)) {
            return true;
        }
        else
            return false;
    }

    protected void itsDraw(){
        Toast toast = Toast.makeText(this, "IT'S A DRAW!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        restartGame();
    }

    protected void pinkHasWon(){
        Toast toast = Toast.makeText(this, "PINK HAS WON THE GAME!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        pinkWins++;
        System.out.println("Pink Wins: " + pinkWins);
        restartGame();
    }

    protected void blueHasWon(){
        Toast toast = Toast.makeText(this, "BLUE HAS WON THE GAME!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        blueWins++;
        System.out.println("Blue Wins: " + blueWins);
        restartGame();
    }

}
