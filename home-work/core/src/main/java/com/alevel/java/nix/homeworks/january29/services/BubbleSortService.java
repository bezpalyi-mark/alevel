package com.alevel.java.nix.homeworks.january29.services;

import com.alevel.java.nix.homeworks.january29.interfaces.SortHandler;

public class BubbleSortService {
    private final SortHandler sortHandler;

    public BubbleSortService(SortHandler sortHandler) {
        this.sortHandler = sortHandler;
    }

    public int[] getBubbleSortedArray(int[] array) {
        return sortHandler.bubbleSort(array);
    }
}
