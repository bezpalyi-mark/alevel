package com.alevel.java.nix.generics;

public interface Aggregator<A, T> {
    A aggregate(T[] items);
}
