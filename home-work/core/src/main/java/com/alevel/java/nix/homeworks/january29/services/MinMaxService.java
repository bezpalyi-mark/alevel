package com.alevel.java.nix.homeworks.january29.services;

import com.alevel.java.nix.homeworks.january29.MinMaxHandler;

import java.util.List;

public class MinMaxService {
    private final MinMaxHandler minMaxHandler;

    public MinMaxService(MinMaxHandler minMaxHandler) {
        this.minMaxHandler = minMaxHandler;
    }

    public List<Integer> getMinMax(int[] array) {
        return minMaxHandler.minMaxInArray(array);
    }

}
