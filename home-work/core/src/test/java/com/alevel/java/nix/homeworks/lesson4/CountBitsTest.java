package com.alevel.java.nix.homeworks.lesson4;

import com.alevel.java.nix.homeworks.lesson4.implementation.Numbers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountBitsTest {
    long[] inputData = {87, 5, 9365, 0, -20, 331, -1, 1};
    int[] output = {5, 2, 6, 0, 61, 5, 64, 1};

    @Test
    void testCountBitsEstablishedToOne() {
        Numbers numbers = new Numbers();
        for (int i = 0; i < inputData.length; i++) {
            int actual = numbers.countBitsEstablishedToOne(inputData[i]);
            assertEquals(output[i], actual);
        }
    }
}
