package com.alevel.jdbcbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConfigurationTest {
    @Test
    void configurationTest() {
        DatabaseConfiguration configuration = new DatabaseConfiguration("testConfiguration.properties");
        assertEquals("jdbc:mysql://localhost:3306/test_graph", configuration.getDatabaseURL());
        assertEquals("root", configuration.getDatabaseUser());
        assertEquals("123456", configuration.getDatabasePassword());
    }

}