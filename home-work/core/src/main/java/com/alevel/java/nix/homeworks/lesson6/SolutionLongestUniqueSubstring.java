package com.alevel.java.nix.homeworks.lesson6;

public class SolutionLongestUniqueSubstring {
    public static int getLengthLongestUniqueSubstring(String string) {
        StringBuilder stringBuilder = new StringBuilder(string.length());
        stringBuilder.append(string.charAt(0));
        int value = 0;
        for (int i = 0, length = string.length(); i < length; i++) {
            for (int j = 0; j < stringBuilder.length(); j++) {
                if (stringBuilder.charAt(j) == string.charAt(i)) {
                    if (stringBuilder.length() > value) {
                        value = stringBuilder.length();
                    }
                    stringBuilder.replace(0, stringBuilder.length(), "");
                }
            }
            stringBuilder.append(string.charAt(i));
        }
        return value;
    }
}
