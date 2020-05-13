package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.tictactoe.database.DatabaseHelper;
import com.example.tictactoe.database.Items;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard extends AppCompatActivity {
    private List<Items> itemsList = new ArrayList<Items>();
    private ListView listView;
    private ScoreListAdapter scoreListAdapter;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        db = new DatabaseHelper(this);


        listView = (ListView) findViewById(R.id.score_list);
        scoreListAdapter = new ScoreListAdapter(this, itemsList);
        listView.setAdapter(scoreListAdapter);

        itemsList.addAll(db.getAllScores());
        scoreListAdapter.notifyDataSetChanged();

    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
