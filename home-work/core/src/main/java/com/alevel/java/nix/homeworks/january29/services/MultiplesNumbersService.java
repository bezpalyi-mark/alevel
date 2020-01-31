package com.alevel.java.nix.homeworks.january29.services;

import com.alevel.java.nix.homeworks.january29.interfaces.MultiplesOfNumberHandler;

public class MultiplesNumbersService {

    private final MultiplesOfNumberHandler multiplesOfNumberHandler;

    public MultiplesNumbersService(MultiplesOfNumberHandler multiplesOfNumberHandler) {
        this.multiplesOfNumberHandler = multiplesOfNumberHandler;
    }

    public int[] getMultiplesOfNumber(int[] array, int number) {
        return multiplesOfNumberHandler.getArrayWithMultiplesNumbers(array, number);
    }
}
