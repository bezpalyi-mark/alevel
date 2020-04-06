package com.alevel.java.nix.module2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class UniqueNameTest {

    @Test
    void from() {
        UniqueName uniqueName = new UniqueName();
        assertEquals("Vika", uniqueName.from(Arrays.asList("Mark", "Dima", "Vika", "Dima", "Sasha", "Mark")));
        assertNull(uniqueName.from(Arrays.asList("Mark", "Dima", "Mark", "Dima", "Dima", "Mark")));
        assertEquals("Sasha", uniqueName.from(Arrays.asList("Mark", "Dima", "Dima", "Sasha", "Mark")));
        assertNull(uniqueName.from(Collections.emptyList()));
    }
}