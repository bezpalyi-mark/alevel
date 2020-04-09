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

    public  class Node {
        private final String name;

        private final Integer number;

        /**
         * Roads. Consist 'Road to' -> 'Weight'
         */
        private final Map<String, Integer> roadsFromCurrent;

        private final Map<String, Integer> roadsToCurrent;

        private Node minToCurrent;

        public Node(String name, Integer number) {
            this.name = name;
            this.number = number;
            roadsFromCurrent = new HashMap<>();
            roadsToCurrent = new HashMap<>();
            minToCurrent = new Node("none", 0);
        }

        public void addRoadFromTo(String roadTo, Integer weight) {
            roadsFromCurrent.put(roadTo, weight);
        }

        public void addRoadToCurrent(String roadFrom, Integer weight) {
            roadsToCurrent.put(roadFrom, weight);
        }

        public Integer getShortestWayToThis() {
            Integer minWay = Integer.MAX_VALUE;
            for(var entry : roadsToCurrent.entrySet()) {
                if(entry.getValue() < minWay) {
                    minToCurrent = nodes.get(entry.getKey());
                    minWay = entry.getValue();
                }
            }
            return minWay;
        }
    }

//    public Integer findShortestWay(String from, String to) {
//        if (!alreadyConsist(from) || !alreadyConsist(to)) {
//            throw new IllegalArgumentException();
//        }
//
//        Node start = nodes.get(from);
//        Node finish = nodes.get(to);
//        Integer currentPointNumber = numbersOfPoints.get(from);
//        Integer endPointNumber = numbersOfPoints.get(to);
//        if(currentPointNumber > endPointNumber) {
//            findShortestWay(to, from);
//        }
//        Node currentNode = new Node(start.name, start.number);
//        Map<String, Integer> minWays = new HashMap<>();
//        while(!currentNode.name.equals(finish.name)) {
//            Node[] arrayNodes = new Node[currentNode.roadsFromCurrent.size()];
//            int i = 0;
//            for(var entry : currentNode.roadsFromCurrent.entrySet()) {
//                arrayNodes[i] = nodes.get(entry.getKey());
//                i++;
//            }
//
//        }
//        return 0;
//    }

    String func(String from, String to) {
        if (!alreadyConsist(from) || !alreadyConsist(to)) {
            throw new IllegalArgumentException();
        }
        if(from.equals(to)) {
            return to;
        }
        Node start = nodes.get(from);
        Set<String> visited = new HashSet<>();
        visited.add(from);
        List<String> road = new ArrayList<>();
        for(var entry : start.roadsFromCurrent.entrySet()) {
            if(visited.add(entry.getKey())) {
                road.add(entry.getKey());
                road.add(func(entry.getKey(), to));
            } else {
                return "typic";
            }
        }
        return Arrays.toString(road.toArray());
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

        System.out.println(roadGraph.func("Third", "Fifth"));
    }
}


