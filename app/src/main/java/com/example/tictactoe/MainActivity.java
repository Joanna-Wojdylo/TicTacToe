package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button singlePlayer, doublePlayer, scoreBoard, gameExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //defining buttons
        singlePlayer = (Button) findViewById(R.id.single_player);
        doublePlayer = (Button) findViewById(R.id.double_player);
        scoreBoard = (Button) findViewById(R.id.score_board);
        gameExit = (Button) findViewById(R.id.game_exit);
        //adding click listeners to buttons
        singlePlayer.setOnClickListener(this);
        doublePlayer.setOnClickListener(this);
        scoreBoard.setOnClickListener(this);
        gameExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()){
        case R.id.single_player : intent = new Intent(this,SinglePlayer.class);startActivity(intent); break;
        case R.id.double_player : intent = new Intent(this,DoublePlayer.class);startActivity(intent); break;
        case R.id.score_board : intent =  new Intent(this,ScoreBoard.class); startActivity(intent);break;
        case R.id.game_exit : finish();break;
        default: break;
        }

    }
}
