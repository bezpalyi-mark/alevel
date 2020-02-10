package com.alevel.java.nix.homeworks.lesson6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UniqueSubstringTest {
    @Test
    void testGetLengthLongestUniqueSubstring() {
        final String[] strings = {"abcabcbb", "bbbbb", "pwwkew"};
        final int[] outputs = {3, 1, 3};
        for (int i = 0; i < strings.length; i++) {
            int actual = SolutionLongestUniqueSubstring.getLengthLongestUniqueSubstring(strings[i]);
            assertEquals(outputs[i], actual);
        }
    }
}
