package com.example.tictactoe.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tictactoe.R;
import com.example.tictactoe.database.DatabaseHelper;

public class EndGameSingle extends AppCompatActivity {
    private DatabaseHelper db;
    private int pinkWins;
    public int[] pink_point_list = {R.drawable.pink_0,R.drawable.pink_1,R.drawable.pink_2,R.drawable.pink_3,R.drawable.pink_4,R.drawable.pink_5,R.drawable.pink_6,R.drawable.pink_7,
            R.drawable.pink_8,R.drawable.pink_9,R.drawable.pink_10,R.drawable.pink_11,R.drawable.pink_12,R.drawable.pink_13,R.drawable.pink_14,R.drawable.pink_15,R.drawable.pink_16,
            R.drawable.pink_17,R.drawable.pink_18,R.drawable.pink_19,R.drawable.pink_20,R.drawable.pink_21,R.drawable.pink_22,R.drawable.pink_23,R.drawable.pink_24,R.drawable.pink_25,
            R.drawable.pink_26, R.drawable.pink_27, R.drawable.pink_28, R.drawable.pink_29, R.drawable.pink_30, R.drawable.pink_31,R.drawable.pink_32,R.drawable.pink_33,R.drawable.pink_34,
            R.drawable.pink_35,R.drawable.pink_36,R.drawable.pink_37,R.drawable.pink_38,R.drawable.pink_39,R.drawable.pink_40,R.drawable.pink_41,R.drawable.pink_42,R.drawable.pink_43,
            R.drawable.pink_44,R.drawable.pink_45,R.drawable.pink_46,R.drawable.pink_47,R.drawable.pink_48,R.drawable.pink_49,R.drawable.pink_50};


    public EndGameSingle(){
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game_single_layout);


        ImageButton end_without_saving = findViewById(R.id.skip_saving_single);
        ImageButton save_and_open_board = findViewById(R.id.save_to_score_board_single);
        final EditText pink_name = findViewById(R.id.enter_pink_name_single);
        final ImageView points_pink = findViewById(R.id.points_pink);
        db = new DatabaseHelper(this);
        getScores();
        points_pink.setImageResource(pink_point_list[pinkWins]);


        save_and_open_board.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String pink_name_to_save= pink_name.getText().toString();


                if(!TextUtils.isEmpty(pink_name_to_save)){
                    db.insertScore(pink_name.getText().toString(), pinkWins);}
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
