package com.goodmeal.adapters.impl;

import com.goodmeal.adapters.SiteToEntityAdapter;
import com.goodmeal.entities.Recipe;
import com.goodmeal.repositoriesImplementations.*;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipe;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipeBase;

import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;

public class SiteToEntityRecipeBaseAdapter implements SiteToEntityAdapter<SiteRecipeBase, List<Recipe>> {
    private final RecipesRepositoryImplementation recipesRepository;
    private final IngredientsRepositoryImplementation ingredientsRepository;
    private final CuisinesRepositoryImplementation cuisinesRepository;
    private final DishesRepositoryImplementation dishesRepository;
    private final MealsRepositoryImplementation mealsRepository;
    private final HealthDietLabelRepositoryImplementation hdLabelsRepository;
    private final HdLabelTypeRepositoryImplementation hdLabelTypesRepository;
    private final IngredientsToRecipesRepositoryImplementation ingredientsToRecipesRepository;


    public SiteToEntityRecipeBaseAdapter(
            RecipesRepositoryImplementation recipesRepo,
            IngredientsRepositoryImplementation ingredientsRepo,
            CuisinesRepositoryImplementation cuisinesRepo,
            DishesRepositoryImplementation dishesRepo,
            MealsRepositoryImplementation mealsRepo,
            HealthDietLabelRepositoryImplementation hdLabelsRepo,
            HdLabelTypeRepositoryImplementation hdLabelTypesRepo,
            IngredientsToRecipesRepositoryImplementation ingredientsToRecipesRepo
    ){
        this.recipesRepository = recipesRepo;
        this.ingredientsRepository = ingredientsRepo;
        this.cuisinesRepository = cuisinesRepo;
        this.dishesRepository = dishesRepo;
        this.mealsRepository = mealsRepo;
        this.hdLabelsRepository = hdLabelsRepo;
        this.hdLabelTypesRepository = hdLabelTypesRepo;
        this.ingredientsToRecipesRepository = ingredientsToRecipesRepo;
    }

    @Override
    public List<Recipe> transform(SiteRecipeBase siteRecipeBase) {
        List<Recipe> recipeBase = new LinkedList<>();
        for (SiteRecipe siteRecipe : siteRecipeBase.getRecipes()){
            recipeBase.add(new SiteToEntityRecipeAdapter(
                    recipesRepository,
                    ingredientsRepository,
                    cuisinesRepository,
                    dishesRepository,
                    mealsRepository,
                    hdLabelsRepository,
                    hdLabelTypesRepository,
                    ingredientsToRecipesRepository
            ).transform(siteRecipe));
        }
        return recipeBase;
    }
}
