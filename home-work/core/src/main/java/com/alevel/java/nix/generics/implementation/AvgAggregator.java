package com.alevel.java.nix.generics.implementation;

import com.alevel.java.nix.generics.Aggregator;

public class AvgAggregator<T extends Number> implements Aggregator<Double, T> {

    @Override
    public Double aggregate(T[] items) {
        if (items == null) {
            return 0.0;
        }
        if (items.length == 0) {
            return 0.0;
        }
        if (items.length == 1) {
            return items[0].doubleValue();
        }
        double sum = 0.0;
        for (T item : items) {
            sum += item.doubleValue();
        }
        return sum / (double) items.length;
    }
}
