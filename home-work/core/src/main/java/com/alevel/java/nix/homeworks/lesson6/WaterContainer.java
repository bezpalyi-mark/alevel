package com.alevel.java.nix.homeworks.lesson6;

public class WaterContainer {
    public static int maxArea(int[] height) {
        int maximum = 0;
        for (int i = 0, length = height.length; i < length; i++) {
            for(int j = length - 1; j != i; j--) {
                int current = height[j];
                int min = Math.min(height[i], current);
                int temp = min * (j - i);
                if (temp > maximum) {
                    maximum = temp;
                }
            }
        }
        return maximum;
    }
}
