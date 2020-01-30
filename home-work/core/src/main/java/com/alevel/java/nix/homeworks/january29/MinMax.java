package com.alevel.java.nix.homeworks.january29;

import java.util.ArrayList;
import java.util.List;

public class MinMax implements MinMaxHandler {

    @Override
    public List<Integer> MinMaxInArray(int[] array) {
        List<Integer> minMaxValues = new ArrayList<>();

        int min = array[0];
        int max = array[0];

        for (int value : array) {
            if (value < min) {
                min = value;
            }

            if (value > max) {
                max = value;
            }
        }

        minMaxValues.add(min);
        minMaxValues.add(max);

        return minMaxValues;
    }
}
