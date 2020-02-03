package com.alevel.java.nix.homeworks.lesson3;

import com.alevel.java.nix.homeworks.lesson3.implementation.MultiplesNumbers;
import com.alevel.java.nix.homeworks.lesson3.services.MultiplesNumbersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;


public class MultiplesOfNumberTest {
    private MultiplesNumbersService multiplesNumbersService;
    private MultiplesOfNumberHandler multiplesOfNumberHandler;
    private MultiplesNumbers multiplesNumbers;

    int[][] inputArrays = {
            {2, 5, 8, 9, 3, 10},
            {1, 3, 5, 9, 8},
            {3, 4, 9, 5, 20}
    };

    int[] inputNumbers = {2, 3, 5};

    int[][] outputArrays = {
            {2, 8, 10},
            {3, 9},
            {5, 20}
    };

    @BeforeEach
    void setUp() {
        multiplesOfNumberHandler = mock(MultiplesOfNumberHandler.class);
        multiplesNumbersService = new MultiplesNumbersService(multiplesOfNumberHandler);
        multiplesNumbers = new MultiplesNumbers();
    }

    /**
     * Mock test.
     */
    @Test
    void testGetMultiplesOfNumber() {
        assertMultiplesOfNumber(inputArrays[0], inputNumbers[0], outputArrays[0]);
        assertMultiplesOfNumber(inputArrays[1], inputNumbers[1], outputArrays[1]);
        assertMultiplesOfNumber(inputArrays[2], inputNumbers[2], outputArrays[2]);
    }


    void assertMultiplesOfNumber(int[] array, int number, int[] expectedArray) {
        when(multiplesOfNumberHandler.getArrayWithMultiplesNumbers(array, number)).thenReturn(expectedArray);

        int[] actualArray = multiplesNumbersService.getMultiplesOfNumber(array, number);
        assertArrayEquals(expectedArray, actualArray);

        verify(multiplesOfNumberHandler, only()).getArrayWithMultiplesNumbers(array, number);
        reset(multiplesOfNumberHandler);
    }

    /**
     * Unit test.
     */
    @Test
    void testGetArrayWithMultiplesNumbers() {
        assertGetArrayWithMultiplesNumbers(inputArrays[0], inputNumbers[0], outputArrays[0]);
        assertGetArrayWithMultiplesNumbers(inputArrays[1], inputNumbers[1], outputArrays[1]);
        assertGetArrayWithMultiplesNumbers(inputArrays[2], inputNumbers[2], outputArrays[2]);
    }

    void assertGetArrayWithMultiplesNumbers(int[] array, int number, int[] expectedArray) {
        int[] actual = multiplesNumbers.getArrayWithMultiplesNumbers(array, number);
        assertArrayEquals(expectedArray, actual);
    }
}
