package com.alevel.java.nix.dzenmoney.controller;

import com.alevel.java.nix.dzenmoney.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class HibernateDzenMoney {
    private static final Logger logger = LoggerFactory.getLogger(HibernateDzenMoney.class);

    private final Session session;

    private final OperationCreator creator;

    public HibernateDzenMoney(SessionFactory sessionFactory) {
        session = sessionFactory.openSession();
        creator = new OperationCreator();
    }

    public void saveOperation() {
        try {
            Operation operation = creator.createOperation(this);
            if (operation == null) {
                logger.error("Operation add failed");
                return;
            }
            session.beginTransaction();
            session.save(operation);
            session.getTransaction().commit();
            logger.info("Operation added!\n");
        } catch (Exception e) {
            logger.error("Error while executing transaction", e);
            session.getTransaction().rollback();
        }
    }


    public Account getAccount(Long id) {
        Account account = null;
        try {
            session.beginTransaction();
            account = session.find(Account.class, id);
            if (account == null) {
                logger.warn("No account found with id {}", id);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Error while executing transaction: {}", e.getMessage());
            session.getTransaction().rollback();
        }
        return account;
    }

    public User getUser(Long id) {
        User user = null;
        try {
            session.beginTransaction();
            user = session.find(User.class, id);
            if (user == null) {
                logger.warn("No user found with id {}", id);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Error while executing transaction", e);
            session.getTransaction().rollback();
        }
        return user;
    }

    public boolean containsCategories(List<String> categories) {
        try {
            session.beginTransaction();
            for (String category : categories) {
                String query = String.format("from Category WHERE name = '%s' ", category.toLowerCase());

                if (session.createQuery(query, Category.class) == null) return false;
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Error while executing transaction", e);
            session.getTransaction().rollback();
        }
        return true;
    }

    public List<String> getCategories() {
        List<String> categoryNames = new ArrayList<>();
        try {
            session.beginTransaction();

            Query<Consumption> consumptionCategories =
                    session.createQuery("from Consumption ", Consumption.class);
            List<Consumption> listConsumptionCategories = consumptionCategories.list();

            Query<Profit> profitCategories =
                    session.createQuery("from Profit ", Profit.class);
            List<Profit> listProfitCategories = profitCategories.list();

            for (Profit profitCategory : listProfitCategories) {
                categoryNames.add(profitCategory.getCategoryID().getName());
            }

            for (Consumption consumptionCategory : listConsumptionCategories) {
                categoryNames.add(consumptionCategory.getCategoryID().getName());
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Error while executing transaction", e);
            session.getTransaction().rollback();
        }
        return categoryNames;
    }

    public Category getCategory(String name, Class<? extends Category> c) {
        Category category = null;
        try {
            String query = null;
            session.beginTransaction();
            Query<Category> categoryQuery;
            if (c == Profit.class) {
                query = String.format("from Category WHERE name = '%s' AND DTYPE = 'Profit'", name);
            } else if (c == Consumption.class) {
                query = String.format("from Category WHERE name = '%s' AND DTYPE = 'Consumption'", name);
            }
            categoryQuery = session.createQuery(query, Category.class);
            try {
                category = categoryQuery.getSingleResult();
            } catch (NoResultException e) {
                logger.error("No category matches for given type");
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Error while executing transaction", e);
            session.getTransaction().rollback();
        }
        return category;
    }

    public void close() {
        session.close();
    }
}
