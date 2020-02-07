package com.alevel.java.nix.homeworks.lesson5.implementation;

import com.alevel.java.nix.homeworks.lesson5.Shareholder;

public class Business implements Shareholder {

    @Override
    public double getMaximumBenefit(double[] array) {
        if(hasNegativeNumbers(array)){
            return 0;
        }
        int[] minMaxIndexes = regionMinMaxIndexesOf(array, 0, array.length);
        if (minMaxIndexes[0] < minMaxIndexes[1]) {
            return getDiffInArrayByIndexes(array, minMaxIndexes[1], minMaxIndexes[0]);
        } else if (minMaxIndexes[0] == minMaxIndexes[1]) {
            return 0;
        } else {
            int[] firstRegionIndexes = regionMinMaxIndexesOf(array, 0, minMaxIndexes[1] + 1);
            int[] secondRegionIndexes = regionMinMaxIndexesOf(array, minMaxIndexes[0], array.length);
            double firstBenefit = getDiffInArrayByIndexes(array, firstRegionIndexes[1], firstRegionIndexes[0]);
            double secondBenefit = getDiffInArrayByIndexes(array, secondRegionIndexes[1], secondRegionIndexes[0]);
            return Math.max(firstBenefit, secondBenefit);
        }
    }

    double getDiffInArrayByIndexes(double[] array, int index1, int index2) {
        if (index1 < 0 || index2 < 0) {
            return 0;
        } else if (index1 > array.length || index2 > array.length) {
            return 0;
        }
        return array[index1] - array[index2];
    }

    boolean hasNegativeNumbers(double[] array) {
        for(double value : array) {
            if(value < 0) {
                return true;
            }
        }
        return false;
    }

    int[] regionMinMaxIndexesOf(final double[] array, final int begin, final int end) {
        if (array.length < 2) {
            return new int[2];
        }
        if (array.length < (begin + 1)) {
            return new int[2];
        }
        double min = array[begin];
        double max = array[begin];

        int minPos = begin;
        int maxPos = begin;

        for (int i = begin; i < end; i++) {
            if (array[i] < min) {
                min = array[i];
                minPos = i;
            }

            if (array[i] > max) {
                max = array[i];
                maxPos = i;
            }
        }
        return new int[]{minPos, maxPos};
    }
}
