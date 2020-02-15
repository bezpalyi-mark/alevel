package com.alevel.java.nix.module1;

import java.util.Arrays;
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

    public double triangleArea(final int[] pointA, final int[] pointB, final int[] pointC) {
        if (pointA.length != 2 || pointB.length != 2 || pointC.length != 2) {
            return 0;
        }
        if (hasEqualsPoints(pointA, pointB, pointC)) {
            return 0;
        }

        double lengthA = Math.sqrt(
                Math.pow(pointB[0] - pointA[0], 2) + Math.pow(pointB[1] - pointA[1], 2)
        );
        double lengthB = Math.sqrt(
                Math.pow(pointC[0] - pointB[0], 2) + Math.pow(pointC[1] - pointB[1], 2)
        );
        double lengthC = Math.sqrt(
                Math.pow(pointA[0] - pointC[0], 2) + Math.pow(pointA[1] - pointC[1], 2)
        );

        if (!isAcceptedLengths(lengthA, lengthB, lengthC)) {
            return 0;
        }

        double halfArea = (lengthA + lengthB + lengthC) / 2;
        double area = Math.sqrt(
                halfArea * (halfArea - lengthA) * (halfArea - lengthB) * (halfArea - lengthC)
        );
        return area;
    }

    private boolean hasEqualsPoints(final int[] pointA, final int[] pointB, final int[] pointC) {
        return Arrays.equals(pointA, pointB) || Arrays.equals(pointB, pointC) || Arrays.equals(pointA, pointC);
    }

    private boolean isAcceptedLengths(final double lengthA, final double lengthB, final double lengthC) {
        return !((lengthA + lengthB) < lengthC) && !((lengthB + lengthC) < lengthA) && !((lengthC + lengthA) < lengthB);
    }
}
