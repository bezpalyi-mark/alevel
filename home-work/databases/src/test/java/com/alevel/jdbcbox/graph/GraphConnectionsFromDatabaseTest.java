package com.alevel.jdbcbox.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphConnectionsFromDatabaseTest {
    @Test
    void connectionsFromDBTest() {
        GraphConnectionsFromDatabase connections = new GraphConnectionsFromDatabase();
        connections.addConnection(1, 2, 3);
        connections.addConnection(2, 3, 5);
        connections.addConnection(4, 2, 8);
        connections.addConnection(3, 1, 4);

        assertEquals(4, connections.size());

        assertEquals(connections.nextCost(), 3);
        assertEquals(connections.nextCost(), 5);
        assertEquals(connections.nextCost(), 8);
        assertEquals(connections.nextCost(), 4);

        assertEquals(connections.nextTo(), 2);
        assertEquals(connections.nextTo(), 3);
        assertEquals(connections.nextTo(), 2);
        assertEquals(connections.nextTo(), 1);

        assertEquals(connections.nextFrom(), 1);
        assertEquals(connections.nextFrom(), 2);
        assertEquals(connections.nextFrom(), 4);
        assertEquals(connections.nextFrom(), 3);
    }
}