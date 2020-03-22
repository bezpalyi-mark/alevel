package com.alevel.java.nix.dates;

import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalLong;

public class DateDuration {
    long getMaxDurationBetweenDatesInDays(List<LocalDateTime> dateList) {

        final OptionalLong maxYear = dateList.stream()
                .mapToLong(LocalDateTime::getYear)
                .max();

        if (maxYear.isEmpty()) {
            throw new NullPointerException();
        }

        final OptionalLong minYear = dateList.stream()
                .mapToLong(LocalDateTime::getYear)
                .min();

        final OptionalLong minDay = dateList.stream()
                .mapToLong(LocalDateTime::getDayOfYear)
                .min();

        final OptionalLong maxDay = dateList.stream()
                .mapToLong(LocalDateTime::getDayOfYear)
                .max();

        long yearsDifference = maxYear.getAsLong() - minYear.getAsLong();
        long daysDifference = maxDay.getAsLong() - minDay.getAsLong();

        if (yearsDifference == 0) {
            return daysDifference;
        }

        return (daysDifference) + (365 * yearsDifference);
    }
}
