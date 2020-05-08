package com.alevel.java.nix.dzenmoney;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import static com.alevel.hibernate.HibernateGraphApp.readSQLScript;

public class InitDatabase {
    private static final Logger logger = LoggerFactory.getLogger(InitDatabase.class);

    public static void load(SessionFactory sessionFactory) throws IOException {
        try (Session session = sessionFactory.openSession()) {
            List<String> list = readSQLScript(InitDatabase.class.getResource("/init_tables.sql").getPath());
            if (list != null) {
                try {
                    session.beginTransaction();
                    for (String sqlCommand : list) {
                        session.createNativeQuery(sqlCommand).executeUpdate();
                    }
                    session.getTransaction().commit();
                } catch (Exception e) {
                    logger.error("Error while executing initial script");
                    session.getTransaction().rollback();
                }
            }
        }
    }
}
