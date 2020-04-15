package com.alevel.jdbcbox.graph;

import com.alevel.jdbcbox.DatabaseConnection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphRequestTest {

    private static Connection connection;

    private static DatabaseConnection databaseConnection;

    private static GraphRequest graphRequest;

    @BeforeAll
    static void begin() {
        databaseConnection = new DatabaseConnection("testConfiguration.properties");
        databaseConnection.establishConnection();
        graphRequest = new GraphRequest(databaseConnection);
        connection = databaseConnection.getConnection();
    }

    @AfterAll
    static void end() {
        databaseConnection.closeConnection();
    }


    @BeforeEach
    void setUp() throws IOException, SQLException {
        graphRequest.executeSQLScript("./graph/test_scripts/delete_all_tables.sql");
        graphRequest.executeSQLScript("./graph/test_scripts/create_tables.sql");
        graphRequest.executeSQLScript("./graph/test_scripts/init_tables.sql");
    }

    @Test
    void getCities() {
        List<String> expected = Arrays.asList(
                "gdansk",
                "bydgoszcz",
                "torun",
                "warszawa"
        );
        assertEquals(expected, graphRequest.getCities());
    }

    @Test
    void getConnections() {
        GraphConnectionsFromDatabase expected = new GraphConnectionsFromDatabase();
        expected.addConnection(1, 2, 1);
        expected.addConnection(1, 3, 3);
        expected.addConnection(2, 1, 1);
        expected.addConnection(2, 3, 1);
        expected.addConnection(2, 4, 4);
        expected.addConnection(3, 1, 3);
        expected.addConnection(3, 2, 1);
        expected.addConnection(3, 4, 1);
        expected.addConnection(4, 2, 4);
        expected.addConnection(4, 3, 1);

        GraphConnectionsFromDatabase actual = graphRequest.getConnections();

        for(int i = 0, size = expected.size(); i < size; i++) {
            assertEquals(expected.nextFrom(), actual.nextFrom());
            assertEquals(expected.nextTo(), actual.nextTo());
            assertEquals(expected.nextCost(), actual.nextCost());
        }
    }

    @Test
    void getProblems() {
    }

    @Test
    void writeFoundConnection() {
    }

    @Test
    void writeImpossibleConnection() {
    }
}