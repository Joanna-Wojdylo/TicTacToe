package com.example.tictactoe.database;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "tictactoe_database";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(Items.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Items.TABLE_NAME);

        // Create tables again
        onCreate(db);

    }
    public long insertScore(String name, String score) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //ContentValues() is used to define the column name and its data to be stored.
        values.put(Items.COLUMN_NAME, name);
        values.put(Items.COLUMN_SCORE, score);

        // insert row
        long row = db.insert(Items.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return row;
    }
    public List<Items> getAllScores() {
        List<Items> items = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Items.TABLE_NAME + " ORDER BY " +
                Items.COLUMN_SCORE + " DESC LIMIT " + Items.number_of_rows;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Items item = new Items();
                item.setName(cursor.getString(cursor.getColumnIndex(Items.COLUMN_NAME)));
                item.setScore(cursor.getString(cursor.getColumnIndex(Items.COLUMN_SCORE)));

                items.add(item);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return items;
    }
}
