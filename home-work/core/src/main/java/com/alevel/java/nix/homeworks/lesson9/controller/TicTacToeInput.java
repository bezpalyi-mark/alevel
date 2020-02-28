package com.alevel.java.nix.homeworks.lesson9.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToeInput implements Input {
    private static final Logger log = LoggerFactory.getLogger(TicTacToeInput.class);

    private Scanner scanner;

    public TicTacToeInput() {
        scanner = new Scanner(System.in);
    }

    public int getIntegerInput() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            log.warn("Invalid data input!");
            scanner.next();
            return -1;
        }
    }
}
