package com.alevel.java.nix.homeworks.lesson3;

import com.alevel.java.nix.homeworks.lesson3.implementation.MinMax;
import com.alevel.java.nix.homeworks.lesson3.services.MinMaxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MinMaxTest {
    private MinMaxHandler minMaxHandler;
    private MinMaxService algorithmsService;
    private MinMax minMaxClass;

    private int[][] inputData = {
            {0, 1, 2, -8, 4},
            {2, 6, -9, 2, 0},
            {1, -3, 3, 9, 0}
    };

    private int[][] outputResults = {
            {-8, 4},
            {-9, 6},
            {-3, 9}
    };

    @BeforeEach
    void setUp() {
        minMaxHandler = mock(MinMaxHandler.class);
        algorithmsService = new MinMaxService(minMaxHandler);
        minMaxClass = new MinMax();
    }

    /**
     * Mock test.
     */
    @Test
    void testGetMinMax() {
        assertMinMax(inputData[0], outputResults[0]);
        assertMinMax(inputData[1], outputResults[1]);
        assertMinMax(inputData[2], outputResults[2]);
    }


    void assertMinMax(int[] array, int[] minMax) {
        List<Integer> minMaxExpected = new ArrayList<>();
        minMaxExpected.add(minMax[0]);
        minMaxExpected.add(minMax[1]);

        when(minMaxHandler.minMaxInArray(array)).thenReturn(minMaxExpected);

        List<Integer> minMaxActual = algorithmsService.getMinMax(array);
        assertEquals(minMaxExpected, minMaxActual);

        verify(minMaxHandler, only()).minMaxInArray(array);
        reset(minMaxHandler);
    }

    /**
     * Unit test.
     */
    @Test
    void testMinMaxInArray() {
        assertMinMaxInArray(inputData[0], outputResults[0]);
        assertMinMaxInArray(inputData[1], outputResults[1]);
        assertMinMaxInArray(inputData[2], outputResults[2]);
    }

    void assertMinMaxInArray(int[] array, int[] minMax) {
        List<Integer> expected = new ArrayList<>();
        expected.add(minMax[0]);
        expected.add(minMax[1]);

        List<Integer> actual = minMaxClass.minMaxInArray(array);
        assertEquals(expected, actual);
    }
}
