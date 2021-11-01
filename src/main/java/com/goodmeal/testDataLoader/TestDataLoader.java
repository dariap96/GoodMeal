package com.goodmeal.testDataLoader;

import com.goodmeal.entities.Ingredient;
import io.crnk.core.engine.transaction.TransactionRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
                Ingredient ing1 = new Ingredient( "ingr1", 100F, 101F, 102F, 103F, 104F, "null", "SomeId");
                entityManager.persist(ing1);
                entityManager.flush();
                return null;
            }
        });
    }

}
