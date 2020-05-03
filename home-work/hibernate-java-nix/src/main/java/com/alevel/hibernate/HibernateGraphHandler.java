package com.alevel.hibernate;

import com.alevel.hibernate.entity.City;
import com.alevel.hibernate.entity.Connection;
import com.alevel.hibernate.entity.Problems;
import com.alevel.java.nix.module2.graph.Graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HibernateGraphHandler {

    /**
     * Filling graph by input cities and connections.
     *
     * @param listCities      list with City.class.
     * @param listConnections list with Connection.class.
     * @return filled graph.
     */
    public Graph fillGraph(List<City> listCities, List<Connection> listConnections) {
        Graph graph = new Graph();

        for (City city : listCities) {
            graph.addNode(city.getName());
        }
        for (Connection connection : listConnections) {
            String townFrom = connection.getFromCity().getName();
            String townTo = connection.getToCity().getName();
            Map<String, Integer> road = new HashMap<>();
            road.put(townTo, connection.getCost());
            graph.addRoadsFromTo(townFrom, road);
        }
        return graph;
    }

    /**
     * Method convert problems in HashMap.
     *
     * @param listProblems list with Problems.class.
     * @return converted into HashMap problems.
     */
    public Map<Long, Long> mapProblems(List<Problems> listProblems) {
        Map<Long, Long> problems = new HashMap<>();
        for (Problems problem : listProblems) {
            problems.put(problem.getFromCity().getId(), problem.getToCity().getId());
        }
        return problems;
    }
}
