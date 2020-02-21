package com.alevel.java.nix.homeworks.lesson9.implementation;

import com.alevel.java.nix.homeworks.lesson9.InputOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToeInputOutput implements InputOutput {
    private static final Logger log = LoggerFactory.getLogger(TicTacToeInputOutput.class);

    private Scanner scanner;

    public TicTacToeInputOutput() {
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

    public void printPlayingArea(char[][] playingArea) {
        System.out.println(MessageFormat.format(
                " {0} | {1} | {2} \n" +
                        "-----------\n" +
                        " {3} | {4} | {5} \n" +
                        "-----------\n" +
                        " {6} | {7} | {8} \n",
                playingArea[0][0], playingArea[0][1], playingArea[0][2],
                playingArea[1][0], playingArea[1][1], playingArea[1][2],
                playingArea[2][0], playingArea[2][1], playingArea[2][2]));
    }
}
