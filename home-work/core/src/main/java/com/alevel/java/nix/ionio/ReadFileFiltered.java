package com.alevel.java.nix.ionio;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ReadFileFiltered {
    public void substringFilter(String substring, String path) {
        try (RandomAccessFile file = new RandomAccessFile(path, "r")) {
            String line;
            while (file.getFilePointer() != file.length()) {
                line = file.readLine();
                if (line.contains(substring)) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to demonstrate filtered read of file
     * @param args CLI
     */
    public static void main(String[] args) {
        ReadFileFiltered read = new ReadFileFiltered();
        read.substringFilter("at", "data/filterMe.txt");
    }
}
