package com.alevel.java.nix.threads;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ThreadFileWriterTest {

    @Test
    void spyWrite() throws IOException, InterruptedException {
        String string = "First\nSecond\nquit";
        byte[] buf = string.getBytes();
        Reader inputStream = new InputStreamReader(new ByteArrayInputStream(buf));
        ThreadFileWriter threadFileWriter = new ThreadFileWriter();
        threadFileWriter.spyWrite(inputStream, "../data/output.txt");
        String expected = "First Second ";
        String fileContents = new Scanner(new File("../data/output.txt")).useDelimiter("\\Z").next();
        assertEquals(expected, fileContents);
    }
}