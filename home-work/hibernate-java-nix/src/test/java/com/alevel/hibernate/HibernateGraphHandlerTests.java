package com.alevel.hibernate;

import com.alevel.hibernate.entity.City;
import com.alevel.hibernate.entity.Connection;
import com.alevel.hibernate.entity.Problems;
import com.alevel.java.nix.module2.graph.Graph;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HibernateGraphHandlerTests {

    @Test
    void fillGraph() {
        HibernateGraphHandler graphHandler = new HibernateGraphHandler();

        City city1 = new City(1L, "Kiev");
        City city2 = new City(2L, "Odessa");
        City city3 = new City(3L, "New York");

        List<City> cityList = Arrays.asList(
                city1, city2, city3
        );

        Connection connection1 = new Connection();
        connection1.setCost(12);
        connection1.setFromCity(city1);
        connection1.setToCity(city3);

        Connection connection2 = new Connection();
        connection2.setCost(20);
        connection2.setFromCity(city3);
        connection2.setToCity(city2);

        List<Connection> connectionList = Arrays.asList(
                connection1, connection2
        );

        Map<String, Integer> road1 = new HashMap<>();
        road1.put(city3.getName(), 12);

        Map<String, Integer> road2 = new HashMap<>();
        road2.put(city2.getName(), 20);

        Graph actual = graphHandler.fillGraph(cityList, connectionList);

        assertTrue(actual.has(city1.getName()));
        assertTrue(actual.has(city2.getName()));
        assertTrue(actual.has(city3.getName()));

        assertEquals(road1, actual.getNode(city1.getName()).getRoadsFromCurrent());
        assertEquals(road2, actual.getNode(city3.getName()).getRoadsFromCurrent());
    }

    @Test
    void mapProblems() {
        HibernateGraphHandler graphHandler = new HibernateGraphHandler();
        City city1 = new City(1L, "Kiev");
        City city2 = new City(2L, "Odessa");
        City city3 = new City(3L, "New York");

        List<Problems> problemsList = Arrays.asList(
                new Problems(1L, city2, city3),
                new Problems(2L, city1, city2),
                new Problems(3L, city3, city2)
        );

        Map<Long, Long> expected = Map.ofEntries(
                Map.entry(2L, 3L),
                Map.entry(1L, 2L),
                Map.entry(3L, 2L)
        );

        assertEquals(expected, graphHandler.mapProblems(problemsList));
    }
}