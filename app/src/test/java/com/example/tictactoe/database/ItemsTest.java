package com.example.tictactoe.database;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemsTest {
    private String score = "20;";
    private String name = "Joan";
    Items items;

    public ItemsTest() {
        items = new Items(name, score);
    }

    @Test
    public void getName() {
        assertEquals(name,items.getName());
    }

    @Test
    public void setName() {
        String new_name = "Jon";
        items.setName(new_name);
        assertEquals(new_name,items.getName());
    }

    @Test
    public void getScore() {
        assertEquals(score, items.getScore());
    }

    @Test
    public void setScore() {
        String new_score = "30";
        items.setScore(new_score);
        assertEquals(new_score,items.getScore());
    }
}