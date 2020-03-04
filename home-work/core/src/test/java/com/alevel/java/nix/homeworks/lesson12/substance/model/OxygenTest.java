package com.alevel.java.nix.homeworks.lesson12.substance.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OxygenTest {

    @Test
    void assertHeatUpAndTemperature() {
        Substance oxygen = new Oxygen();

        assertEquals(State.GAS, oxygen.heatUp(0));
        assertEquals(0, oxygen.getTemperature());

        assertEquals(State.LIQUID, oxygen.heatUp(-193.5));
        assertEquals(-193.5, oxygen.getTemperature());

        assertEquals(State.SOLID, oxygen.heatUp(-1000));
        assertEquals(-1193.5, oxygen.getTemperature());
    }
}