package com.alevel.java.nix.hangman.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleInput implements Input {
    private final Scanner scanner;

    public ConsoleInput(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    @Override
    public char getChar() {
        try {
            String input = scanner.next();
            if(input.length() > 1 || !Character.isAlphabetic(input.charAt(0))) {
                return '-';
            }
            return input.charAt(0);
        } catch (InputMismatchException e) {
            scanner.next();
            return '-';
        }
    }

    @Override
    public List<String> readFileFromClasspath(String name) {
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
            return Collections.emptyList();
        }
        return stringList;
    }

}
