package com.alevel.java.nix.homeworks.lesson4;

import com.alevel.java.nix.homeworks.lesson4.implementation.Numbers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzTest {
    final int[] inputData = {97365, 0, -8345, 111, -0};
    final boolean[] inputOptions = {false, false, true, false, true};
    final String[] outputData = {"buzz buzz fizzbuzz ", "fizzbuzz ", "fizz buzz fizz ", "", "fizzbuzz "};
    final int testsCount = 5;

    @Test
    void testFizzBuzzOfNumber() {
        Numbers numbers = new Numbers();
        for(int i = 0; i < testsCount; i++) {
            String actual = numbers.fizzBuzzOfNumber(inputData[i], inputOptions[i]);
            assertEquals(outputData[i], actual);
        }
    }

}
