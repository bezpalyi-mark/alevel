package com.alevel.java.nix.dates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static java.util.stream.Collectors.*;

public class DateManipulate {
    TreeMap<LocalDate, TreeSet<LocalTime>> groupingTimesByDates(List<LocalDateTime> list) {
        return list.stream()
                .distinct()
                .sorted(LocalDateTime::compareTo)
                .collect(groupingBy(
                        LocalDateTime::toLocalDate,
                        TreeMap::new,
                        mapping(LocalDateTime::toLocalTime, toCollection(TreeSet::new))
                ));
    }
}
