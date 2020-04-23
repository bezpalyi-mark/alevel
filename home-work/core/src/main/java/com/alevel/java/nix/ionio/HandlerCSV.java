package com.alevel.java.nix.ionio;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HandlerCSV {
    private List<String> data;
    private String[] headers;
    private String[][] extractedData;
    private Integer headersCount;

    public HandlerCSV(Path path) throws NoSuchFileException {
        assertPath(path);
        readCSV(path);
        extractHeaders();
        extractData();
    }

    private void assertPath(Path path) throws NoSuchFileException {
        File file = new File(path.toString());
        if (!file.exists() || !file.isFile()) {
            throw new NoSuchFileException(path.toString());
        }
    }

    private void readCSV(Path path) {
        Stream<String> stringStream;
        try {
            stringStream = Files.lines(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        data = stringStream.collect(Collectors.toList());
    }

    private void extractHeaders() {
        String headersNames = data.get(0);
        headers = headersNames.split(",");
        headersCount = headers.length;
    }

    private void extractData() {
        extractedData = new String[data.size() - 1][headersCount];
        for (int i = 1, length = data.size(); i < length; i++) {
            extractedData[i - 1] = data.get(i).split(",");
        }
    }

    public String get(int row, int column) {
        if (row > extractedData.length || row < 0) {
            throw new IndexOutOfBoundsException(MessageFormat.format(
                    "Row number is {0}, but rows in file {1}",
                    row, extractedData.length
            ));
        }
        if (column > extractedData[0].length || column < 0) {
            throw new IndexOutOfBoundsException(MessageFormat.format(
                    "Column number is {0}, but columns in file {1}",
                    column, headersCount
            ));
        }
        return extractedData[row][column];
    }

    public String[] getHeaders() {
        return headers;
    }

    public String get(int row, String headName) {
        if (row > extractedData.length || row < 0) {
            throw new IndexOutOfBoundsException(MessageFormat.format(
                    "Row number is {0}, but rows in file {1}",
                    row, extractedData.length
            ));
        }
        try {
            for (int i = 0; i < headersCount; i++) {
                if (headName.equals(headers[i])) {
                    return extractedData[row][i];
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }
        return "";
    }

    public int size() {
        return extractedData.length;
    }

}
