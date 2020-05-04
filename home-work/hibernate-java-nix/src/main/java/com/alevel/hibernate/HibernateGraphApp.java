package com.alevel.hibernate;

import com.alevel.hibernate.entity.*;
import com.alevel.java.nix.module2.graph.Graph;
import com.alevel.java.nix.module2.graph.GraphResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


public class HibernateGraphApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateGraphApp.class);

    public static void main(String[] args) {
        List<String> sqlScript = null;
        HibernateGraphHandler graphHandler = new HibernateGraphHandler();
        try {
            sqlScript = readSQLScript("./databases/graph/scripts/init_tables.sql");
        } catch (IOException e) {
            LOGGER.error("Failed to read init sql script", e);
        }

        Configuration cfg = new Configuration().configure();

        try (SessionFactory sessionFactory = cfg.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            try {
                //Executing sql script in separate transaction.
                if (sqlScript != null) {
                    session.beginTransaction();
                    for (String s : sqlScript) {
                        session.createNativeQuery(s).executeUpdate();
                    }
                    session.getTransaction().commit();
                }

                //Getting all cities, connections and problems
                List<City> listCities = session.createQuery("from City", City.class).list();
                List<Connection> listConnections = session.createQuery("from Connection", Connection.class).list();
                List<Problem> listProblems = session.createQuery("from Problem", Problem.class).list();

                //Assert data not null
                if (listCities == null || listConnections == null || listProblems == null) {
                    throw new RuntimeException("Empty data from db");
                }

                //Filling graph with cities and connections
                Graph graph = graphHandler.fillGraph(listCities, listConnections);
                //Converting problems to HashMap
                Map<Long, Long> problems = graphHandler.mapProblems(listProblems);

                //Begging of new transaction
                session.beginTransaction();
                int idCounter = 0;
                for (var entry : problems.entrySet()) {

                    //Getting shortest way between two cities
                    GraphResult shortestWay = graph.getShortestWay(
                            listCities.get((int) (entry.getKey() - 1)).getName(),
                            listCities.get((int) (entry.getValue() - 1)).getName()
                    );

                    //if way does not exist, write information to impossible_routes table
                    if (shortestWay == null) {
                        ImpossibleRoute impossibleRoute = new ImpossibleRoute();
                        impossibleRoute.setProblem(listProblems.get(idCounter));
                        session.save(impossibleRoute);
                    } else {

                        //if way exists, write information to found_routes table
                        FoundRoute foundRoute = new FoundRoute();
                        foundRoute.setProblem(listProblems.get(idCounter));
                        foundRoute.setMinCost(shortestWay.getLength());
                        session.save(foundRoute);
                    }
                    idCounter++;
                }
                session.getTransaction().commit();
            } catch (Exception e) {
                LOGGER.error("Error while work with transactions", e);
                session.getTransaction().rollback();
            }
        }
    }

    /**
     * Method to read SQL script and formatting data.
     *
     * @param pathToFile path to file.
     * @return list with SQL commands.
     * @throws IOException error while work with files.
     */
    public static List<String> readSQLScript(final String pathToFile) throws IOException {
        List<String> statements = new ArrayList<>();
        FileInputStream fileReader = new FileInputStream(pathToFile);
        byte[] buffer = new byte[fileReader.available()];
        if (fileReader.read(buffer, 0, fileReader.available()) == -1) {
            LOGGER.error("Failed to read file {}!", pathToFile);
            return null;
        }
        StringBuilder query = new StringBuilder();
        for (byte b : buffer) {
            query.append((char) b);
        }
        String filteredQuery = query.toString();
        //Deleting redundant '\n' and whitespaces.
        filteredQuery = filteredQuery.replace("\n", " ").replaceAll("\\s{2,}", "");
        StringTokenizer stringTokenizer = new StringTokenizer(filteredQuery, ";");
        while (stringTokenizer.hasMoreTokens()) {
            statements.add(stringTokenizer.nextToken() + ";");
        }
        return statements;
    }

}
