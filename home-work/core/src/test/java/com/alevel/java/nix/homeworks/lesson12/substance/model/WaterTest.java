package com.alevel.java.nix.homeworks.lesson12.substance.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaterTest {

    @Test
    void assertHeatUpAndTemperature() {
        Substance water = new Water();

        assertEquals(State.LIQUID, water.heatUp(0));
        assertEquals(0, water.getTemperature());

        assertEquals(State.GAS, water.heatUp(101));
        assertEquals(101, water.getTemperature());

        assertEquals(State.SOLID, water.heatUp(-150));
        assertEquals(-49, water.getTemperature());
    }
}