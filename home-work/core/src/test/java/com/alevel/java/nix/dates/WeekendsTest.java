package com.alevel.java.nix.dates;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class WeekendsTest {

    @Test
    void getWeekendsBetween() {
        Weekends weekends = new Weekends();

        assertEquals(
                Arrays.asList(
                        LocalDate.of(2019, 10, 12),
                        LocalDate.of(2019, 10, 13),
                        LocalDate.of(2019, 10, 19),
                        LocalDate.of(2019, 10, 20),
                        LocalDate.of(2019, 10, 26),
                        LocalDate.of(2019, 10, 27)
                ),
                weekends.getWeekendsBetween("20191010", "20191029")
        );

        assertEquals(
                Collections.singletonList(
                        LocalDate.of(2019, 3, 2)
                ),
                weekends.getWeekendsBetween("20190301", "20190302")
        );

        assertEquals(0, weekends.getWeekendsBetween("20190301", "20190301").size());

        assertEquals(
                Arrays.asList(
                        LocalDate.of(2019, 10, 5),
                        LocalDate.of(2019, 10, 6)
                ),
                weekends.getWeekendsBetween("20191010", "20191004")
        );

    }
}