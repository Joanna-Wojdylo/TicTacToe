package com.example.tictactoe.database;

public class Items {
    public static final String TABLE_NAME = "scores";

    //public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SCORE = "score";
    public static int number_of_rows = 10;
    private String name;
    private int score;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_SCORE + " INTEGER"
                    + ")";

    public Items() {
    }

    public Items(String name, int score) {

        this.name = name;
        this.score = score;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
