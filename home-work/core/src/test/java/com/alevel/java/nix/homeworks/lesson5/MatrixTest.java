package com.alevel.java.nix.homeworks.lesson5;

import com.alevel.java.nix.homeworks.lesson5.implementation.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MatrixTest {
    int[][] inputMatrix = {
            {1, 3, 0, -5, 3},
            {0, 3, 4, 5, 1},
            {8, 2, 5, -2, 5}
    };

    int[][] outputMatrix = {
            {1, 0, 8},
            {3, 3, 2},
            {0, 4, 5},
            {-5, 5, -2},
            {3, 1, 5}
    };

    @Test
    void testTransposeMatrix() {
        Matrix matrix = new Matrix(inputMatrix);
        matrix.transposeMatrix();
        int[][] actual = matrix.getMatrixByArray();
        assertArrayEquals(outputMatrix, actual);
    }
}
