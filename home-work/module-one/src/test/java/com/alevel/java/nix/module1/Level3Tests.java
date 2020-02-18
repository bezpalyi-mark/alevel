package com.alevel.java.nix.module1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Level3Tests {
    @Test
    public void nextLifeStepTest() {
        final int[][] input = {
                {0, 0, 1, 1, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0}
        };
        final int[][] expected = {
                {0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        Level3 level3 = new Level3();
        int[][] actual = level3.nextLifeStep(input);
        level3.print2dArray(actual);
        assertArrayEquals(expected, actual);
    }
}
