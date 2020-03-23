package com.alevel.java.nix.ionio;

import java.io.*;

public class ReadFileFiltered {
    public void substringFilter(String substring, String path, OutputStream outputStream) {
        try (RandomAccessFile file = new RandomAccessFile(path, "r")) {
            String line;
            while (file.getFilePointer() != file.length()) {
                line = file.readLine();
                if (line.contains(substring)) {
                    outputStream.write(line.getBytes());
                    outputStream.write(System.lineSeparator().getBytes());
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Method to demonstrate filtered read of file
     * @param args CLI
     */
    public static void main(String[] args) {
        ReadFileFiltered read = new ReadFileFiltered();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        read.substringFilter("at", "data/filterMe.txt", baos);
        System.out.println(new String(baos.toByteArray()));
    }
}
