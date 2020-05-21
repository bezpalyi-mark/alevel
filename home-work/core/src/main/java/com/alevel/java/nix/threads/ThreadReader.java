package com.alevel.java.nix.threads;

import java.io.*;

public class ThreadReader {
    public static void main(String[] args) throws IOException {
        ConsoleReader reader = new ConsoleReader(System.in);
        Thread thread = new Thread(reader);
        String input = "";
        File file = new File("./data/output.txt");
        if (file.createNewFile()) {
            System.out.println("File already exists");
        }
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            thread.start();
            Thread.sleep(1000);
            while (thread.isAlive()) {
                Thread.sleep(1000);
                StringBuilder next = reader.getInput();
                if (!input.equals(next.toString())) {
                    randomAccessFile.setLength(0);  //clear file
                    randomAccessFile.write(next.toString().getBytes()); //write to file
                    input = next.toString();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
