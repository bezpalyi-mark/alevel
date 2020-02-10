package com.alevel.java.nix.homeworks.lesson6;

public class SolutionZigZag {
    public static String zigZagConversion(String string, int numRows) {
        StringBuilder[] stringBuilders = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            stringBuilders[i] = new StringBuilder();
        }
        int index = 0;
        boolean down = true;
        for (int i = 0, length = string.length(); i < length; i++) {
            stringBuilders[index].append(string.charAt(i));
            if (down) {
                index++;
            } else {
                index--;
            }
            if (index == numRows) {
                down = false;
                index -= 2;
            } else if (index == 0) {
                down = true;
            }
        }
        StringBuilder result = new StringBuilder(string.length());
        for (int i = 0; i < numRows; i++) {
            result.append(stringBuilders[i]);
        }
        return result.toString();
    }
}
