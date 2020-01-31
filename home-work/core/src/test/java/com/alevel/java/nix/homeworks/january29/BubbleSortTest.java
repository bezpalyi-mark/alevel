package com.alevel.java.nix.homeworks.january29;

import com.alevel.java.nix.homeworks.january29.interfaces.SortHandler;
import com.alevel.java.nix.homeworks.january29.services.BubbleSortService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BubbleSortTest {
    private BubbleSortService bubbleSortService;
    private SortHandler sortHandler;

    @BeforeEach
    void setUp() {
        sortHandler = mock(SortHandler.class);
        bubbleSortService = new BubbleSortService(sortHandler);
    }

    @Test
    void testGetBubbleSortedArray() {
        assertBubbleSort(new int[]{1, 9, -3, 0, 5});
        assertBubbleSort(new int[]{145, -1, 289, -23});
        assertBubbleSort(new int[]{88, 87, 89, 90, 85});
    }


    void assertBubbleSort(int[] array) {
        int[] expected = new int[array.length];
        System.arraycopy(array, 0, expected, 0, array.length);
        when(sortHandler.bubbleSort(array)).thenReturn(expected);

        int[] actual = bubbleSortService.getBubbleSortedArray(array);
        assertEquals(expected, actual);

        verify(sortHandler, only()).bubbleSort(array);
        reset(sortHandler);
    }
}
