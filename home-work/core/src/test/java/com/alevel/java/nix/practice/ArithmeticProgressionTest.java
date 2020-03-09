package com.alevel.java.nix.practice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ArithmeticProgressionTest {

    @Test
    public void calculateTest() throws ProgressionConfigurationException {
        assertThrows(ProgressionConfigurationException.class,
                () -> new ArithmeticProgression(1, 0).calculate(3), "step = 0!");

        assertThrows(ProgressionConfigurationException.class,
                () -> new ArithmeticProgression(1, 2).calculate(0), "n <= 0!");

        assertThrows(ProgressionConfigurationException.class,
                () -> new ArithmeticProgression(1, 2).calculate(-5), "n <= 0!");

        ArithmeticProgression progression = new ArithmeticProgression(1, 2);

        assertEquals(5, progression.calculate(2));
        assertEquals(11, progression.calculate(5));

    }

}