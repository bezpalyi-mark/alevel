package com.alevel.java.nix.module1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
