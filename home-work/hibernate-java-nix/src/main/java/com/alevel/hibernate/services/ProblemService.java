package com.alevel.hibernate.services;

import com.alevel.hibernate.entity.City;
import com.alevel.hibernate.entity.Problem;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProblemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProblemService.class);

    private final Session session;

    public ProblemService(Session session) {
        this.session = session;
    }

    public void save(List<Problem> problems) {
        try {
            session.beginTransaction();
            for (Problem problem : problems) {
                session.save(problem);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to save problem", e);
        }
    }

    public void save(Problem problem) {
        try {
            session.beginTransaction();
            session.save(problem);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to save problem", e);
        }
    }

    public void delete(Problem problem) {
        try {
            session.beginTransaction();
            session.delete(problem);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to delete problem", e);
        }
    }

    public void update(Problem problem) {
        try {
            session.beginTransaction();
            session.update(problem);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Failed to update problem", e);
        }
    }

    public Problem get(Long id) {
        return session.find(Problem.class, id);
    }
}
