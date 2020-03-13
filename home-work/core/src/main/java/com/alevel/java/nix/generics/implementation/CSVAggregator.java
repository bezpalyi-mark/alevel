package com.alevel.java.nix.generics.implementation;

import com.alevel.java.nix.generics.Aggregator;

import java.util.StringJoiner;

public class CSVAggregator<T> implements Aggregator<String, T> {
    @Override
    public String aggregate(T[] items) {
        if (items == null) {
            return "";
        }
        if (items.length == 0) {
            return "";
        }
        if (items.length == 1) {
            return items[0].toString();
        }
        StringJoiner stringJoiner = new StringJoiner(",");
        for (T item : items) {
            stringJoiner.add(item.toString());
        }
        return stringJoiner.toString();
    }
}
