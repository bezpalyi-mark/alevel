package com.alevel.hibernate;

import com.alevel.hibernate.services.CityService;
import com.alevel.hibernate.services.ConnectionService;
import com.alevel.hibernate.services.ProblemService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriteEntitiesApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(WriteEntitiesApp.class);

    public static void main(String[] args) {
        EntitiesFromFile entities = EntitiesFromFile.readEntities("./hibernate-java-nix/src/main/resources/output.txt");
        if (entities == null) {
            LOGGER.error("Entities is null");
            return;
        }
        Configuration cfg = new Configuration().configure();
        try (SessionFactory sessionFactory = cfg.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            CityService cityService = new CityService(session);
            cityService.save(entities.getCities());

            ConnectionService connectionService = new ConnectionService(session);
            connectionService.save(entities.getConnections());

            ProblemService problemService = new ProblemService(session);
            problemService.save(entities.getProblems());
        }

    }
}
