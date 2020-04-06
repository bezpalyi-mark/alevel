package com.alevel.java.nix.module2;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UniqueName {
    String from(List<String> names) {
        if (names.size() == 0) {
            return null;
        }
        LinkedHashMap<String, Integer> collect = names.stream()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,
                                Collectors.summingInt(name -> 1)
                        )
                );
        for (var entry : collect.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return null;
    }
}
