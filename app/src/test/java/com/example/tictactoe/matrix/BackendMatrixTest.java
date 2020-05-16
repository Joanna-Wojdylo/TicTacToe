package com.example.tictactoe.matrix;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("deprecation")
public class BackendMatrixTest {
    private BackendMatrix backendMatrix;
    private int gridSize = 10;
    @Before
    public void setUp() {
        backendMatrix = new BackendMatrix(gridSize);
    }

    @Test
    public void setMatrixCell() {
        int position = 3;
        int w = gridSize;
        int h = gridSize;
        int value = 2;
        int[][] matrix_zeros = new int[w][h];

        //we fill up backend matrix with 1 to check if function will clear all fields to 0
        for (int i=0; i<h; i++){
            for (int j=0; j<w; j++){
                matrix_zeros[i][j] = 0;
            }
        }
        matrix_zeros[0][3] = value;
        backendMatrix.setMatrixCell(position,value);
        assertEquals(matrix_zeros,backendMatrix.matrix);

    }

    @Test
    public void areFiveConnectedHorizontal() {
        int player = 1;
        backendMatrix.matrix[0][0] = backendMatrix.matrix[0][1] = backendMatrix.matrix[0][2] = backendMatrix.matrix[0][3] = backendMatrix.matrix[0][4] = player;
        assertTrue(backendMatrix.areFiveConnected(player));
    }
    @Test
    public void areFiveConnectedVertical() {
        int player = 1;
        backendMatrix.matrix[0][0] = backendMatrix.matrix[1][0] = backendMatrix.matrix[2][0] = backendMatrix.matrix[3][0] = backendMatrix.matrix[4][0] = player;
        assertTrue(backendMatrix.areFiveConnected(player));
    }

    @Test
    public void areFiveConnectedAscendingDiagonal() {
        int player = 1;
        backendMatrix.matrix[4][0] = backendMatrix.matrix[3][1] = backendMatrix.matrix[2][2] = backendMatrix.matrix[1][3] = backendMatrix.matrix[0][4] = player;
        assertTrue(backendMatrix.areFiveConnected(player));
    }
    @Test
    public void areFiveConnectedDescendingDiagonal() {
        int player = 1;
        backendMatrix.matrix[0][0] = backendMatrix.matrix[1][1] = backendMatrix.matrix[2][2] = backendMatrix.matrix[3][3] = backendMatrix.matrix[4][4] = player;
        assertTrue(backendMatrix.areFiveConnected(player));
    }
    @Test
    public void clearBackendMatrix() {
        //here we check if our method sets all cells to 0
        int w = gridSize;
        int h = gridSize;
        int[][] matrix_zeros = new int[w][h];

        //we fill up backend matrix with 1 to check if function will clear all fields to 0
        for (int i=0; i<h; i++){
            for (int j=0; j<w; j++){
                matrix_zeros[i][j] = 0;
            }
        }
        //here I fill in backend matrix all with 1 on purpose, to see if clearBackendMatrix will return all zeros
        for (int i=0; i<h; i++){
            for (int j=0; j<w; j++){
                backendMatrix.matrix[i][j] = 1;
            }
        }
        backendMatrix.clearBackendMatrix();
        assertEquals(matrix_zeros,backendMatrix.matrix);
    }
}