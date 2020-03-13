package com.alevel.java.nix.generics.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CSVAggregatorTest {

    @Test
    void CSVAggregateTest() {
        Integer[] array1 = {1, 5, 2, 3};
        String[] array2 = {"Frank", "Lucy", "John"};
        Integer[] array3 = {2};
        Integer[] array4 = new Integer[0];

        CSVAggregator<Integer> integerCSVAggregator = new CSVAggregator<>();
        assertEquals("1,5,2,3", integerCSVAggregator.aggregate(array1));
        assertEquals("2", integerCSVAggregator.aggregate(array3));
        assertEquals("", integerCSVAggregator.aggregate(null));
        assertEquals("", integerCSVAggregator.aggregate(array4));

        CSVAggregator<String> stringCSVAggregator = new CSVAggregator<>();
        assertEquals("Frank,Lucy,John", stringCSVAggregator.aggregate(array2));
    }
}