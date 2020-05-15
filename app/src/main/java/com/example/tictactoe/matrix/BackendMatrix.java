package com.example.tictactoe.matrix;

import java.io.Serializable;
import java.util.Arrays;

public class BackendMatrix implements Serializable {
    public int[][] matrix;        // restricted to int elements only
    private int w;
    private int h;

    public BackendMatrix(int gridSize){
        w = gridSize;
        h = gridSize;
        matrix = new int[w][h];

        //at the beginning we fill up backend matrix with zeros
        for (int i=0; i<h; i++){
            for (int j=0; j<w; j++){
                matrix[i][j] = 0;
            }
        }
    }
    public void setMatrixCell(int position, int value){
        //we need row and column instead of index to write down to matrix
        // x : horizontal position in range [0; columns-1]
        // y : vertical position in range [0; rows-1]
        int x = position / w;
        int y = position % w;
        matrix[x][y] = value;
    }

    public void print2D() {
        // Loop through all rows
        for (int[] row : matrix)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }
    public boolean areFiveConnected(int player){

        // horizontalCheck
        for (int j = 0; j<h-4 ; j++ ){
            for (int i = 0; i<w; i++){
                if (matrix[i][j] == player && matrix[i][j+1] == player && matrix[i][j+2] == player && matrix[i][j+3] == player && matrix[i][j+4] == player){
                    return true;
                }
            }
        }
        // verticalCheck
        for (int i = 0; i<w-4 ; i++ ){
            for (int j = 0; j<h; j++){
                if (matrix[i][j] == player && matrix[i+1][j] == player && matrix[i+2][j] == player && matrix[i+3][j] == player && matrix[i+4][j] == player){
                    return true;
                }
            }
        }
        // ascendingDiagonalCheck
        for (int i=4; i<w; i++){
            for (int j=0; j<h-4; j++){
                if (matrix[i][j] == player && matrix[i-1][j+1] == player && matrix[i-2][j+2] == player && matrix[i-3][j+3] == player && matrix[i-4][j+4] == player)
                    return true;
            }
        }
        // descendingDiagonalCheck
        for (int i=4; i<w; i++){
            for (int j=4; j<h; j++){
                if (matrix[i][j] == player && matrix[i-1][j-1] == player && matrix[i-2][j-2] == player && matrix[i-3][j-3] == player && matrix[i-4][j-4] == player)
                    return true;
            }
        }
        return false;
    }

    public void clearBackendMatrix(){
        for (int i=0; i<h; i++){
            for (int j=0; j<w; j++){
                matrix[i][j] = 0;
            }
        }
    }
}
