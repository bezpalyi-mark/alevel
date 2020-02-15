package com.alevel.java.nix.module1;

import java.util.HashSet;
import java.util.Set;

public class Level1 {
    public int getUniqueNumbersCount(final int[] array) {
        Set<Integer> integerSet = new HashSet<>();
        for (int value : array) {
            integerSet.add(value);
        }
        return integerSet.size();
    }

    public boolean isValidHorsebackRiding(final int[] horsePosition, final int[] movePosition) {
        if (horsePosition.length != 2 || movePosition.length != 2) {
            return false;
        }
        int[][] validPositions = {
                {horsePosition[0] - 2, horsePosition[1] - 1}, //going up and take left
                {horsePosition[0] - 2, horsePosition[1] + 1}, //going up and take right
                {horsePosition[0] + 2, horsePosition[1] - 1}, //going down and take left
                {horsePosition[0] + 2, horsePosition[1] + 1}, //going down and take right
                {horsePosition[0] + 1, horsePosition[1] - 2}, //going left and take under
                {horsePosition[0] - 1, horsePosition[1] - 2}, //going left and take top
                {horsePosition[0] + 1, horsePosition[1] + 2}, //going right and take under
                {horsePosition[0] - 1, horsePosition[1] + 2}  //going right and take top

        };
        for (int[] validPosition : validPositions) {
            if (validPosition[0] == movePosition[0] && validPosition[1] == movePosition[1]) {
                return true;
            }
        }
        return false;
    }
}
