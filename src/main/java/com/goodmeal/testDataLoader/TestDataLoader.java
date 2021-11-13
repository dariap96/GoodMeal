package com.goodmeal.testDataLoader;

import com.goodmeal.adapters.impl.SiteToEntityRecipeBaseAdapter;
import com.goodmeal.entities.Ingredient;
import com.goodmeal.entities.IngredientsToRecipes;
import com.goodmeal.entities.Recipe;
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
import java.util.LinkedList;
import java.util.List;

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

    @Autowired
    private TransactionRunner transactionRunner;

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;


    private void loadData(
            SiteToEntityRecipeBaseAdapter recipeBaseAdapter,
            int ingredientIndex,
            int recipeCount
    ) {
        List<Ingredient> ingredients = new LinkedList<>();
        ingredientsRepository.findAll().forEach(ingredients::add);

        int i = ingredientIndex;
        int j = recipeCount;
        try {
            for(; i < ingredients.size(); i++){
                String ingredientName = ingredients.get(i).getName();
                SiteRecipeBase recipeBase = new EdamRecipeRequest(ingredientName).sendRequest();
                j += 1;
                for(; j < recipeBase.getCount() / 10; j++){
                    recipeBase = new EdamRecipeRequest(ingredientName).sendRequest();
                    recipeBaseAdapter.transform(recipeBase);
                }
            }
        } catch (Exception exception) {
            System.out.println("======RecipeInterruption=====");
            System.out.println(exception.getMessage());
            System.out.println("=============================");
            try
            {
                Thread.sleep(60_000);
            }
            catch(InterruptedException ex)
            {
                System.out.println("Dataloader interruption");
                Thread.currentThread().interrupt();
                System.out.println("=======================");
            }
            loadData(recipeBaseAdapter, i, j);
        }
    }

    @PostConstruct
    @Transactional
    public void setup() {
/*
        entityManager.createQuery("DELETE FROM User").executeUpdate();

        User u1 = new User("user1", "1234", "name1", "surname1", "email1@mail.ru", new Date(1, 1, 2000), null);
        User u2 = new User("user2", "4321", "name2", "surname2", "email2@mail.ru", new Date(1, 1, 2000), null);
        entityManager.persist(u1);
        entityManager.persist(u2);
        entityManager.flush();
*/
/*
        SiteToEntityRecipeBaseAdapter recipeBaseAdapter = new SiteToEntityRecipeBaseAdapter(
                recipesRepository,
                ingredientsRepository,
                cuisinesRepository,
                dishesRepository,
                mealsRepository,
                hdLabelsRepository,
                hdLabelTypesRepository,
                ingredientsToRecipesRepository
        );

        SiteRecipeBase siteRecipeBase = new EdamRecipeRequest("apple").sendRequest();
        recipeBaseAdapter.transform(siteRecipeBase);
        loadData(recipeBaseAdapter, 0, 0);
*/
/*
        List<Recipe> recipes = new LinkedList<>();
        recipesRepository.findAll().forEach(recipes::add);
        System.out.println(recipes.size());
*/
/*
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
*/
    }
}
