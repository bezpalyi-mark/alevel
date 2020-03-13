package com.alevel.java.nix.generics.implementation;

import com.alevel.java.nix.generics.Aggregator;

import java.util.Arrays;
import java.util.HashSet;

public class DistinctAggregator<T> implements Aggregator<Integer, T> {
    @Override
    public Integer aggregate(T[] items) {
        if (items == null) {
            return 0;
        }
        if (items.length == 0) {
            return 0;
        }
        if (items.length == 1) {
            return 1;
        }
        return new HashSet<>(Arrays.asList(items)).size();
    }
}
