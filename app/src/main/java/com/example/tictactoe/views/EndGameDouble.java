package com.example.tictactoe.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tictactoe.R;
import com.example.tictactoe.database.DatabaseHelper;

import java.util.ArrayList;


public class EndGameDouble extends AppCompatActivity {
    private DatabaseHelper db;
    private int pinkWins;
    private int blueWins;
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


    public EndGameDouble(){
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game_double_layout);


        Button end_without_saving = (Button) findViewById(R.id.skip_saving);
        Button save_and_open_board = (Button) findViewById(R.id.save_to_score_board);
        final EditText pink_name = (EditText) findViewById(R.id.enter_pink_name);
        final EditText blue_name = (EditText) findViewById(R.id.enter_blue_name);
        final ImageView points_pink = (ImageView) findViewById(R.id.points_pink);
        final ImageView points_blue = (ImageView) findViewById(R.id.points_blue);
        db = new DatabaseHelper(this);
        getScores();
        points_pink.setImageResource(pink_point_list[pinkWins]);
        points_blue.setImageResource(blue_point_list[blueWins]);


        //points_blue.setImageResource(blue_score_image);



        save_and_open_board.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String pink_name_to_save= pink_name.getText().toString();
                String blue_name_to_save = blue_name.getText().toString();

                if(!TextUtils.isEmpty(pink_name_to_save)){
                db.insertScore(pink_name.getText().toString(), String.valueOf(pinkWins));}
                if(!TextUtils.isEmpty(blue_name_to_save)){
                db.insertScore(blue_name.getText().toString(),String.valueOf(blueWins));}
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
            blueWins = bundle.getInt("blueWins");
        }
    }


}
