package com.goodmeal.adapters.impl;

import com.goodmeal.adapters.SiteToEntityAdapter;

import com.goodmeal.entities.*;

import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipe;

public class SiteToEntityRecipeAdapter implements SiteToEntityAdapter<SiteRecipe, Recipe> {
    private Ingredient createIngredient(){
        return null;
    }

    private Cuisine createCuisine(){
        return null;
    }
    private Dish createDish(){
        return null;
    }
    private HealthDietLabel createHealthDietLabel(){
        return null;
    }
    private HdLabelType createHDLabelType(){
        return null;
    }
    private IngredientsToRecipes createIngredientToRecipe(){
        return null;
    }
    private Meal createMeal(){
        return null;
    }

    @Override
    public Recipe transform(SiteRecipe siteEntity) {
        return null;
    }
}
