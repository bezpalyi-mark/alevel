package com.alevel.jdbcbox;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfiguration {
    /**
     * File with configurations for database.
     */
    private final Properties properties;

    /**
     * Constructor with params.
     *
     * @param configName path to config file.
     */
    public DatabaseConfiguration(final String configName) {
        properties = loadProperties(configName);
    }

    private static Properties loadProperties(String configName) {
        var props = new Properties();

        try(InputStream input = DatabaseConfiguration.class.getResourceAsStream(configName)) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }


    /**
     * Getter for URL.
     *
     * @return URL of database.
     */
    public String getDatabaseURL() {
        return properties.getProperty("mysql.database.url");
    }

    /**
     * Getter for User.
     *
     * @return User of database.
     */
    public String getDatabaseUser() {
        return  properties.getProperty("mysql.user");
    }

    /**
     * Getter for Password.
     *
     * @return Password to database.
     */
    public String getDatabasePassword() {
        return  properties.getProperty("mysql.password");
    }

}
