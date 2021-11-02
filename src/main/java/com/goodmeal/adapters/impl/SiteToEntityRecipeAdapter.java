package com.goodmeal.adapters.impl;

import com.goodmeal.adapters.SiteToEntityAdapter;

import com.goodmeal.entities.*;

import com.goodmeal.repositoriesImplementations.*;

import com.srcsite.edamrequest.impl.EdamIngredientRequest;
import com.srcsite.siteDataBase.siteIngredientDataBase.SiteIngredientBase;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteIngredient;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipe;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SiteToEntityRecipeAdapter implements SiteToEntityAdapter<SiteRecipe, Recipe> {
    private Ingredient createIngredient(SiteIngredient siteIngredient){
        // getting new site ingredients
        SiteIngredientBase siteIngredientBase =
                new EdamIngredientRequest("", "", siteIngredient.getName())
                        .sendRequest();

        // getting or creating new ingredients
        List<Ingredient> ingredients =
                new SiteToEntityIngredientBaseAdapter()
                        .transform(siteIngredientBase);

        // result
        return ingredients
                .stream()
                .filter(ingredient -> siteIngredient.getOriginalId().equals(ingredient.getOriginalId()))
                .collect(Collectors.toList()).get(0);
    }

    private Cuisine createCuisine(String cuisine){
        Cuisine cuisineEntity = new Cuisine(cuisine);
        return cuisineEntity;
    }
    private Dish createDish(String dish){
        return null;
    }
    private Meal createMeal(String meal){
        return null;
    }
    private HealthDietLabel createHealthDietLabel(String hdLabel){
        return null;
    }
    private HdLabelType createHDLabelType(String hdLabelType){
        return null;
    }
    private IngredientsToRecipes createIngredientToRecipe(Ingredient ingredient, Recipe recipe){
        return null;
    }

    private Recipe createRecipe(SiteRecipe siteRecipe) {
        Recipe recipe;
        // getting cuisine
        Cuisine cuisine = SiteToEntityAdapter.findOrCreate(
                Cuisine.class,
                siteRecipe.getCuisines().get(0),
                new CuisinesRepositoryImplementation(),
                Cuisine::getType,
                this::createCuisine,
                siteRecipe.getCuisines().get(0)
        );

        // getting dish
        Dish dish = SiteToEntityAdapter.findOrCreate(
                Dish.class,
                siteRecipe.getDishes().get(0),
                new DishesRepositoryImplementation(),
                Dish::getType,
                this::createDish,
                siteRecipe.getDishes().get(0)
        );

        // getting meal
        Meal meal = SiteToEntityAdapter.findOrCreate(
                Meal.class,
                siteRecipe.getMeals().get(0),
                new MealsRepositoryImplementation(),
                Meal::getType,
                this::createMeal,
                siteRecipe.getMeals().get(0)
        );

        // getting health diet labels
        Set<HealthDietLabel> healthDietLabels = new HashSet<>();
        for (String label : siteRecipe.getHealths()){
            healthDietLabels.add(SiteToEntityAdapter.findOrCreate(
                    HealthDietLabel.class,
                    label,
                    new HealthDietLabelRepositoryImplementation(),
                    HealthDietLabel::getLabel,
                    this::createHealthDietLabel,
                    label
            ));
        }

        // getting ingredients
        Set<Ingredient> ingredients = new HashSet<>();
        for(SiteIngredient siteIngredient : siteRecipe.getSiteIngredients()){
            ingredients.add(SiteToEntityAdapter.findOrCreate(
                    Ingredient.class,
                    siteIngredient.getOriginalId(),
                    new IngredientsRepositoryImplementation(),
                    Ingredient::getOriginalId,
                    this::createIngredient,
                    siteIngredient
            ));
        }

        // creating recipe

        // result
        return null;
    }

    @Override
    public Recipe transform(SiteRecipe siteRecipe) {
        return SiteToEntityAdapter.findOrCreate(
                Recipe.class,
                siteRecipe.getOriginalId(),
                new RecipesRepositoryImplementation(),
                Recipe::getOriginalId,
                this::createRecipe,
                siteRecipe);
    }
}
