package com.example.tictactoe.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.tictactoe.R;
import com.example.tictactoe.adapters.ScoreListAdapter;
import com.example.tictactoe.database.DatabaseHelper;
import com.example.tictactoe.database.Items;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard extends AppCompatActivity {
    private List<Items> itemsList = new ArrayList<>();

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_score_board);
        ImageButton back_to_menu = findViewById(R.id.back_to_menu);
        DatabaseHelper db = new DatabaseHelper(this);


        ListView listView = findViewById(R.id.score_list);
        ScoreListAdapter scoreListAdapter = new ScoreListAdapter(this, itemsList);
        listView.setAdapter(scoreListAdapter);

        itemsList.addAll(db.getAllScores());
        scoreListAdapter.notifyDataSetChanged();

        back_to_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
