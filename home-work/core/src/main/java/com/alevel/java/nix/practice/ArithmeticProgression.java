package com.alevel.java.nix.practice;

public class ArithmeticProgression {
    private final int initial;
    private final int step;

    public ArithmeticProgression(int initial, int step) throws ProgressionConfigurationException {
        this.initial = initial;
        this.step = step;
    }

    int calculate(int n) throws ProgressionConfigurationException {
        int result = initial;
        if(n <= 0) {
            throw new ProgressionConfigurationException("n <= 0!");
        }
        if(step == 0) {
            throw new ProgressionConfigurationException("step = 0!");
        }
        for(int i = 0; i < n; i++) {
            result += step;
        }
        return result;
    }

}
