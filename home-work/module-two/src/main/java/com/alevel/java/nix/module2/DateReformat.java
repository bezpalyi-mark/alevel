package com.alevel.java.nix.module2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DateReformat {

    private final DateTimeFormatter[] formatters = {
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy")
    };

    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public String reformatDate(String dateString) {
        Objects.requireNonNull(dateString);
        for (DateTimeFormatter formatter : formatters) {
            String result = tryToCovertWithFormatter(dateString, formatter);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    private String tryToCovertWithFormatter(String dateString, DateTimeFormatter formatter) {
        LocalDate date;
        try {
            date = LocalDate.parse(dateString, formatter);
            return date.format(outputFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Pattern " + formatter.toString() + " is not compatible with " + dateString);
        }
        return null;
    }

    public List<String> reformatDate(List<String> dateList) {
        List<String> result = new ArrayList<>();
        if (dateList.size() == 0) {
            return result;
        }
        for (String s : dateList) {
            String date = reformatDate(s);
            if (date != null) {
                result.add(date);
            }
        }
        return result;
    }
}
