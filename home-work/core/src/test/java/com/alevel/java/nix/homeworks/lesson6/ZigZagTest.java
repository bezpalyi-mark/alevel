package com.alevel.java.nix.homeworks.lesson6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZigZagTest {
    @Test
    void testZigZagConversion() {
        final String input = "PAYPALISHIRING";
        final int[] cols = {3, 4, 5};
        final String[] outputs = {"PAHNAPLSIIGYIR", "PINALSIGYAHRPI", "PHASIYIRPLIGAN"};

        String actual = SolutionZigZag.zigZagConversion(input, cols[0]);
        assertEquals(outputs[0], actual);

        actual = SolutionZigZag.zigZagConversion(input, cols[1]);
        assertEquals(outputs[1], actual);

        actual = SolutionZigZag.zigZagConversion(input, cols[2]);
        assertEquals(outputs[2], actual);
    }
}
