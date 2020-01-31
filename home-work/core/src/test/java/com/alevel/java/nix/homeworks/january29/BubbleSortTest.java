package com.alevel.java.nix.homeworks.january29;

import com.alevel.java.nix.homeworks.january29.implementation.BubbleSort;
import com.alevel.java.nix.homeworks.january29.services.BubbleSortService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BubbleSortTest {
    private BubbleSortService bubbleSortService;
    private SortHandler sortHandler;
    private BubbleSort bubbleSort;

    @BeforeEach
    void setUp() {
        sortHandler = mock(SortHandler.class);
        bubbleSortService = new BubbleSortService(sortHandler);
        bubbleSort = new BubbleSort();
    }

    int[][] inputData = {
            {1, 9, -3, 0, 5},
            {145, -1, 289, -23},
            {88, 87, 89, 90, 85}
    };

    /**
     * Mock test.
     */
    @Test
    void testGetBubbleSortedArray() {
        assertGetBubbleSort(inputData[0]);
        assertGetBubbleSort(inputData[1]);
        assertGetBubbleSort(inputData[2]);
    }


    void assertGetBubbleSort(int[] array) {
        int[] expected = new int[array.length];
        System.arraycopy(array, 0, expected, 0, array.length);
        Arrays.sort(expected);
        when(sortHandler.bubbleSort(array)).thenReturn(expected);

        int[] actual = bubbleSortService.getBubbleSortedArray(array);
        assertEquals(expected, actual);

        verify(sortHandler, only()).bubbleSort(array);
        reset(sortHandler);
    }

    /**
     * Unit test.
     */
    @Test
    void testBubbleSort() {
        assertBubbleSort(inputData[0]);
        assertBubbleSort(inputData[1]);
        assertBubbleSort(inputData[2]);
    }

    void assertBubbleSort(int[] array) {
        int[] expected = new int[array.length];
        System.arraycopy(array, 0, expected, 0, array.length);
        Arrays.sort(expected);

        int[] actual = bubbleSort.bubbleSort(array);
        assertArrayEquals(expected, actual);
    }

}
