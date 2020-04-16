package com.alevel.jdbcbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {
    @Test
    void databaseConnectionTest() {
        DatabaseConnection connection = new DatabaseConnection("testConfiguration.properties");
        assertTrue(connection.establishConnection());
        assertNotNull(connection.getConnection());
        connection.closeConnection();
    }
}