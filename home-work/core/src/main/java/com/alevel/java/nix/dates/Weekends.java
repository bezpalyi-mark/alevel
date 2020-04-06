package com.alevel.java.nix.dates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Weekends {
    List<LocalDate> getWeekendsBetween(String beginning, String ending) {
        LocalDate date1, date2;
        date1 = LocalDate.parse(beginning, DateTimeFormatter.BASIC_ISO_DATE);
        date2 = LocalDate.parse(ending, DateTimeFormatter.BASIC_ISO_DATE);
        int cmp = date1.compareTo(date2);
        if (cmp == 0) { //When beginning date = ending date
            return new ArrayList<>();
        } else if (cmp > 0) { //When beginning date bigger than ending
            return getWeekendsBetween(ending, beginning);
        }
        List<LocalDate> result = new ArrayList<>();
        while (date1.compareTo(date2) <= 0) {
            DayOfWeek day = date1.getDayOfWeek();
            if (day == DayOfWeek.SATURDAY) {
                result.add(date1); //add Saturday
                date1 = date1.plusDays(1); //plus 1 day
                if (date1.compareTo(date2) <= 0) { //if Sunday is valid day
                    result.add(date1);
                    date1 = date1.plusDays(6); //go onward for 6 days to next Saturday
                    continue;
                }
            }
            date1 = date1.plusDays(1);
        }
        return result;
    }
}
