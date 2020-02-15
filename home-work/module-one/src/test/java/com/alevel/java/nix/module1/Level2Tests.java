package com.alevel.java.nix.module1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Level2Tests {
    @Test
    public void assertIsValidString() {
        String[] inputs = {"{[([])]}", "{([)]}", "", "{())}", "[(]", "{}}}", "()", "}{", "[](){}", "abc"};
        boolean[] expected = {true, false, true, false, false, false, true, false, true, false};
        Level2 level2 = new Level2();
        for (int i = 0; i < expected.length; i++) {
            boolean actual = level2.isValidString(inputs[i]);
            assertEquals(expected[i], actual);
        }
    }
}