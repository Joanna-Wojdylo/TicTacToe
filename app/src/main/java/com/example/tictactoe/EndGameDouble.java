package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EndGameDouble extends AppCompatActivity {

    public EndGameDouble(){
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game_double_layout);

        Button end_without_saving = (Button) findViewById(R.id.skip_saving);
        Button save_and_open_board = (Button) findViewById(R.id.save_to_score_board);
        EditText pink_name = (EditText) findViewById(R.id.enter_pink_name);
        EditText blue_name = (EditText) findViewById(R.id.enter_blue_name);


        save_and_open_board.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(), EndGameDouble.class);
                //startActivity(intent);
            }
        });

        end_without_saving.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
