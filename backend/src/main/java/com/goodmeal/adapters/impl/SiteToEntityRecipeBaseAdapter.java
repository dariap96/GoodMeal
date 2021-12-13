package com.goodmeal.adapters.impl;

import com.goodmeal.adapters.SiteToEntityAdapter;
import com.goodmeal.entities.Recipe;
import com.goodmeal.repositoriesImplementations.*;
import com.goodmeal.services.impl.*;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipe;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipeBase;

import java.util.LinkedList;
import java.util.List;

public class SiteToEntityRecipeBaseAdapter implements SiteToEntityAdapter<SiteRecipeBase, List<Recipe>> {

    private final RecipesService recipesRepository;
    private final IngredientsService ingredientsRepository;
    private final CuisinesService cuisinesRepository;
    private final DishesService dishesRepository;
    private final MealsService mealsRepository;
    private final HealthDietLabelsService hdLabelsRepository;
    private final HdLabelTypesService hdLabelTypesRepository;
    private final IngredientsToRecipesService ingredientsToRecipesRepository;


    public SiteToEntityRecipeBaseAdapter(
            RecipesService recipesService,
            IngredientsService ingredientsService,
            CuisinesService cuisinesService,
            DishesService dishesService,
            MealsService mealsService,
            HealthDietLabelsService healthDietLabelsService,
            HdLabelTypesService hdLabelTypesService,
            IngredientsToRecipesService ingredientsToRecipesService
    ){
        this.recipesRepository = recipesService;
        this.ingredientsRepository = ingredientsService;
        this.cuisinesRepository = cuisinesService;
        this.dishesRepository = dishesService;
        this.mealsRepository = mealsService;
        this.hdLabelsRepository = healthDietLabelsService;
        this.hdLabelTypesRepository = hdLabelTypesService;
        this.ingredientsToRecipesRepository = ingredientsToRecipesService;
    }

    @Override
    public List<Recipe> transform(SiteRecipeBase siteRecipeBase) {
        List<Recipe> recipeBase = new LinkedList<>();
        for (SiteRecipe siteRecipe : siteRecipeBase.getRecipes()) {
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
