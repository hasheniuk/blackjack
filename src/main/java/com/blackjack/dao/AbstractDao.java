package com.blackjack.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.annotation.*;
import javax.persistence.*;

@RequestScoped
public class AbstractDao {

    @Inject
    private EntityManagerFactorySingleton entityManagerFactorySingleton;
    protected EntityManager entityManager;

    @PostConstruct
    public void postConstruct() {
        EntityManagerFactory entityManagerFactory = entityManagerFactorySingleton.getInstance();
        entityManager = entityManagerFactory.createEntityManager();
    }

    @PreDestroy
    public void preDestroy() {
        entityManager.close();
    }

    protected void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    protected void commitTransaction() {
        entityManager.getTransaction().commit();
    }

    protected void rollbackTransaction() {
        entityManager.getTransaction().rollback();
    }
}
