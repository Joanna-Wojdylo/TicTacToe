package com.example.tictactoe.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tictactoe.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button singlePlayer, doublePlayer, scoreBoard, gameExit;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()){
        case R.id.single_player : intent = new Intent(this,SinglePlayer.class);startActivity(intent); break;
        case R.id.double_player : intent = new Intent(this,DoublePlayer.class);startActivity(intent); break;
        case R.id.score_board : intent =  new Intent(this,ScoreBoard.class); startActivity(intent);break;
        case R.id.game_exit : finishAffinity(); System.exit(0); break;
        default: break;
        }

    }
}
