package com.goodmeal.adapters.impl;

import com.goodmeal.adapters.SiteToEntityAdapter;
import com.goodmeal.entities.Recipe;
import com.goodmeal.services.impl.*;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipe;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipeBase;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class SiteToEntityRecipeBaseAdapter implements SiteToEntityAdapter<SiteRecipeBase, List<Recipe>> {

    private final RecipesService recipesService;
    private final IngredientsService ingredientsService;
    private final CuisinesService cuisinesService;
    private final DishesService dishesService;
    private final MealsService mealsService;
    private final HealthDietLabelsService healthDietLabelsService;
    private final HdLabelTypesService hdLabelTypesService;
    private final IngredientsToRecipesService ingredientsToRecipesService;
    private final APIFoodKeysService apiFoodKeysService;

    @Override
    public List<Recipe> transform(SiteRecipeBase siteRecipeBase) {
        List<Recipe> recipeBase = new LinkedList<>();
        for (SiteRecipe siteRecipe : siteRecipeBase.getRecipes()) {
            recipeBase.add(new SiteToEntityRecipeAdapter(
                    recipesService,
                    ingredientsService,
                    cuisinesService,
                    dishesService,
                    mealsService,
                    healthDietLabelsService,
                    hdLabelTypesService,
                    ingredientsToRecipesService,
                    apiFoodKeysService
            ).transform(siteRecipe));
        }
        return recipeBase;
    }
}
