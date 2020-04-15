package com.alevel.jdbcbox.graph;

import com.alevel.java.nix.module2.graph.Graph;
import com.alevel.java.nix.module2.graph.GraphResult;
import com.alevel.jdbcbox.DatabaseConnection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphFromDatabase {

    private static List<String> cities = null;

    private static GraphConnectionsFromDatabase connections = null;

    public static void main(String[] args) {
        DatabaseConnection connection = new DatabaseConnection("configurations.properties");
        connection.establishConnection();
        GraphRequest graphRequest = new GraphRequest(connection);
        cities = graphRequest.getCities();
        connections = graphRequest.getConnections();
        Graph graph = fillGraph();
        Map<Integer, Integer> problems = graphRequest.getProblems();
        int id_counter = 1;
        for (var entry : problems.entrySet()) {
            GraphResult shortestWay = graph.getShortestWay(cities.get(entry.getKey() - 1),
                                                           cities.get(entry.getValue() - 1));
            if (shortestWay == null) {
                graphRequest.writeImpossibleConnection(id_counter);
            } else {
                graphRequest.writeFoundConnection(id_counter, shortestWay.getLength());
            }
            id_counter++;
        }
        connection.closeConnection();
    }

    private static Graph fillGraph() {
        Graph graph = new Graph();

        for (String city : cities) {
            graph.addNode(city);
        }

        for (int i = 0, size = connections.size(); i < size; i++) {
            String townFrom = cities.get(connections.nextFrom() - 1);
            String townTo = cities.get(connections.nextTo() - 1);
            Map<String, Integer> road = new HashMap<>();
            road.put(townTo, connections.nextCost());
            graph.addRoadsFromTo(townFrom, road);
        }
        return graph;
    }
}
