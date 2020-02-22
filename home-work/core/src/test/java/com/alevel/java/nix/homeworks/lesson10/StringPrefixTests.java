package com.alevel.java.nix.homeworks.lesson10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringPrefixTests {
    @Test
    public void assertLongestCommonPrefix() {
        String[][] inputs = {
                {"flower", "flow", "flight"},
                {"dog","racecar","car"},
                {"abcmpmp", "abcvonos", "abcmpowkp"},
                {"", "aab", "aaf"},
                {"", "", ""}
        };
        String[] expected = {"fl", "", "abc", "", ""};
        StringPrefix stringPrefix = new StringPrefix();
        for(int i = 0; i < expected.length; i++) {
            String actual = stringPrefix.longestCommonPrefix(inputs[i]);
            assertEquals(expected[i], actual);
        }
    }
}
