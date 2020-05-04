package com.alevel.hibernate.services;

import com.alevel.hibernate.entity.City;
import com.alevel.hibernate.entity.ImpossibleRoute;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ImpossibleRouteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImpossibleRouteService.class);

    private final Session session;

    public ImpossibleRouteService(Session session) {
        this.session = session;
    }

    public void save(List<ImpossibleRoute> impossibleRoutes) {
        try {
            session.beginTransaction();
            for (ImpossibleRoute impossibleRoute : impossibleRoutes) {
                session.save(impossibleRoute);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to save impossibleRoute", e);
        }
    }

    public void save(ImpossibleRoute impossibleRoute) {
        try {
            session.beginTransaction();
            session.save(impossibleRoute);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to save impossibleRoute", e);
        }
    }

    public void delete(ImpossibleRoute impossibleRoute) {
        try {
            session.beginTransaction();
            session.delete(impossibleRoute);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to delete impossibleRoute", e);
        }
    }

    public void update(ImpossibleRoute impossibleRoute) {
        try {
            session.beginTransaction();
            session.update(impossibleRoute);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to update impossibleRoute", e);
        }
    }

    public ImpossibleRoute get(Long id) {
        return session.find(ImpossibleRoute.class, id);
    }

}
