package com.alevel.java.nix.homeworks.lesson5.implementation;

public class Matrix {
    private int[][] matrix;
    private int rowsCount;
    private int columnsCount;

    public void setRowsCount(int count) {
        rowsCount = count;
    }

    public void setColumnsCount(int columnsCount) {
        this.columnsCount = columnsCount;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int[][] getMatrixByArray() {
        return getMatrix();
    }

    public Matrix(int[][] array) {
        matrix = array;
        countMatrixSizes();
    }

    public void transposeMatrix() {
        int[][] newMatrix = new int[columnsCount][rowsCount];
        for (int i = 0; i < columnsCount; i++) {
            for (int j = 0; j < rowsCount; j++) {
                newMatrix[i][j] = matrix[j][i];
            }
        }
        matrix = newMatrix;
    }

    private void countMatrixSizes() {
        setColumnsCount(matrix[0].length);
        int count = 0;
        for (int[] ints : matrix) {
            count++;
        }
        setRowsCount(count);
    }
}
