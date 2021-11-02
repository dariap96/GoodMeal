package com.goodmeal.testDataLoader;

import com.goodmeal.entities.Ingredient;
import com.goodmeal.entities.User;
import io.crnk.core.engine.transaction.TransactionRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Date;
import java.util.concurrent.Callable;

@Configuration
public class TestDataLoader {

    @Autowired
    private TransactionRunner transactionRunner;

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void setup() {
        transactionRunner.doInTransaction(new Callable<Object>() {
            @Override
            public Object call() throws Exception {

                entityManager.createQuery("DELETE FROM User").executeUpdate();

                User u1 = new User("user1", "1234", "name1", "surname1", "email1@mail.ru", new Date(1, 1, 2000), null);
                User u2 = new User("user2", "4321", "name2", "surname2", "email2@mail.ru", new Date(1, 1, 2000), null);
                entityManager.persist(u1);
                entityManager.persist(u2);
                entityManager.flush();
                return null;
            }
        });
    }
}
