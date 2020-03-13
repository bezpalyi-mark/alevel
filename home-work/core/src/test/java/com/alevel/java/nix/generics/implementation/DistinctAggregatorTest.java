package com.alevel.java.nix.generics.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistinctAggregatorTest {

    @Test
    public void distinctAggregateTest() {
        final Integer[] array1 = {1, 5, 8, 1, 2, 1};
        final Integer[] array2 = {2};
        final String[] array3 = {"First", "Second", "Third", "First"};

        DistinctAggregator<Integer> integerDistinctAggregator = new DistinctAggregator<>();
        assertEquals(4, integerDistinctAggregator.aggregate(array1));
        assertEquals(0, integerDistinctAggregator.aggregate(null));
        assertEquals(1, integerDistinctAggregator.aggregate(array2));

        DistinctAggregator<String> stringDistinctAggregator = new DistinctAggregator<>();
        assertEquals(3, stringDistinctAggregator.aggregate(array3));

    }
}