package com.alevel.java.nix.homeworks.lesson5;

import com.alevel.java.nix.homeworks.lesson5.implementation.Business;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BusinessTest {
    final double[][] inputPrices = {
            {1.5, 9, 4.8, 2, 0, 3},
            {1, 1, 1, 1, 1},
            {2.9, 5, 7.1, 9, 10},
            {-5.52, 4.43, 2, 0},
            {4, 5.5, 4, 1},
            {10, 3, 2.2, 4.6}
    };
    final double[] outputBenefits = {7.5, 0, 7.1, 0, 1.5, 2.4};

    @Test
    void testGetMaximumBenefit() {
        Business business = new Business();
        double lambda = 0.0001;
        for (int i = 0; i < outputBenefits.length; i++) {
            double actual = business.getMaximumBenefit(inputPrices[i]);
            assertTrue(Math.abs(outputBenefits[i] - actual) < lambda);
        }
    }
}
