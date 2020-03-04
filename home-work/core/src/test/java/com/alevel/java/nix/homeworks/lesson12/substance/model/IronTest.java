package com.alevel.java.nix.homeworks.lesson12.substance.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class IronTest {

    @Test
    void assertHeatUpAndTemperature() {
        Substance iron = new Iron();
        assertEquals(State.SOLID, iron.heatUp(0));
        assertEquals(0, iron.getTemperature());

        assertEquals(State.GAS, iron.heatUp(2861));
        assertEquals(2861, iron.getTemperature());

        assertEquals(State.LIQUID, iron.heatUp(-1000));
        assertEquals(1861, iron.getTemperature());
    }
}