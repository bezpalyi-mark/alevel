package com.alevel.java.nix.module2.graph;

import java.util.*;

public class Graph {

    private final Set<String> names;

    private final Map<String, Node> nodes;

    public Graph() {
        names = new HashSet<>();
        nodes = new HashMap<>();
    }

    public boolean addNode(String name) {
        if (has(name)) {
            return false;
        }
        names.add(name);
        Node node = new Node(name);
        nodes.put(name, node);
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
            if (has(entry.getKey()) && has(from) && entry.getValue() < 200_000) {
                result = true;
                nodes.get(from).addRoadFromTo(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    public boolean has(String name) {
        return names.contains(name);
    }

    public static class Node {
        private final String name;

        /**
         * Roads. Consist 'Road to' -> 'Weight'
         */
        private final Map<String, Integer> roadsFromCurrent;

        public Node(String name) {
            this.name = name;
            roadsFromCurrent = new HashMap<>();
        }

        public Map<String, Integer> getRoadsFromCurrent() {
            return roadsFromCurrent;
        }

        public void addRoadFromTo(String roadTo, Integer weight) {
            roadsFromCurrent.put(roadTo, weight);
        }
    }

    /**
     * Function to get all roads from Point to Point
     *
     * @param from    point to start
     * @param to      point of finish
     * @param visited visited points
     * @return String of tree, like [bydgoszcz, [warszawa, end, torun, [warszawa, end]], torun, [warszawa, end, bydgoszcz, [warszawa, end]]]
     */
    private String getAllPaths(String from, String to, Set<String> visited) {
        if (!has(from) || !has(to)) {
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
        String temp = "";
        for (var entry : start.roadsFromCurrent.entrySet()) {
            road.add(entry.getKey());
            String result = getAllPaths(entry.getKey(), to, visited);
            if (result.equals("typic")) {
                road.remove(entry.getKey());
            } else {
                road.add(result);
            }
            temp = entry.getKey();
        }
        visited.remove(temp);
        return Arrays.toString(road.toArray());
    }

    /**
     * Function to parse result of function 'getAllPaths'
     *
     * @param from  point of beginning
     * @param paths string with paths from 'getAllPaths'
     * @return List of lists with paths.
     */
    private List<List<String>> parsePaths(String from, String paths) {
        if (paths.equals("end") || paths.length() < 3) {
            return null;
        }
        List<List<String>> executedPaths = new ArrayList<>();
        List<String> path = new ArrayList<>();
        LinkedList<String> tree = new LinkedList<>();
        tree.addLast(from);
        StringBuilder stringBuilder = new StringBuilder(paths.length());
        StringBuilder endString = new StringBuilder(paths.length());
        boolean branching = false;
        int endIndex = 0;
        for (int i = 0; i < paths.length(); i++, endIndex++) {
            endString.append(paths.charAt(i));
            if ((paths.charAt(i) == '[' || branching) && paths.charAt(i) != ']') {
                i++;
                endString.append(paths.charAt(i));
                while (paths.charAt(i) != ',') {
                    stringBuilder.append(paths.charAt(i));
                    i++;
                    endString.append(paths.charAt(i));
                }
                tree.addLast(stringBuilder.toString());
                stringBuilder.replace(0, stringBuilder.length(), "");
                branching = false;
            } else if (paths.charAt(i) == ']') {
                branching = true;
                int count = 1;
                i++;
                endString.append(paths.charAt(i));
                while (i < paths.length() && paths.charAt(i) == ']') {
                    count++;
                    i++;
                    if (i < paths.length()) {
                        endString.append(paths.charAt(i));
                    }
                }
                path.addAll(tree);
                executedPaths.add(path);
                path = new ArrayList<>();
                for (int j = 0; j < count + 1; j++) {
                    tree.removeLast();
                }
                endString = new StringBuilder(endString.toString().replace("end", ""));
            }
            if (endString.toString().contains("end") && paths.charAt(i + 1) != ']') {
                path.addAll(tree);
                executedPaths.add(path);
                path = new ArrayList<>();
                tree.removeLast();
                i++;
                endString.append(paths.charAt(i));
                branching = true;
                endString = new StringBuilder(endString.toString().replace("end", ""));
            }
        }
        return executedPaths;
    }

    /**
     * Function looks for length of every path.
     *
     * @param executedPaths List of lists with paths.
     * @return minimal length of paths and index of this path.
     */
    private List<Integer> minWeightFromPaths(List<List<String>> executedPaths) {
        if (executedPaths == null) {
            return new ArrayList<>();
        }
        Integer[] lengths = new Integer[executedPaths.size()];
        int min = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < executedPaths.size(); i++) {
            int length = 0;
            for (int j = 0; j < executedPaths.get(i).size() - 1; j++) {
                Node node = nodes.get(executedPaths.get(i).get(j));
                length += node.roadsFromCurrent.get(executedPaths.get(i).get(j + 1));
            }
            lengths[i] = length;
            if (min > length) {
                min = length;
                minIndex = i;
            }
        }
        return Arrays.asList(lengths[minIndex], minIndex);
    }

    /**
     * Function for users to getShortestWayWeight.
     *
     * @param from point where need to start.
     * @param to   destination point.
     * @return length of shortest way.
     */
    public GraphResult getShortestWay(String from, String to) {
        String result = getAllPaths(from, to, new HashSet<>());
        List<List<String>> parsedResult = parsePaths(from, result);
        if(parsedResult == null) {
            return null;
        }
        List<Integer> lenToIndex = minWeightFromPaths(parsedResult);
        return new GraphResult(parsedResult.get(lenToIndex.get(1)), lenToIndex.get(0));
    }

    public Node getNode(String name) {
        if (!has(name)) {
            return null;
        }
        return nodes.get(name);
    }


    public List<List<String>> parse(String from, String paths) {
        return parsePaths(from, paths);
    }

    /**
     * If you want to see example of program word, run main
     *
     * @param args CLI
     */
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addNode("First");
        graph.addNode("Second");
        graph.addNode("Third");
        graph.addNode("Fourth");
        graph.addNode("Fifth");

        Map<String, Integer> roadsFromFirst = new HashMap<>();
        roadsFromFirst.put("Second", 5);
        graph.addRoadsFromTo("First", roadsFromFirst);

        Map<String, Integer> roadsFromSecond = new HashMap<>();
        roadsFromSecond.put("Third", 3);
        roadsFromSecond.put("Fourth", 8);
        graph.addRoadsFromTo("Second", roadsFromSecond);

        Map<String, Integer> roadsFromThird = new HashMap<>();
        roadsFromThird.put("Second", 5);
        roadsFromThird.put("Fourth", 10);
        graph.addRoadsFromTo("Third", roadsFromThird);

        Map<String, Integer> roadsFromFourth = new HashMap<>();
        roadsFromFourth.put("Fifth", 12);
        graph.addRoadsFromTo("Fourth", roadsFromFourth);

        Map<String, Integer> roadsFromFifth = new HashMap<>();
        roadsFromFifth.put("Third", 4);
        graph.addRoadsFromTo("Fifth", roadsFromFifth);

        GraphResult shortestWay = graph.getShortestWay("First", "Fifth");
        System.out.println(shortestWay.getPath());
        System.out.println("Length: " + shortestWay.getLength());
    }
}
