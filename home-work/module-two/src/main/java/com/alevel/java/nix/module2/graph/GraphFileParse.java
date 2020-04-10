package com.alevel.java.nix.module2.graph;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GraphFileParse {

    /**
     * Index of town -> name of town
     */
    private final Map<Integer, String> indexTown = new HashMap<>();

    /**
     * All neighbours and weights wrote by line.
     */
    private final List<Integer> roadsWeights = new ArrayList<>();

    /**
     * Towns between which you need to find the shortest path. From -> To.
     */
    private final Map<String, String> townsToSearch = new HashMap<>();

    /**
     * Counts of neighbours for every town.
     */
    private final List<Integer> countsOfNeighbours;

    public GraphFileParse() {
        countsOfNeighbours = new ArrayList<>();
    }

    Graph parseGraphFromFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        List<String> stringList;
        Stream<String> stringStream;
        try {
            stringStream = Files.lines(
                    Paths.get(
                            Objects.requireNonNull(
                                    classLoader.getResource(fileName)).getPath()
                    )
            );
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        stringList = stringStream.collect(Collectors.toList());

        int index = 1;
        int position = 1;
        int townCount = Integer.parseInt(stringList.get(0));
        if(townCount > 10000) {
            throw new IllegalArgumentException("To many towns!");
        }
        for (int i = 0; i < townCount; i++) {
            indexTown.put(index, stringList.get(position));
            index++;
            position++;
            int neighbours = Integer.parseInt(stringList.get(position));
            countsOfNeighbours.add(neighbours);
            position++;
            for (int j = 0; j < neighbours; j++) {
                int to = Integer.parseInt(String.valueOf(stringList.get(position).split(" ")[0]));
                int weight = Integer.parseInt(String.valueOf(stringList.get(position).split(" ")[1]));
                roadsWeights.add(to);
                roadsWeights.add(weight);
                position++;
            }
        }
        int pathsCounts = Integer.parseInt(stringList.get(position));
        if(pathsCounts > 100) {
            throw new IllegalArgumentException("To many paths!");
        }
        position++;
        for (int i = position; i < stringList.size(); i++) {
            String[] towns = stringList.get(i).split(" ");
            townsToSearch.put(towns[0], towns[1]);
        }
        return fillGraph();
    }

    private Graph fillGraph() {
        Graph graph = new Graph();

        for (var entry : indexTown.entrySet()) {
            graph.addNode(entry.getValue());
        }
        int index = 1;
        int roadIndex = 0;
        for (int i = 0; i < indexTown.size(); i++, index++) {
            Map<String, Integer> roads = new HashMap<>();
            String townName = indexTown.get(index);
            for (int j = 0, length = countsOfNeighbours.get(i); j < length; j++) {
                roads.put(indexTown.get(roadsWeights.get(roadIndex)), roadsWeights.get(roadIndex + 1));
                roadIndex += 2;
            }
            graph.addRoadsFromTo(townName, roads);
        }
        return graph;
    }

    public Map<String, String> getTownsToSearch() {
        return townsToSearch;
    }
}
