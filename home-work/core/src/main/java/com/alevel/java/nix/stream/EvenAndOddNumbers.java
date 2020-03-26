package com.alevel.java.nix.stream;

import java.util.*;

import static java.util.stream.Collectors.*;

public class EvenAndOddNumbers {

    public Map<Boolean, SortedSet<Integer>> groupingSortedArray(int[] array) {
        return Arrays.stream(array)
                .sorted()
                .boxed()
                .collect(partitioningBy(
                        num -> num % 2 == 0,
                        toCollection(() -> new TreeSet<>(Comparator.reverseOrder()))
                ));
    }
}
