package com.alevel.java.nix.module2;

import java.util.*;

public class RoadGraph {

    private final Set<String> names;

    private final Map<String, Node> nodes;

    private final Map<String, Integer> numbersOfPoints;

    private Integer countNodes = 1;

    public RoadGraph() {
        names = new HashSet<>();
        nodes = new HashMap<>();
        numbersOfPoints = new HashMap<>();
    }

    public boolean addNode(String name) {
        if (alreadyConsist(name)) {
            return false;
        }
        Node node = new Node(name, countNodes);
        nodes.put(name, node);
        numbersOfPoints.put(name, countNodes);
        countNodes++;
        return true;
    }

    /**
     * Method to add road;
     *
     * @param from           Name of Point 'from'
     * @param roadsToWeights Map of roads 'Road to' -> 'Weight'
     * @return true if at least one road was added
     */
    public boolean addRoadsFromTo(String from, Map<String, Integer> roadsToWeights) {
        boolean result = false;
        for (var entry : roadsToWeights.entrySet()) {
            if (alreadyConsist(entry.getKey()) && alreadyConsist(from)) {
                result = true;
                nodes.get(from).addRoadFromTo(entry.getKey(), entry.getValue());
                nodes.get(entry.getKey()).addRoadToCurrent(from, entry.getValue());
            }
        }
        return result;
    }

    private boolean alreadyConsist(String name) {
        return !names.add(name);
    }

    public class Node {
        private final String name;

        private final Integer number;

        /**
         * Roads. Consist 'Road to' -> 'Weight'
         */
        private final Map<String, Integer> roadsFromCurrent;

        private final Map<String, Integer> roadsToCurrent;

        public Node(String name, Integer number) {
            this.name = name;
            this.number = number;
            roadsFromCurrent = new HashMap<>();
            roadsToCurrent = new HashMap<>();
        }

        public void addRoadFromTo(String roadTo, Integer weight) {
            roadsFromCurrent.put(roadTo, weight);
        }

        public void addRoadToCurrent(String roadFrom, Integer weight) {
            roadsToCurrent.put(roadFrom, weight);
        }

        public Integer getShortestWayToThis() {
            Integer minWay = Integer.MAX_VALUE;
            for (var entry : roadsToCurrent.entrySet()) {
                if (entry.getValue() < minWay) {
                    minWay = entry.getValue();
                }
            }
            return minWay;
        }
    }

    String getAllPaths(String from, String to, Set<String> visited) {
        if (!alreadyConsist(from) || !alreadyConsist(to)) {
            throw new IllegalArgumentException();
        }
        if (from.equals(to)) {
            return "end";
        }
        Node start = nodes.get(from);
        if (!visited.add(from)) {
            return "typic";
        }
        List<String> road = new ArrayList<>();
        for (var entry : start.roadsFromCurrent.entrySet()) {
            road.add(entry.getKey());
            String result = getAllPaths(entry.getKey(), to, visited);
            if(result.equals("typic")) {
                road = new ArrayList<>();
            } else {
                road.add(result);
            }
            visited.remove(entry.getKey());
        }
        return Arrays.toString(road.toArray());
    }

    List<List<String>> parsePaths(String from, String paths) {
        String[] temps = paths.split(",");
        paths = paths
                .replaceAll(",", "")
                .replaceAll("\\[", "")
                .replaceAll("\\]", "");
        StringBuilder stringBuilder = new StringBuilder(paths.length());
        String[] points = paths.split(" ");
        List<List<String>> executedPaths = new ArrayList<>();
        List<String> path = new ArrayList<>();
        path.add(from);
        for (String point : points) {
            if (point.equals("end")) {
                executedPaths.add(path);
                path = new ArrayList<>();
                path.add(from);
            } else {
                path.add(point);
            }
        }
        return executedPaths;
    }

    void func(String paths) {

    }

    Integer minWeightFromPaths(List<List<String>> executedPaths) {
        Integer[] lengths = new Integer[executedPaths.size()];
        int min = Integer.MAX_VALUE;
        int minIndex = 0;
        for(int i = 0; i < executedPaths.size(); i++) {
            int length = 0;
            for(int j = 0; j < executedPaths.get(i).size() - 1; j++) {
                Node node = nodes.get(executedPaths.get(i).get(j));
                length += node.roadsFromCurrent.get(executedPaths.get(i).get(j+1));
            }
            lengths[i] = length;
            if(min > length) {
                min = length;
                minIndex = i;
            }
        }
        return lengths[minIndex];
    }

    public static void main(String[] args) {
        RoadGraph roadGraph = new RoadGraph();
        roadGraph.addNode("First");
        roadGraph.addNode("Second");
        roadGraph.addNode("Third");
        roadGraph.addNode("Fourth");
        roadGraph.addNode("Fifth");

        Map<String, Integer> roadsFromFirst = new HashMap<>();
        roadsFromFirst.put("Second", 5);
        roadGraph.addRoadsFromTo("First", roadsFromFirst);

        Map<String, Integer> roadsFromSecond = new HashMap<>();
        roadsFromSecond.put("Third", 3);
        roadsFromSecond.put("Fourth", 8);
        roadGraph.addRoadsFromTo("Second", roadsFromSecond);

        Map<String, Integer> roadsFromThird = new HashMap<>();
        roadsFromThird.put("Second", 5);
        roadsFromThird.put("Fourth", 10);
        roadGraph.addRoadsFromTo("Third", roadsFromThird);

        Map<String, Integer> roadsFromFourth = new HashMap<>();
        roadsFromFourth.put("Fifth", 12);
        roadGraph.addRoadsFromTo("Fourth", roadsFromFourth);

        Map<String, Integer> roadsFromFifth = new HashMap<>();
        roadsFromFifth.put("Third", 4);
        roadGraph.addRoadsFromTo("Fifth", roadsFromFifth);

        String result = roadGraph.getAllPaths("First", "Fifth", new HashSet<>());
        List<List<String>> res = roadGraph.parsePaths("First", result);
        System.out.println(roadGraph.minWeightFromPaths(res));
    }
}


