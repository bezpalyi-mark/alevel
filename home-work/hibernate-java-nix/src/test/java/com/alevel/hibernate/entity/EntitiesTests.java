package com.alevel.hibernate.entity;

import com.alevel.hibernate.HibernateGraphApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class EntitiesTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateGraphApp.class);

    @Test
    public void testEntities() {

        City city1 = new City();
        city1.setId(1L);
        city1.setName("Kiev");

        City city2 = new City();
        city1.setId(2L);
        city2.setName("Odessa");

        City city3 = new City();
        city1.setId(3L);
        city3.setName("New York");

        City city4 = new City();
        city1.setId(4L);
        city4.setName("Gravity False");

        List<City> cityExpected = Arrays.asList(
                city1, city2, city3, city4
        );

        Connection connection1 = new Connection();
        connection1.setCost(12);
        connection1.setFromCity(city1);
        connection1.setToCity(city3);

        Connection connection2 = new Connection();
        connection2.setCost(20);
        connection2.setFromCity(city3);
        connection2.setToCity(city2);

        List<Connection> connectionExpected = Arrays.asList(
                connection1, connection2
        );

        Problem problem1 = new Problem();
        problem1.setId(1L);
        problem1.setFromCity(city1);
        problem1.setToCity(city2);

        Problem problem2 = new Problem();
        problem2.setId(2L);
        problem2.setFromCity(city3);
        problem2.setToCity(city4);

        List<Problem> problemExpected = Arrays.asList(
                problem1, problem2
        );

        FoundRoute foundRoute = new FoundRoute();
        foundRoute.setMinCost(32);
        foundRoute.setProblem(problem1);

        List<FoundRoute> foundRouteExpected = Collections.singletonList(foundRoute);

        ImpossibleRoute impossibleRoute = new ImpossibleRoute();
        impossibleRoute.setProblem(problem2);

        List<ImpossibleRoute> impossibleRouteExpected = Collections.singletonList(impossibleRoute);

        List<City> actualCities = null;
        List<Connection> actualConnections = null;
        List<Problem> actualProblems = null;
        List<FoundRoute> actualFoundRouts = null;
        List<ImpossibleRoute> actualImpossibleRouts = null;

        File cfgFile = new File("./src/test/resources/hibernate.cfg.xml");
        Configuration cfg = new Configuration().configure(cfgFile);
        try (SessionFactory sessionFactory = cfg.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.save(city1);
                session.save(city2);
                session.save(city3);
                session.save(city4);
                session.save(connection1);
                session.save(connection2);
                session.save(problem1);
                session.save(problem2);
                session.save(foundRoute);
                session.save(impossibleRoute);
                session.getTransaction().commit();

                actualCities = session
                        .createQuery("from City ORDER BY id", City.class)
                        .list();
                actualConnections = session
                        .createQuery("from Connection ORDER BY cost", Connection.class)
                        .list();
                actualProblems = session
                        .createQuery("from Problem", Problem.class)
                        .list();
                actualFoundRouts = session
                        .createQuery("from FoundRoute", FoundRoute.class)
                        .list();
                actualImpossibleRouts = session
                        .createQuery("from ImpossibleRoute", ImpossibleRoute.class)
                        .list();

            } catch (Exception e) {
                LOGGER.error("Error while transaction: ", e);
                session.getTransaction().rollback();
            }
        }

        assertNotNull(actualCities);
        for (int i = 0; i < cityExpected.size(); i++) {
            assertEquals(cityExpected.get(i).getName(), actualCities.get(i).getName());
        }

        assertNotNull(actualConnections);
        for (int i = 0; i < connectionExpected.size(); i++) {
            assertEquals(connectionExpected.get(i).getCost(), actualConnections.get(i).getCost());
            assertEquals(connectionExpected.get(i).getFromCity().getName(), actualConnections.get(i).getFromCity().getName());
            assertEquals(connectionExpected.get(i).getToCity().getName(), actualConnections.get(i).getToCity().getName());
        }

        assertNotNull(actualProblems);
        for (int i = 0; i < problemExpected.size(); i++) {
            assertEquals(problemExpected.get(i).getFromCity().getName(), actualProblems.get(i).getFromCity().getName());
            assertEquals(problemExpected.get(i).getToCity().getName(), actualProblems.get(i).getToCity().getName());
        }

        assertNotNull(actualFoundRouts);
        for (int i = 0; i < foundRouteExpected.size(); i++) {
            assertEquals(foundRouteExpected.get(i).getMinCost(), actualFoundRouts.get(i).getMinCost());
            assertEquals(
                    foundRouteExpected.get(i).getProblem().getFromCity().getName(),
                    actualFoundRouts.get(i).getProblem().getFromCity().getName()
            );
            assertEquals(
                    foundRouteExpected.get(i).getProblem().getToCity().getName(),
                    actualFoundRouts.get(i).getProblem().getToCity().getName()
            );
        }

        assertNotNull(actualImpossibleRouts);
        for (int i = 0; i < impossibleRouteExpected.size(); i++) {
            assertEquals(
                    impossibleRouteExpected.get(i).getProblem().getFromCity().getName(),
                    actualImpossibleRouts.get(i).getProblem().getFromCity().getName()
            );
            assertEquals(
                    impossibleRouteExpected.get(i).getProblem().getToCity().getName(),
                    actualImpossibleRouts.get(i).getProblem().getToCity().getName()
            );
        }
    }
}
