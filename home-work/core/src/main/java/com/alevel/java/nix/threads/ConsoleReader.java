package com.alevel.java.nix.threads;

import java.io.InputStream;
import java.util.Scanner;

public class ConsoleReader implements Runnable {

    private final StringBuilder input;

    private final InputStream inputStream;

    public ConsoleReader(InputStream inputStream) {
        input = new StringBuilder();
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        System.out.println("Enter:");
        Scanner scanner = new Scanner(inputStream).useDelimiter("\n");
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
