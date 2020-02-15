package com.alevel.java.nix.module1;

import java.util.HashSet;
import java.util.Set;

public class Level1 {
    public int getUniqueNumbersCount(final int[] array) {
        Set<Integer> integerSet = new HashSet<>();
        for (int value : array) {
            integerSet.add(value);
        }
        return integerSet.size();
    }
}
