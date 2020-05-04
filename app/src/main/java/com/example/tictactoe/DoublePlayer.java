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

public class DoublePlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_player);
        final GridView gameBoard = (GridView) findViewById(R.id.board_grid_view);
        Button restartGameButton = (Button) findViewById(R.id.restartGame);
        //Instance of ImageAdapter Class
        clearGameBoard(gameBoard);

        //On Click event for Single GridView Item

        gameBoard.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageView = (ImageView) view;
                imageView.setImageResource(R.drawable.pink);
            }

        });

        restartGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clearGameBoard(gameBoard);
            }
        });

    }

    private void clearGameBoard(GridView gameBoard){
        gameBoard.setAdapter(new EmptyBoardGridAdapter(this));
    }
}
