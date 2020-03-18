package com.alevel.java.nix.homeworks.lesson15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DigitStringFilterTest {
    @Test
    public void assertGetCompoundNumber() {
        String[] strings1 = {"oalf 23 ad[a", "12 lpla09", "11"};
        String[] strings2 = {"", " ", "f2f"};
        String[] strings3 = {"ljefb", "", " "};
        String[] strings4 = new String[0];

        DigitStringFilter stringFilter = new DigitStringFilter();
        assertEquals(23120911L, stringFilter.getCompoundNumber(strings1));
        assertEquals(2L, stringFilter.getCompoundNumber(strings2));
        assertEquals(0L, stringFilter.getCompoundNumber(strings3));
        assertEquals(0L, stringFilter.getCompoundNumber(strings4));
        assertThrows(NullPointerException.class, () -> new DigitStringFilter().getCompoundNumber(null),
                "Strings array is null!");
    }

}