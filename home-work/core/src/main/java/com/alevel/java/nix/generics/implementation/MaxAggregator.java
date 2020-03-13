package com.alevel.java.nix.generics.implementation;

import com.alevel.java.nix.generics.Aggregator;

import java.util.Comparator;

public class MaxAggregator<T extends Comparable<? super T>> implements Aggregator<T, T> {

    private Comparator<T> comparator;

    public MaxAggregator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public T aggregate(T[] items) {
        if (items == null) {
            throw new IllegalArgumentException("Array reference of items is null");
        }
        if (items.length == 0) {
            throw new IllegalArgumentException("Array of items have 0 items");
        }
        if (items.length == 1) {
            return items[0];
        }
        T maxValue = items[0];
        for (T item : items) {
            if (comparator.compare(item, maxValue) > 0) {
                maxValue = item;
            }
        }
        return maxValue;
    }
}
