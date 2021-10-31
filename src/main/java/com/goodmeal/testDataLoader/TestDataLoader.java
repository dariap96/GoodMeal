package com.goodmeal.testDataLoader;

import com.goodmeal.entities.Ingridient;
import io.crnk.core.engine.transaction.TransactionRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;
import java.util.concurrent.Callable;

@Configuration
public class TestDataLoader {

    @Autowired
    private TransactionRunner transactionRunner;

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;


    // ------ CAUSES ERROR ------
    // Viktor: добавлено мной, при разкомментировании – org.hibernate.PersistentObjectException: detached entity passed to persist: com.goodmeal.entities.Ingridient

    @PostConstruct
    public void setup() {
        transactionRunner.doInTransaction(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Ingridient ing1 = new Ingridient( "ingr1", 100F, 101F, 102F, 103F, 104F, "null", UUID.randomUUID());
                entityManager.persist(ing1);
                entityManager.flush();
                return null;
            }
        });
    }

}
