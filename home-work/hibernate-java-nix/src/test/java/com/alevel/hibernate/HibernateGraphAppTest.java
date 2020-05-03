package com.alevel.hibernate;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HibernateGraphAppTest {

    @Test
    void readSQLScript() throws IOException {
        List<String> strings = HibernateGraphApp.readSQLScript("./src/test/resources/init_tables.sql");
        assertNotNull(strings);
        assertEquals(4, strings.size());
        assertEquals("USE graph;", strings.get(0));
        assertEquals("INSERT INTO city (name) VALUES ('gdansk'),('bydgoszcz'),('torun'),('warszawa');", strings.get(1));
        assertEquals("INSERT INTO connection (cost, from_city, to_city) VALUES (1, 1, 2),(3, 1, 3),(1, 2, 1)," +
                "(1, 2, 3),(4, 2, 4),(3, 3, 1),(1, 3, 2),(1, 3, 4),(4, 4, 2),(1, 4, 3);", strings.get(2));
        assertEquals("INSERT INTO problems (from_city, to_city) VALUES (1, 4),(2, 4);", strings.get(3));
    }
}