package com.alevel.java.nix.module2.graph;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    Graph graph = new Graph();

    private static final String[] names = {
            "First",
            "Second",
            "Third",
            "Fourth",
            "Fifth"
    };

    @BeforeAll
    static void graphAddNodeTest() {
        Graph graph = new Graph();
        assertTrue(graph.addNode(names[0]));
        assertTrue(graph.addNode(names[1]));
        assertTrue(graph.addNode(names[2]));
        assertTrue(graph.addNode(names[3]));
        assertTrue(graph.addNode(names[4]));

        assertFalse(graph.addNode(names[0]));
        assertFalse(graph.addNode(names[1]));
        assertFalse(graph.addNode(names[2]));
        assertFalse(graph.addNode(names[3]));
        assertFalse(graph.addNode(names[4]));

        assertTrue(graph.addNode("Six"));
    }

    @BeforeEach
    void setUp() {
        graph.addNode(GraphTest.names[0]);
        graph.addNode(GraphTest.names[1]);
        graph.addNode(GraphTest.names[2]);
        graph.addNode(GraphTest.names[3]);
        graph.addNode(GraphTest.names[4]);
    }

    @Test
    void addRoadsFromToTest() {
        Map<String, Integer> roadsFromFirst = new HashMap<>();
        roadsFromFirst.put("Second", 200_015);
        assertFalse(graph.addRoadsFromTo("First", roadsFromFirst));

        Graph.Node node = graph.getNode("First");
        assertEquals(new HashMap<String, Integer>(), node.getRoadsFromCurrent());

        Map<String, Integer> roadsFromThird = new HashMap<>();
        roadsFromThird.put("Second", 5);
        roadsFromThird.put("Fourth", 10);
        assertTrue(graph.addRoadsFromTo("Third", roadsFromThird));

        node = graph.getNode("Third");
        assertEquals(roadsFromThird, node.getRoadsFromCurrent());

        assertFalse(graph.addRoadsFromTo("Absent name", roadsFromThird));

        Map<String, Integer> roadsWithAbsentPoints = new HashMap<>();
        roadsFromThird.put("Absent point1", 5);
        roadsFromThird.put("Absent point2", 10);
        assertFalse(graph.addRoadsFromTo("Third", roadsWithAbsentPoints));
    }

    @Test
    void getAllPathsTest() {
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

        GraphResult result = graph.getShortestWay("First", "Fifth");
        assertNotNull(result);
        assertEquals(25, result.getLength());
        assertEquals("First->Second->Fourth->Fifth", result.getPath());

        result = graph.getShortestWay("Second", "Fifth");
        assertNotNull(result);
        assertEquals(20, result.getLength());
        assertEquals("Second->Fourth->Fifth", result.getPath());

        result = graph.getShortestWay("Fifth", "Fourth");
        assertNotNull(result);
        assertEquals(14, result.getLength());
        assertEquals("Fifth->Third->Fourth", result.getPath());
    }

    @Test
    void parsePathsTest() {
        List<List<String>> expected = new ArrayList<>();
        List<String> first = Arrays.asList(
                "gdansk",
                "bydgoszcz",
                "warszawa"
        );
        List<String> second = Arrays.asList(
                "gdansk",
                "bydgoszcz",
                "torun",
                "warszawa"
        );
        List<String> third = Arrays.asList(
                "gdansk",
                "torun",
                "warszawa"
        );
        List<String> fourth = Arrays.asList(
                "gdansk",
                "torun",
                "bydgoszcz",
                "warszawa"
        );
        expected.add(first);
        expected.add(second);
        expected.add(third);
        expected.add(fourth);

        String paths = "[bydgoszcz, [warszawa, end, torun, [warszawa, end]], torun, [warszawa, end, bydgoszcz, [warszawa, end]]]";
        assertEquals(expected, graph.parse("gdansk", paths));
    }
}