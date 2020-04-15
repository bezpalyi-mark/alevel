package com.alevel.jdbcbox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConnection {
    /**
     * Class for gets configurations.
     */
    private final DatabaseConfiguration configurationsDB;

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConnection.class);

    /**
     * Connection to DB.
     */
    private Connection connection;

    /**
     * Constructor with params.
     *
     * @param configFile path to config file.
     */
    public DatabaseConnection(final String configFile) {
        configurationsDB = new DatabaseConfiguration(configFile);
    }

    /**
     * Function to connect to database.
     *
     * @return connection to database.
     */
    private Connection connectToMySQL() {
        final String databaseURL = configurationsDB.getDatabaseURL();
        final String user = configurationsDB.getDatabaseUser();
        final String password = configurationsDB.getDatabasePassword();

        try {
            return DriverManager.getConnection(databaseURL, user, password);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    /**
     * Function for establishing connection.
     *
     * @return is connected.
     */
    public boolean establishConnection() {
        connection = connectToMySQL();
        return connection != null;
    }

    /**
     * Method to close connection.
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Error when trying to close connection");
        }
    }

    /**
     * Getter for connection.
     *
     * @return connection.
     */
    public Connection getConnection() {
        return connection;
    }
}
