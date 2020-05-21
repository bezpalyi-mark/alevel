package com.alevel.java.nix.threads;

import java.util.Scanner;

public class ConsoleReader implements Runnable {

    private final StringBuilder input;

    public ConsoleReader() {
        input = new StringBuilder();
    }

    @Override
    public void run() {
        System.out.println("Enter:");
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        String next = scanner.next();
        while(!next.equals("quit")) {
            input.append(next).append(" ");
            next = scanner.next();
        }
    }

    public StringBuilder getInput() {
        return input;
    }
}
