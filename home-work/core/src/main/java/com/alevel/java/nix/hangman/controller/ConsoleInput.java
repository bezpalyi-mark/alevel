package com.alevel.java.nix.hangman.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleInput implements Input {
    private Scanner scanner;

    public ConsoleInput(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    @Override
    public char getChar() {
        try {
            return scanner.next().charAt(0);
        } catch (InputMismatchException e) {
            scanner.next();
            return '-';
        }
    }

    @Override
    public String[] readFileFromClasspath(String name) {
        ClassLoader classLoader = getClass().getClassLoader();
        List<String> stringList;
        Stream<String> stringStream;
        try {
            stringStream = Files.lines(
                    Paths.get(
                            Objects.requireNonNull(
                                    classLoader.getResource(name)).getPath()
                    )
            );
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        stringList = stringStream.collect(Collectors.toList());
        if (stringList.size() == 0) {
            return new String[0];
        }
        return stringList.toString().split("\\n");
    }

}
