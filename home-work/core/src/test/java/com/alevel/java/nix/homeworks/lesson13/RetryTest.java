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

    public static Integer ensureException() {
        int a = 1 / 0;
        return a;
    }

    public static Integer mayBeException() throws FileNotFoundException {
        File file = new File(FILE_NAME);
        increaseFileNumber();
        FileReader fr = new FileReader(file);
        return fr.hashCode();
    }

    @Test
    public void repeatTest() throws Exception {
        assertThrows(Exception.class,
                () -> new Retry<Integer>(100).repeat(RetryTest::ensureException, 5)
        );

        File file = new File("./src/test/java/com/alevel/java/nix/homeworks/lesson13/3.txt");
        file.createNewFile();

        //Будем пытаться открыть файл сначала 1.txt. потом 2.txt, существует только 3.txt
        assertDoesNotThrow(() -> new Retry<Integer>(100).repeat(RetryTest::mayBeException, 5));

        //Обнуляем имя файла в программе до 1.txt
        restFileNumber();

        //Доходим только до 2.txt
        assertThrows(Exception.class,
                () -> new Retry<Integer>(100).repeat(RetryTest::mayBeException, 2)
        );
    }
}