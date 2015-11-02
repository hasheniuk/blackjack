package com.blackjack.dao;

import javax.inject.Singleton;
import javax.annotation.*;
import javax.persistence.*;

@Singleton
public class EntityManagerFactorySingleton {
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void postConstruct() {
        entityManagerFactory = Persistence.createEntityManagerFactory("blackjack");
    }

    @PreDestroy
    public void preDestroy() {
        entityManagerFactory.close();
    }

    public EntityManagerFactory getInstance() {
        return entityManagerFactory;
    }
}
