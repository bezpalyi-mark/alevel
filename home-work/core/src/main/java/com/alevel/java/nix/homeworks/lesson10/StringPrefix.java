package com.alevel.java.nix.homeworks.lesson10;

import com.alevel.java.nix.homeworks.lesson3.implementation.MinMax;

public class StringPrefix {
    public String longestCommonPrefix(String... strs) {
        int[] lengths = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            lengths[i] = strs[i].length();
        }
        MinMax minMax = new MinMax();
        final int minLength = minMax.minMaxInArray(lengths).get(0);
        if (minLength == 0)
            return "";
        StringBuilder stringBuilder = new StringBuilder(minLength);
        boolean accept = false;
        int index = 0;
        for (int i = 0; i < minLength; i++) {
            for(int j = 0; j < strs.length - 1; j++) {
                if(strs[j].charAt(i) != strs[j+1].charAt(i)) {
                    accept = false;
                    break;
                }
                accept = true;
            }
            if(accept) {
                stringBuilder.append(strs[index].charAt(i));
                index++;
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }
}
