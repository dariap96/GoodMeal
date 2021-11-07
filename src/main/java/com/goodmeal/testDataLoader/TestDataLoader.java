package com.goodmeal.testDataLoader;

import com.goodmeal.adapters.impl.SiteToEntityRecipeAdapter;
import com.goodmeal.entities.Recipe;
import com.goodmeal.entities.User;
import com.goodmeal.repositoriesImplementations.*;
import com.srcsite.edamrequest.impl.EdamRecipeRequest;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipeBase;
import io.crnk.core.engine.internal.registry.ResourceRegistryImpl;
import io.crnk.core.engine.registry.DefaultResourceRegistryPart;
import io.crnk.core.engine.registry.ResourceRegistryPartListener;
import io.crnk.core.engine.transaction.TransactionRunner;
import io.crnk.core.module.ModuleRegistry;
import io.crnk.core.queryspec.QuerySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.concurrent.Callable;

@Configuration
public class TestDataLoader {
    @Autowired
    private RecipesRepositoryImplementation recipesRepository;
    @Autowired
    private IngredientsRepositoryImplementation ingredientsRepository;
    @Autowired
    private CuisinesRepositoryImplementation cuisinesRepository;
    @Autowired
    private DishesRepositoryImplementation dishesRepository;
    @Autowired
    private MealsRepositoryImplementation mealsRepository;
    @Autowired
    private HealthDietLabelRepositoryImplementation hdLabelsRepository;
    @Autowired
    private HdLabelTypeRepositoryImplementation hdLabelTypesRepository;
    @Autowired
    private IngredientsToRecipesRepositoryImplementation ingredientsToRecipesRepository;
/*

    @PostConstruct
    @Transactional
    public void setup() {

        entityManager.createQuery("DELETE FROM User").executeUpdate();

        User u1 = new User("user1", "1234", "name1", "surname1", "email1@mail.ru", new Date(1, 1, 2000), null);
        User u2 = new User("user2", "4321", "name2", "surname2", "email2@mail.ru", new Date(1, 1, 2000), null);
        entityManager.persist(u1);
        entityManager.persist(u2);
        entityManager.flush();

       // recipesRepository.save(new Recipe())

*/
/*        SiteRecipeBase siteRecipeBase = new EdamRecipeRequest(
                "86eec527",
                "15ab7f74aaa32f92d53df79c9ecdc948",
                "chicken",
                0,
                100)
                .sendRequest();
        recipesRepository.create(
                new Recipe(

                ));

/*
        Recipe recipe = new SiteToEntityRecipeAdapter(
                recipesRepository,
                ingredientsRepository,
                cuisinesRepository,
                dishesRepository,
                mealsRepository,
                hdLabelsRepository,
                hdLabelTypesRepository,
                ingredientsToRecipesRepository
        ).transform(siteRecipeBase.getRecipes().get(0));
*//*



    }
*/

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
