package com.goodmeal.testDataLoader;

import com.goodmeal.adapters.impl.SiteToEntityRecipeAdapter;
import com.goodmeal.entities.*;
import com.goodmeal.repositoriesImplementations.*;
import com.goodmeal.services.impl.*;
import com.srcsite.edamrequest.impl.EdamRecipeRequest;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipeBase;
import io.crnk.core.engine.transaction.TransactionRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;
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
    @Autowired
    private RecipesService recipesService;

    @Transactional
    public void setup() {

        System.out.print(recipesService.findAll());

//
//        SiteRecipeBase siteRecipeBase = new EdamRecipeRequest(
//                "86eec527",
//                "15ab7f74aaa32f92d53df79c9ecdc948",
//                "chicken",
//                0,
//                100)
//                .sendRequest();
//
//        Recipe recipe = new SiteToEntityRecipeAdapter(
//                recipesRepository,
//                ingredientsRepository,
//                cuisinesRepository,
//                dishesRepository,
//                mealsRepository,
//                hdLabelsRepository,
//                hdLabelTypesRepository,
//                ingredientsToRecipesRepository
//        ).transform(siteRecipeBase.getRecipes().get(0));
//
//
//        transactionRunner.doInTransaction(new Callable<Object>() {
//            @Override
//            public Object call() throws Exception {
//                entityManager.createQuery("DELETE FROM User").executeUpdate();
//                User u1 = new User("user1", "1234", "name1", "surname1", "email1@mail.ru", new Date(1, 1, 2000), null);
//                User u2 = new User("user2", "4321", "name2", "surname2", "email2@mail.ru", new Date(1, 1, 2000), null);
//                entityManager.persist(u1);
//                entityManager.persist(u2);
//                entityManager.flush();
//                return null;
//            }
//        });

    }

    @Autowired
    private TransactionRunner transactionRunner;

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;


}
