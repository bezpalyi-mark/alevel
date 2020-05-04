package com.alevel.hibernate.services;

import com.alevel.hibernate.entity.City;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityService.class);

    private final Session session;

    public CityService(Session session) {
        this.session = session;
    }

    public void save(List<City> cities) {
        try {
            session.beginTransaction();
            for (City city : cities) {
                session.save(city);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to save city", e);
        }
    }

    public void save(City city) {
        try {
            session.beginTransaction();
            session.save(city);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to save city", e);
        }
    }

    public void delete(City city) {
        try {
            session.beginTransaction();
            session.delete(city);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to delete city", e);
        }
    }

    public void update(City city) {
        try {
            session.beginTransaction();
            session.update(city);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to update city", e);
        }
    }

    public City get(Long id) {
        return session.find(City.class, id);
    }
}
