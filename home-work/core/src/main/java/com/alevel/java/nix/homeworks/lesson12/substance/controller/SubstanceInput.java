package com.alevel.java.nix.homeworks.lesson12.substance.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SubstanceInput implements Input {

    private Scanner scanner;

    public SubstanceInput() {
        scanner = new Scanner(System.in);
    }

    @Override
    public int getInt() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next();
            return -1;
        }
    }

    @Override
    public double getDouble() {
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            scanner.next();
            return -1.0;
        }
    }
}
