package com.alevel.java.nix.homeworks.january29;

import com.alevel.java.nix.homeworks.january29.interfaces.MinMaxHandler;
import com.alevel.java.nix.homeworks.january29.services.MinMaxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MinMaxTest {
    private MinMaxHandler minMaxHandler;
    private MinMaxService algorithmsService;

    @BeforeEach
    void setUp() {
        minMaxHandler = mock(MinMaxHandler.class);
        algorithmsService = new MinMaxService(minMaxHandler);
    }

    @Test
    void testGetMinMax() {
        assertMinMax(new int[]{0, 1, 2, -8, 4}, new int[]{-7, 4});
        assertMinMax(new int[]{2, 6, -9, 2, 0}, new int[]{-9, 6});
        assertMinMax(new int[]{1, -3, 3, 9, 0}, new int[]{-3, 9});
    }


    void assertMinMax(int[] array, int[] minMax) {
        List<Integer> minMaxExpected = new ArrayList<>();
        minMaxExpected.add(minMax[0]);
        minMaxExpected.add(minMax[1]);

        when(minMaxHandler.MinMaxInArray(array)).thenReturn(minMaxExpected);

        List<Integer> minMaxActual = algorithmsService.getMinMax(array);
        assertEquals(minMaxExpected, minMaxActual);

        verify(minMaxHandler, only()).MinMaxInArray(array);
        reset(minMaxHandler);
    }
}
