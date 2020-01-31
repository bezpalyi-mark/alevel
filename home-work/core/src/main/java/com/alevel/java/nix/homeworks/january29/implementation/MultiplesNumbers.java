package com.alevel.java.nix.homeworks.january29.implementation;

import com.alevel.java.nix.homeworks.january29.MultiplesOfNumberHandler;

import java.util.ArrayList;
import java.util.List;

public class MultiplesNumbers implements MultiplesOfNumberHandler {
    @Override
    public int[] getArrayWithMultiplesNumbers(int[] array, int number) {
        List<Integer> multiplesNumbers = new ArrayList<>();
        for (int value : array) {
            if (value % number == 0) {
                multiplesNumbers.add(value);
            }
        }
        int[] multiplesNumbersArray = new int[multiplesNumbers.size()];
        for(int i = 0; i < multiplesNumbers.size(); i++) {
            multiplesNumbersArray[i] = multiplesNumbers.get(i);
        }
        return multiplesNumbersArray;
    }
}
