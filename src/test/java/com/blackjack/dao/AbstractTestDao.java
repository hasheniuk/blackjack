package com.blackjack.dao;

import org.jglue.cdiunit.CdiRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import javax.annotation.*;
import javax.inject.Inject;
import javax.persistence.*;

@RunWith(CdiRunner.class)
public class AbstractTestDao {

    @Inject
    private EntityManagerFactorySingleton entityManagerFactorySingleton;
    protected EntityManager entityManager;

    @Before
    public void postConstruct() {
        EntityManagerFactory entityManagerFactory = entityManagerFactorySingleton.getInstance();
        entityManager = entityManagerFactory.createEntityManager();
    }

    @After
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
