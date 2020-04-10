package com.alevel.java.nix.module2.graph;

import java.util.Map;

public class RoadGraph {
    public static void main(String[] args) {
        GraphFileParse graphFileParse = new GraphFileParse();
        Graph graph = graphFileParse.parseGraphFromFile("output.txt");
        Map<String, String> townsToSearch = graphFileParse.getTownsToSearch();
        for (var entry : townsToSearch.entrySet()) {
            GraphResult shortestWay = graph.getShortestWay(entry.getKey(), entry.getValue());
            System.out.println(shortestWay.getPath());
            System.out.println("Length: " + shortestWay.getLength());
        }
    }
}
