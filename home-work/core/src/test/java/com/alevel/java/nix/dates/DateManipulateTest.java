package com.alevel.java.nix.dates;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DateManipulateTest {

    @Test
    void groupingTimesByDates() {
        LocalDateTime firstTimeStamp = LocalDateTime.of(
                LocalDate.of(2020, 3, 21),
                LocalTime.of(16, 38, 34, 3)
        );

        LocalDateTime secondTimeStamp = LocalDateTime.of(
                LocalDate.of(2020, 3, 22),
                LocalTime.of(15, 38, 34, 6)
        );

        List<LocalDateTime> timeStamps = Arrays.asList(
                firstTimeStamp,
                firstTimeStamp.minusHours(2),
                firstTimeStamp.plusMinutes(10),
                secondTimeStamp,
                secondTimeStamp.plusHours(4),
                secondTimeStamp.plusDays(3)
        );

        TreeSet<LocalTime> firstDay = new TreeSet<>();
        firstDay.add(firstTimeStamp.toLocalTime());
        firstDay.add(firstTimeStamp.minusHours(2).toLocalTime());
        firstDay.add(firstTimeStamp.plusMinutes(10).toLocalTime());

        TreeSet<LocalTime> secondDay = new TreeSet<>();
        secondDay.add(secondTimeStamp.toLocalTime());
        secondDay.add(secondTimeStamp.plusHours(4).toLocalTime());

        TreeSet<LocalTime> thirdDay = new TreeSet<>();
        thirdDay.add(secondTimeStamp.plusDays(3).toLocalTime());

        TreeMap<LocalDate, TreeSet<LocalTime>> expected = new TreeMap<>();
        expected.put(firstTimeStamp.toLocalDate(), firstDay);
        expected.put(secondTimeStamp.toLocalDate(), secondDay);
        expected.put(secondTimeStamp.plusDays(3).toLocalDate(), thirdDay);

        DateManipulate dateManipulate = new DateManipulate();
        TreeMap<LocalDate, TreeSet<LocalTime>> actual = dateManipulate.groupingTimesByDates(timeStamps);
        assertEquals(expected, actual);
    }
}