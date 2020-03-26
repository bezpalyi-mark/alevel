package com.alevel.java.nix.ionio;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
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

    public HandlerCSV(List<String> data, String[] headers, String[][] extractedData, Integer headersCount) {
        this.data = data;
        this.headers = headers;
        this.extractedData = extractedData;
        this.headersCount = headersCount;
    }

    public HandlerCSV getHandledCSV(Path path) {
        readCSV(path);
        extractHeaders();
        extractData();
        return new HandlerCSV(data, headers, extractedData, headersCount);
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
            for (int j = 0; j < headersCount; j++) {
                extractedData[i] = data.get(i).split(",");
            }
        }
    }

    public String get(int row, int column) {
        if(row > extractedData.length || row < 0) {
            throw new IndexOutOfBoundsException(MessageFormat.format(
                    "Row number is {0}, but rows in file {1}",
                    row, extractedData.length
            ));
        }
        if(column > extractedData[0].length || column < 0) {
            throw new IndexOutOfBoundsException(MessageFormat.format(
                    "Column number is {0}, but columns in file {1}",
                    column, headersCount
            ));
        }
        return extractedData[row][column];
    }

    public String get(int row, String headName) {
        if(row > extractedData.length || row < 0) {
            throw new IndexOutOfBoundsException(MessageFormat.format(
                    "Row number is {0}, but rows in file {1}",
                    row, extractedData.length
            ));
        }
        for(int i = 0; i < headersCount; i++) {
            if(headName.equals(headers[i])) {
                return extractedData[row][i];
            }
        }
        return null;
    }
    
}
