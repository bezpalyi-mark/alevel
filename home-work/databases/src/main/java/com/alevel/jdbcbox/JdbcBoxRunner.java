package com.alevel.jdbcbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcBoxRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcBoxRunner.class);

    public static void main(String[] args) {
        DatabaseConnection connection = new DatabaseConnection("configurations.properties");
        if(connection.establishConnection()) {
            LOGGER.info("Successfully connected to database");
        }
        connection.closeConnection();
    }
}
