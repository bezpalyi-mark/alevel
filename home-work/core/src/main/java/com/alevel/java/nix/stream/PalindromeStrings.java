package com.alevel.java.nix.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class PalindromeStrings {
    public Collection<String> getPalindromes(Collection<String> strings) {
        return strings.stream()
                .flatMap(s -> Arrays.stream(s.split("")))
                .filter(PalindromeStrings::isPalindrome)
                .collect(Collectors.toList());
    }

    static boolean isPalindrome(String string) {
        int start = 0;
        int end = string.length() - 1;
        while (start < end) {
            if (string.charAt(start++) != string.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}
