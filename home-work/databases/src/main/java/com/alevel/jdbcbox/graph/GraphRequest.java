package com.alevel.jdbcbox.graph;

import com.alevel.jdbcbox.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphRequest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConnection.class);

    private static final String REQUEST_CITIES_NAMES = "SELECT name FROM city ORDER BY id;";

    private static final String REQUEST_CONNECTIONS = "SELECT from_city, to_city, cost FROM connection;";

    private static final String REQUEST_PROBLEMS = "SELECT from_city, to_city FROM problems ORDER BY id;";

    private final Connection connection;

    GraphRequest(DatabaseConnection connection) {
        this.connection = connection.getConnection();
    }

    /**
     * Extracting cities names.
     *
     * @return list with cities names.
     */
    public List<String> getCities() {
        List<String> cities = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQUEST_CITIES_NAMES);
            while (resultSet.next()) {
                cities.add(resultSet.getString("name"));
            }
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("Failed to extract cities names! {}", e.getMessage());
        }
        return cities;
    }


    public GraphConnectionsFromDatabase getConnections() {
        GraphConnectionsFromDatabase connections = new GraphConnectionsFromDatabase();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQUEST_CONNECTIONS);
            while (resultSet.next()) {
                int from = resultSet.getInt("from_city");
                int to = resultSet.getInt("to_city");
                int cost = resultSet.getInt("cost");
                connections.addConnection(from, to, cost);
            }
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("Failed to extract connections! {}", e.getMessage());
        }
        return connections;
    }

    public Map<Integer, Integer> getProblems() {
        Map<Integer, Integer> problems = new HashMap<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQUEST_PROBLEMS);
            while (resultSet.next()) {
                int from = resultSet.getInt("from_city");
                int to = resultSet.getInt("to_city");
                problems.put(from, to);
            }
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("Failed to extract problems! {}", e.getMessage());
        }
        return problems;
    }

    public boolean writeFoundConnection(int problemId, int minCost) {
        boolean result = false;
        String insert = String.format("INSERT INTO found_routes (problem, min_cost) VALUES (%d, %d);", problemId, minCost);
        try {
            Statement statement = connection.createStatement();
            result = statement.execute(insert);
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("Failed to write found connection! {}", e.getMessage());
        }
        return result;
    }

    public boolean writeImpossibleConnection(int problemId) {
        boolean result = false;
        String insert = String.format("INSERT INTO impossible_routes (problem) VALUES (%d);", problemId);
        try {
            Statement statement = connection.createStatement();
            result = statement.execute(insert);
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("Failed to write impossible connection! {}", e.getMessage());
        }
        return result;
    }

}
