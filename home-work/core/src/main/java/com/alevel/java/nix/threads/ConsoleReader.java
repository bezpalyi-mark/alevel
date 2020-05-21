package com.alevel.java.nix.threads;

import java.io.Reader;
import java.util.Scanner;

public class ConsoleReader implements Runnable {

    private final StringBuilder input;

    private final Reader inputStream;

    public ConsoleReader(Reader inputStream) {
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
