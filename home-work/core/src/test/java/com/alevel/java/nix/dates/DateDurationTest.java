package com.alevel.java.nix.dates;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DateDurationTest {

    @Test
    void getMaxDurationBetweenDatesInDays() {
        LocalDateTime firstTimeStamp = LocalDateTime.of(
                LocalDate.of(2014, 3, 21),
                LocalTime.of(16, 38, 34, 3)
        );

        List<LocalDateTime> firstTimeStamps = Arrays.asList(
                firstTimeStamp,
                firstTimeStamp.plusDays(2),
                firstTimeStamp.minusDays(1),
                firstTimeStamp.plusDays(4),
                firstTimeStamp.plusMinutes(3600)
        );

        List<LocalDateTime> secondTimeStamps = Arrays.asList(
                firstTimeStamp,
                firstTimeStamp.plusMinutes(36)
        );

        List<LocalDateTime> thirdTimeStamps = Arrays.asList(
                firstTimeStamp,
                firstTimeStamp.plusDays(36)
        );

        List<LocalDateTime> fourthTimeStamps = Arrays.asList(
                firstTimeStamp,
                firstTimeStamp.plusDays(2).plusYears(1)
        );

        List<LocalDateTime> fifthTimeStamps = Arrays.asList(
                firstTimeStamp,
                firstTimeStamp.plusDays(5).plusYears(3)
        );

        Long[] longs = {
                5L, 0L, 36L, 367L, 1100L
        };

        DateDuration dateDuration = new DateDuration();

        long actual = dateDuration.getMaxDurationBetweenDatesInDays(firstTimeStamps);
        assertEquals(longs[0], actual);

        actual = dateDuration.getMaxDurationBetweenDatesInDays(secondTimeStamps);
        assertEquals(longs[1], actual);

        actual = dateDuration.getMaxDurationBetweenDatesInDays(thirdTimeStamps);
        assertEquals(longs[2], actual);

        actual = dateDuration.getMaxDurationBetweenDatesInDays(fourthTimeStamps);
        assertEquals(longs[3], actual);

        actual = dateDuration.getMaxDurationBetweenDatesInDays(fifthTimeStamps);
        assertEquals(longs[4], actual);
    }
}