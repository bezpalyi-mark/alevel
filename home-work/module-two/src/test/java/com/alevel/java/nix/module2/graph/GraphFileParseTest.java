package com.alevel.java.nix.module2.graph;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GraphFileParseTest {

    private final String[] townsNames = {
            "Kurakhove",
            "Kiev",
            "Zaporizza",
            "Svetlovodsk",
            "Kharkiv"
    };

    @Test
    void parseGraphFromFile() {
        Map<String, String> srcDstTowns = new HashMap<>();
        GraphFileParse fileParse = new GraphFileParse();
        Graph graph = fileParse.parseGraphFromFile("testOutput.txt");
        for(String name : townsNames) {
            assertTrue(graph.has(name));
        }
        Map<String, Integer> roadsFromFirst = new HashMap<>();
        roadsFromFirst.put(townsNames[1], 5);
        assertEquals(roadsFromFirst, graph.getNode(townsNames[0]).getRoadsFromCurrent());

        Map<String, Integer> roadsFromSecond = new HashMap<>();
        roadsFromSecond.put(townsNames[2], 3);
        roadsFromSecond.put(townsNames[3], 8);
        assertEquals(roadsFromSecond, graph.getNode(townsNames[1]).getRoadsFromCurrent());


        Map<String, Integer> roadsFromThird = new HashMap<>();
        roadsFromThird.put(townsNames[1], 5);
        roadsFromThird.put(townsNames[3], 10);
        assertEquals(roadsFromThird, graph.getNode(townsNames[2]).getRoadsFromCurrent());


        Map<String, Integer> roadsFromFourth = new HashMap<>();
        roadsFromFourth.put(townsNames[4], 12);
        assertEquals(roadsFromFourth, graph.getNode(townsNames[3]).getRoadsFromCurrent());


        Map<String, Integer> roadsFromFifth = new HashMap<>();
        roadsFromFifth.put(townsNames[2], 4);
        assertEquals(roadsFromFifth, graph.getNode(townsNames[4]).getRoadsFromCurrent());

        srcDstTowns.put(townsNames[0], townsNames[4]);
        srcDstTowns.put(townsNames[4], townsNames[3]);

        assertEquals(srcDstTowns, fileParse.getTownsToSearch());
    }
}