package com.alevel.hibernate.services;

import com.alevel.hibernate.entity.City;
import com.alevel.hibernate.entity.Connection;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ConnectionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionService.class);

    private final Session session;

    public ConnectionService(Session session) {
        this.session = session;
    }

    public void save(List<Connection> connections) {
        try {
            session.beginTransaction();
            for (Connection connection : connections) {
                session.save(connection);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to save connection", e);
        }
    }

    public void save(Connection connection) {
        try {
            session.beginTransaction();
            session.save(connection);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to save Connection", e);
        }
    }

    public void delete(Connection connection) {
        try {
            session.beginTransaction();
            session.delete(connection);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to delete Connection", e);
        }
    }

    public void update(Connection connection) {
        try {
            session.beginTransaction();
            session.update(connection);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to update Connection", e);
        }
    }

    public Connection get(Long id) {
        return session.find(Connection.class, id);
    }
}
