package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tictactoe.database.DatabaseHelper;

public class EndGameSingle extends AppCompatActivity {
    private DatabaseHelper db;
    private int pinkWins;


    public EndGameSingle(){
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game_single_layout);


        Button end_without_saving = (Button) findViewById(R.id.skip_saving_single);
        Button save_and_open_board = (Button) findViewById(R.id.save_to_score_board_single);
        final EditText pink_name = (EditText) findViewById(R.id.enter_pink_name_single);
        db = new DatabaseHelper(this);
        getScores();


        save_and_open_board.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String pink_name_to_save= pink_name.getText().toString();


                if(!TextUtils.isEmpty(pink_name_to_save)){
                    db.insertScore(pink_name.getText().toString(), String.valueOf(pinkWins));}
                Intent intent = new Intent(getApplicationContext(), ScoreBoard.class);
                startActivity(intent);

            }
        });

        end_without_saving.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getScores(){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            pinkWins = bundle.getInt("pinkWins");

        }
    }


}
