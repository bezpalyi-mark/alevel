package com.alevel.java.nix.homeworks.lesson15;

import java.util.stream.Collectors;

public class DigitStringFilter {

    public long getCompoundNumber(String... strings) {
        if (strings == null) {
            throw new NullPointerException("Strings array is null!");
        } else if (strings.length == 0) {
            return 0;
        }
        long result;
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strings) {
            String temp = str.chars()
                    .filter(Character::isDigit)
                    .mapToObj(c -> String.valueOf((char) c))
                    .collect(Collectors.joining());
            stringBuilder.append(temp);
        }
        if (stringBuilder.length() == 0) {
            return 0;
        }
        result = Long.parseLong(stringBuilder.toString());
        return result;
    }
}
