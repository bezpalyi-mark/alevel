package com.alevel.hibernate;

import com.alevel.hibernate.entity.City;
import com.alevel.hibernate.entity.Connection;
import com.alevel.hibernate.entity.ConnectionId;
import com.alevel.hibernate.entity.Problem;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EntitiesFromFileTest {

    @Test
    void readEntities() {
        EntitiesFromFile entities = EntitiesFromFile.readEntities("./src/test/resources/output.txt");
        assertNotNull(entities);

        City city1 = new City(1L, "gdansk");
        City city2 = new City(2L, "bydgoszcz");
        City city3 = new City(3L, "torun");
        City city4 = new City(4L, "warszawa");
        List<City> citiesExpected = Arrays.asList(city1, city2, city3, city4);

        //from city1
        Connection connection1 = new Connection(new ConnectionId(1, city1, city2));
        Connection connection2 = new Connection(new ConnectionId(3, city1, city3));

        //from city2
        Connection connection3 = new Connection(new ConnectionId(1, city2, city1));
        Connection connection4 = new Connection(new ConnectionId(1, city2, city3));
        Connection connection5 = new Connection(new ConnectionId(4, city2, city4));

        //from city3
        Connection connection6 = new Connection(new ConnectionId(3, city3, city1));
        Connection connection7 = new Connection(new ConnectionId(1, city3, city2));
        Connection connection8 = new Connection(new ConnectionId(1, city3, city4));

        //from city4
        Connection connection9 = new Connection(new ConnectionId(4, city4, city2));
        Connection connection10 = new Connection(new ConnectionId(1, city4, city3));

        List<Connection> connectionsExpected = Arrays.asList(
                connection1, connection2, connection3, connection4, connection5,
                connection6, connection7, connection8, connection9, connection10
        );

        Problem problem1 = new Problem(1L, city1, city4);
        Problem problem2 = new Problem(2L, city2, city4);

        List<Problem> problemsExpected = Arrays.asList(problem1, problem2);

        assertEquals(citiesExpected, entities.getCities());
        assertEquals(connectionsExpected, entities.getConnections());
        assertEquals(problemsExpected, entities.getProblems());
    }
}