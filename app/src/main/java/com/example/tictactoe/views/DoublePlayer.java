package com.example.tictactoe.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.res.Configuration;

import com.example.tictactoe.R;
import com.example.tictactoe.adapters.EmptyBoardGridAdapter;
import com.example.tictactoe.matrix.BackendMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoublePlayer extends AppCompatActivity {
    int[] board_cells_array = new int[100];
    public int[] pink_point_list = {R.drawable.pink_0,R.drawable.pink_1,R.drawable.pink_2,R.drawable.pink_3,R.drawable.pink_4,R.drawable.pink_5,R.drawable.pink_6,R.drawable.pink_7,
            R.drawable.pink_8,R.drawable.pink_9,R.drawable.pink_10,R.drawable.pink_11,R.drawable.pink_12,R.drawable.pink_13,R.drawable.pink_14,R.drawable.pink_15,R.drawable.pink_16,
            R.drawable.pink_17,R.drawable.pink_18,R.drawable.pink_19,R.drawable.pink_20,R.drawable.pink_21,R.drawable.pink_22,R.drawable.pink_23,R.drawable.pink_24,R.drawable.pink_25,
            R.drawable.pink_26, R.drawable.pink_27, R.drawable.pink_28, R.drawable.pink_29, R.drawable.pink_30, R.drawable.pink_31,R.drawable.pink_32,R.drawable.pink_33,R.drawable.pink_34,
            R.drawable.pink_35,R.drawable.pink_36,R.drawable.pink_37,R.drawable.pink_38,R.drawable.pink_39,R.drawable.pink_40,R.drawable.pink_41,R.drawable.pink_42,R.drawable.pink_43,
            R.drawable.pink_44,R.drawable.pink_45,R.drawable.pink_46,R.drawable.pink_47,R.drawable.pink_48,R.drawable.pink_49,R.drawable.pink_50};
    public int[] blue_point_list = {R.drawable.blue_0,R.drawable.blue_1,R.drawable.blue_2,R.drawable.blue_3,R.drawable.blue_4,R.drawable.blue_5,R.drawable.blue_6,R.drawable.blue_7,
            R.drawable.blue_8,R.drawable.blue_9,R.drawable.blue_10,R.drawable.blue_11,R.drawable.blue_12,R.drawable.blue_13,R.drawable.blue_14,R.drawable.blue_15,R.drawable.blue_16,
            R.drawable.blue_17,R.drawable.blue_18,R.drawable.blue_19,R.drawable.blue_20,R.drawable.blue_21,R.drawable.blue_22,R.drawable.blue_23,R.drawable.blue_24,R.drawable.blue_25,
            R.drawable.blue_26, R.drawable.blue_27, R.drawable.blue_28, R.drawable.blue_29, R.drawable.blue_30, R.drawable.blue_31,R.drawable.blue_32,R.drawable.blue_33,R.drawable.blue_34,
            R.drawable.blue_35,R.drawable.blue_36,R.drawable.blue_37,R.drawable.blue_38,R.drawable.blue_39,R.drawable.blue_40,R.drawable.blue_41,R.drawable.blue_42,R.drawable.blue_43,
            R.drawable.blue_44,R.drawable.blue_45,R.drawable.blue_46,R.drawable.blue_47,R.drawable.blue_48,R.drawable.blue_49,R.drawable.blue_50};
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

        switch (getResources().getConfiguration().orientation){
            case Configuration.ORIENTATION_LANDSCAPE:
                setContentView(R.layout.landscape_player);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                setContentView(R.layout.activity_double_player);
                break;
        }



        final GridView gameBoard = (GridView) findViewById(R.id.board_grid_view);
        Button restartGameButton = (Button) findViewById(R.id.restartGame);
        Button endGame = (Button) findViewById(R.id.endGame);
        final ImageView points_pink = (ImageView) findViewById(R.id.points_pink);
        final ImageView points_blue = (ImageView) findViewById(R.id.points_blue);


        System.out.println("saved instance " + savedInstanceState);
        if (savedInstanceState != null){
            board_cells_array = savedInstanceState.getIntArray("Board cells");
            backendMatrix = (BackendMatrix) savedInstanceState.getSerializable("Backend matrix");
            playerPinkTurn = savedInstanceState.getBoolean("Player turn");
            pinkWins = savedInstanceState.getInt("Pink wins");
            blueWins = savedInstanceState.getInt("Blue wins");
            points_pink.setImageResource(pink_point_list[pinkWins]);
            points_blue.setImageResource(blue_point_list[blueWins]);

        }
        else{
            cleanGameBoard();
            backendMatrix = new BackendMatrix(gridSize);
            playerPinkTurn = true;
            clearPinkScore(points_pink);
            clearBlueScore(points_blue);
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
                    board_cells_array[position] = R.drawable.pink1;
                    backendMatrix.setMatrixCell(position, 1);
                }
                else{
                    board_cells_array[position] = R.drawable.blue1;
                    backendMatrix.setMatrixCell(position, 2);
                    }
                already_used_cells.add(position);
                boardAdapter.notifyDataSetChanged();

                if (backendMatrix.areFiveConnected(1)){
                    pinkHasWon();
                    points_pink.setImageResource(pink_point_list[pinkWins]);
                }
                else if (backendMatrix.areFiveConnected(2)){
                    blueHasWon();
                    points_blue.setImageResource(blue_point_list[blueWins]);
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

        endGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("pinkWins",pinkWins);
                bundle.putInt("blueWins", blueWins);
                Intent intent = new Intent(getApplicationContext(), EndGameDouble.class);
                intent.putExtras(bundle);
                startActivity(intent);
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
        Arrays.fill(board_cells_array,R.drawable.empty_ring1);
        already_used_cells.clear();
    }
    protected void restartGame(){
        cleanGameBoard();
        boardAdapter.notifyDataSetChanged();
        roundCount++;
        playerPinkTurn = roundCount % 2 == 0;
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
    protected void clearPinkScore(ImageView pink_points){
        pink_points.setImageResource(pink_point_list[0]);
    }
    protected void clearBlueScore(ImageView blue_points){
        blue_points.setImageResource(blue_point_list[0]);
    }
}
