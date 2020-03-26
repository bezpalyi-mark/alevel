package com.alevel.java.nix.stream;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;

public class StreamMaxMinAvg {

    private double min;

    private double max;

    private double average;

    private StreamMaxMinAvg(double min, double max, double average) {
        this.min = min;
        this.max = max;
        this.average = average;
    }

    public static StreamMaxMinAvg MaxMinAvg(double[] array) {
        DoubleSummaryStatistics statistic = Arrays.stream(array)
                .summaryStatistics();
        return new StreamMaxMinAvg(statistic.getMin(), statistic.getMax(), statistic.getAverage());
    }

    public  double getMin() {
        return min;
    }

    public  double getMax() {
        return max;
    }

    public  double getAverage() {
        return average;
    }
}
