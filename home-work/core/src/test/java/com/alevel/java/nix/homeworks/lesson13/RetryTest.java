package com.alevel.java.nix.homeworks.lesson13;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RetryTest {

    private static Integer COUNT = 1;

    private static String FILE_NAME = "./src/test/java/com/alevel/java/nix/homeworks/lesson13/1.txt";

    public static void increaseFileNumber() {
        String currentName = COUNT + ".txt";
        COUNT++;
        FILE_NAME = FILE_NAME.replace(currentName, COUNT + ".txt");
    }

    public static void restFileNumber() {
        String currentName = COUNT + ".txt";
        COUNT = 1;
        FILE_NAME = FILE_NAME.replace(currentName, COUNT + ".txt");
    }

    public static void ensureException() {
        int a = 1 / 0;
    }

    public static void mayBeException() throws FileNotFoundException {
        File file = new File(FILE_NAME);
        increaseFileNumber();
        FileReader fr = new FileReader(file);
    }

    @Test
    public void repeatTest() throws Exception {
        assertThrows(Exception.class,
                () -> new Retry(100).repeat(RetryTest::ensureException, 5)
        );
        File file = new File("./src/test/java/com/alevel/java/nix/homeworks/lesson13/3.txt");
        file.createNewFile();
        assertDoesNotThrow(() -> new Retry(100).repeat(RetryTest::mayBeException, 5));


        restFileNumber();
        assertThrows(Exception.class,
                () -> new Retry(100).repeat(RetryTest::mayBeException, 2)
        );
    }
}