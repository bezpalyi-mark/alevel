package com.alevel.java.nix.hangman.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleInput implements Input {
    private final InputStream inputStream;

    public ConsoleInput(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public char getChar() {
        try {
            byte[] input = inputStream.readNBytes(2);
            byte extracted = input[0] == 10 ? input[1] : input[0];
            if (!Character.isAlphabetic(extracted)) {
                return '-';
            }
            return (char) extracted;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
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
