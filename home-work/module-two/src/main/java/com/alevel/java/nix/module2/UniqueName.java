package com.alevel.java.nix.module2;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class UniqueName {
    String from(List<String> names) {
        if(names.size() == 0) {
            return "";
        }
        LinkedHashMap<String, Integer> collect = names.stream()
                .collect(
                        Collectors.groupingBy(
                                String::toString,
                                LinkedHashMap::new,
                                Collectors.summingInt(name -> 1)
                        )
                );
        for (String s : collect.keySet()) {
            if (collect.get(s) == 1) {
                return s;
            }
        }
        return "";
    }
}
