package com.alevel.java.nix.threads;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class ThreadFileWriter {
    public void spyWrite(InputStream inputStream) throws IOException {
        ConsoleReader reader = new ConsoleReader(inputStream);
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

    public static void main(String[] args) throws IOException {
        new ThreadFileWriter().spyWrite(System.in);
    }
}
