package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Cuisine;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.engine.transaction.TransactionRunner;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

@Component
public class CuisinesRepositoryImplementation extends JpaEntityRepositoryBase<Cuisine, Long> implements IRepository<Cuisine,Long> {

    //    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TransactionRunner transactionRunner;

    private Map<Long, Cuisine> cuisines = new HashMap<>();

    public CuisinesRepositoryImplementation() {
        super(Cuisine.class);
    }

    @Override
    public ResourceList<Cuisine> findAll(QuerySpec querySpec) {
        return querySpec.apply(cuisines.values());
    }

//    @PostConstruct
    public void addToDatabase(Cuisine cuisine) {
/*        transactionRunner.doInTransaction(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                entityManager.persist(cuisine);
                entityManager.flush();
                return cuisine;
            }
        });
*/    }
}
