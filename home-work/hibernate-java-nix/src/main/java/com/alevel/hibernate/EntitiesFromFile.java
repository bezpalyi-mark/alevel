package com.alevel.hibernate;

import com.alevel.hibernate.entity.City;
import com.alevel.hibernate.entity.Connection;
import com.alevel.hibernate.entity.Problem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class EntitiesFromFile {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntitiesFromFile.class);

    private final List<City> cities;

    private final List<Connection> connections;

    private final List<Problem> problems;

    private static Map<String, Integer> cityNameIndex;

    private EntitiesFromFile(List<City> cities, List<Connection> connections, List<Problem> problems) {
        this.cities = cities;
        this.connections = connections;
        this.problems = problems;
    }

    public static EntitiesFromFile readEntities(String path) {
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            LOGGER.error("Cannot read file: {}", path);
            return null;
        }

        List<City> cities = readCities(path);
        List<Connection> connections = new ArrayList<>();
        List<Problem> problems = new ArrayList<>();

        int cityCounter = 0;
        try {
            Scanner scanner = new Scanner(file);
            int citySize = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < citySize; i++) {
                scanner.nextLine();
                int neighboursSize = scanner.nextInt();
                scanner.nextLine();
                for (int j = 0; j < neighboursSize; j++) {
                    Connection connection = new Connection();
                    connection.setFromCity(cities.get(cityCounter));
                    connection.setToCity(cities.get(scanner.nextInt() - 1));
                    connection.setCost(scanner.nextInt());
                    connections.add(connection);
                    scanner.nextLine();
                }
                cityCounter++;
            }

            int problemsSize = scanner.nextInt();
            for (int i = 0; i < problemsSize; i++) {
                Problem problem = new Problem();
                problem.setId((long) i + 1);
                int indexFrom = cityNameIndex.get(scanner.next());
                problem.setFromCity(cities.get(indexFrom));
                int indexTo = cityNameIndex.get(scanner.next());
                problem.setToCity(cities.get(indexTo));
                problems.add(problem);
                scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("Failed to parse file", e);
        }
        return new EntitiesFromFile(cities, connections, problems);
    }

    private static List<City> readCities(String path) {
        List<City> cities = new ArrayList<>();
        cityNameIndex = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            int citySize = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < citySize; i++) {
                City city = new City((long) i + 1, scanner.nextLine());
                cities.add(city);
                cityNameIndex.put(city.getName(), i);
                int skipSize = scanner.nextInt();
                scanner.nextLine();
                for (int j = 0; j < skipSize; j++) {
                    scanner.nextLine();
                }
            }

        } catch (FileNotFoundException e) {
            LOGGER.error("Failed to parse file", e);
        }
        return cities;
    }

    public List<City> getCities() {
        return cities;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public List<Problem> getProblems() {
        return problems;
    }
}
