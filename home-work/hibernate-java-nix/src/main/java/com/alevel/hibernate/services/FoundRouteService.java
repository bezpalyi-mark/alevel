package com.alevel.hibernate.services;

import com.alevel.hibernate.entity.City;
import com.alevel.hibernate.entity.FoundRoute;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FoundRouteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FoundRouteService.class);

    private final Session session;

    public FoundRouteService(Session session) {
        this.session = session;
    }

    public void save(List<FoundRoute> foundRoutes) {
        try {
            session.beginTransaction();
            for (FoundRoute foundRoute : foundRoutes) {
                session.save(foundRoute);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to save foundRoute", e);
        }
    }

    public void save(FoundRoute foundRoute) {
        try {
            session.beginTransaction();
            session.save(foundRoute);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to save foundRoute", e);
        }
    }

    public void delete(FoundRoute foundRoute) {
        try {
            session.beginTransaction();
            session.delete(foundRoute);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to delete foundRoute", e);
        }
    }

    public void update(FoundRoute foundRoute) {
        try {
            session.beginTransaction();
            session.update(foundRoute);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to update foundRoute", e);
        }
    }

    public FoundRoute get(Long id) {
        return session.find(FoundRoute.class, id);
    }
}
