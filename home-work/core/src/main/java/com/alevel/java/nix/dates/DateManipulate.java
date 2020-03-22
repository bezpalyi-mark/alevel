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

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        List<LocalDateTime> timestamps = Arrays.asList(
                now,
                now.minusHours(1),
                now.plusHours(1),
                now.minusDays(1),
                now.plusDays(1),
                now.plusHours(25),
                now.plusDays(2)
        );
        DateManipulate dateManipulate = new DateManipulate();
        System.out.println(dateManipulate.groupingTimesByDates(timestamps));
    }
}
