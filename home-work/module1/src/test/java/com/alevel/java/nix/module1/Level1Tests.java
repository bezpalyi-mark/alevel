package com.alevel.java.nix.module1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Level1Tests {
    @Test
    public void assertGetUniqueNumbersCount() {
        final int[][] inputs = {
                {1, 4, 5, 1, 1, 3},
                {2, 4, 7, 3, 2, 2, 7},
                {0, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {},
                {-1, 1, -1, 1, -2, 0}
        };
        final int[] expected = {4, 4, 1, 2, 0, 4};
        Level1 level1 = new Level1();
        for (int i = 0; i < inputs.length; i++) {
            int actual = level1.getUniqueNumbersCount(inputs[i]);
            assertEquals(expected[i], actual);
        }
    }

    @Test
    public void assertIsValidHorsebackRiding() {
        int[][] inputsHorsePositions = {
                {4, 4},
                {-5, -3},
        };
        int[][] inputsFirstMovePositions = {
                {4, 4},
                {2, 4},
                {0, 0},
                {3, 6},
                {6, 3}
        };
        int[][] inputsSecondMovePositions = {
                {-4, -5}, //true
                {-5, -5}, //false
                {-7, -3}, //false
                {-3, -2}, //true
                {}, //false
                {1, 2, 3} //false
        };
        boolean[] expectedFirstResults = {false, false, false, true, true};
        boolean[] expectedSecondResults = {true, false, false, true, false, false};
        Level1 level1 = new Level1();
        for (int i = 0; i < expectedFirstResults.length; i++) {
            boolean actual = level1.isValidHorsebackRiding(inputsHorsePositions[0], inputsFirstMovePositions[i]);
            assertEquals(expectedFirstResults[i], actual);
        }
        for (int i = 0; i < expectedSecondResults.length; i++) {
            boolean actual = level1.isValidHorsebackRiding(inputsHorsePositions[1], inputsSecondMovePositions[i]);
            assertEquals(expectedSecondResults[i], actual);
        }
    }
}
