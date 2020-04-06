package com.alevel.java.nix.module2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DateReformatTest {

    @Test
    void reformatDate() {
        DateReformat dateReformat = new DateReformat();
        List<String> inputDates = Arrays.asList(
                "2020/04/05",
                "02/10/2014",
                "11-22-2010"
        );

        assertEquals(
                Arrays.asList(
                        "20200405",
                        "20141002",
                        "20101122"),
                dateReformat.reformatDate(inputDates)
        );

        inputDates = Arrays.asList(
                "2020/22/04",
                "2010/03/21",
                "22-11-2011",
                "20101122"
        );

        assertEquals(
                Collections.singletonList(
                        "20100321"
                ),
                dateReformat.reformatDate(inputDates)
        );

        inputDates = Arrays.asList(
                "22-11-2011",
                "20/10/20",
                "2020-04-05"
        );

        assertEquals(0, dateReformat.reformatDate(inputDates).size());
    }
}