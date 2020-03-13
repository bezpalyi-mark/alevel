package com.alevel.java.nix.generics.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvgAggregatorTest {

    @Test
    void AvgAggregateTest() {
        Integer[] array1 = {1, 2, 3, 4, 5}; //3.0
        Double[] array2 = {1.5, 2.4}; //1.95
        Integer[] array3 = {1}; //1

        AvgAggregator<Integer> integerAvgAggregator = new AvgAggregator<>();
        assertEquals(3.0, integerAvgAggregator.aggregate(array1));
        assertEquals(1.0, integerAvgAggregator.aggregate(array3));
        assertEquals(0.0, integerAvgAggregator.aggregate(null));

        AvgAggregator<Double> doubleAvgAggregator = new AvgAggregator<>();
        assertTrue(Math.abs(1.95 - doubleAvgAggregator.aggregate(array2)) < 0.00001);
    }
}