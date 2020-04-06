package com.alevel.java.nix.module2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DateReformat {

    private final String[] patterns = {
            "dd/MM/yyyy", "yyyy/MM/dd", "MM-dd-yyyy"
    };


    public String reformatDate(String dateString) {
        Objects.requireNonNull(dateString);
        for (String pattern : patterns) {
            String result = tryToCovertWithFormatter(dateString, pattern);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    private String tryToCovertWithFormatter(String dateString, String pattern) {
        LocalDate date;
        try {
            date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(pattern));
            return date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (DateTimeParseException e) {
            System.out.println("Pattern " + pattern + " is not compatible with " + dateString);
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
