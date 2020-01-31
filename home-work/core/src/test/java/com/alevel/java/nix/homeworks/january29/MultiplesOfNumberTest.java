package com.alevel.java.nix.homeworks.january29;

import com.alevel.java.nix.homeworks.january29.interfaces.MultiplesOfNumberHandler;
import com.alevel.java.nix.homeworks.january29.services.MultiplesNumbersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class MultiplesOfNumberTest {
    private MultiplesNumbersService multiplesNumbersService;
    private MultiplesOfNumberHandler multiplesOfNumberHandler;

    @BeforeEach
    void setUp() {
        multiplesOfNumberHandler = mock(MultiplesOfNumberHandler.class);
        multiplesNumbersService = new MultiplesNumbersService(multiplesOfNumberHandler);
    }

    @Test
    void testGetMultiplesOfNumber() {
        assertMultiplesOfNumber(new int[]{2, 5, 8, 9, 3, 10}, 2, new int[]{2, 8, 10});
        assertMultiplesOfNumber(new int[]{1, 3, 5, 9, 8}, 3, new int[]{3, 9});
    }


    void assertMultiplesOfNumber(int[] array, int number, int[] expectedArray) {
        when(multiplesOfNumberHandler.getArrayWithMultiplesNumbers(array, number)).thenReturn(expectedArray);

        int[] actualArray = multiplesNumbersService.getMultiplesOfNumber(array, number);
        assertEquals(expectedArray, actualArray);

        verify(multiplesOfNumberHandler, only()).getArrayWithMultiplesNumbers(array, number);
        reset(multiplesOfNumberHandler);
    }
}
