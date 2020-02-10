package com.alevel.java.nix.homeworks.lesson6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaterContainerTest {
    @Test
    void testMaxArea() {
        int[][] inputs = {
                {1, 8, 6, 2, 5, 4, 8, 3, 7},
                {8, 4, 1, 9, 1, 3, 5},
                {1, 2, 4, 7, 9, 2, 12}
        };
        int[] expected = {49, 30, 21};
        for(int i = 0; i < inputs.length; i++){
            int actual = WaterContainer.maxArea(inputs[i]);
            assertEquals(expected[i], actual);
        }
    }
}
