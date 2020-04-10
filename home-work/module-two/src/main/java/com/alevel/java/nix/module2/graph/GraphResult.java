package com.alevel.java.nix.module2.graph;

import java.util.List;

public class GraphResult {
    private final List<String> path;

    private String stringPath;

    private final Integer length;

    public GraphResult(List<String> path, Integer length) {
        this.path = path;
        this.length = length;
        formatPath();
    }

    public Integer getLength() {
        return length;
    }

    private void formatPath() {
        StringBuilder stringBuilder = new StringBuilder();
        for(String s : path) {
            stringBuilder.append(s).append("->");
        }
        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "");
        stringPath = stringBuilder.toString();
    }

    public String getPath() {
        return stringPath;
    }
}
