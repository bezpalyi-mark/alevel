package com.alevel.java.nix.ionio;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HandlerCSVTest {

    @Test
    void getHandledCSV() {
        File file = new File("./core/data/fileToReadCSV.csv");
        File file1 = new File(".");
        if(file.exists()) {
            System.out.println("It's ok");
        }
        System.out.println(Arrays.toString(file.list()));
    }
}