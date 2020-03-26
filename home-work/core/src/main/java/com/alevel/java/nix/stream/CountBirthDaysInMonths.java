package com.alevel.java.nix.stream;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import static java.util.stream.Collectors.*;

public class CountBirthDaysInMonths {
    public Map<Month, Integer> getFromLocalDate(Collection<LocalDate> dates) {
        return dates.stream()
                .collect(groupingBy(
                        LocalDate::getMonth,
                        () -> new EnumMap<>(Month.class),
                        summingInt(date -> 1)
                ));
    }
}
