package com.goodmeal.adapters.impl;

import com.goodmeal.adapters.SiteToEntityAdapter;
import com.goodmeal.entities.Ingredient;
import com.goodmeal.repositoriesImplementations.IngredientsRepositoryImplementation;
import com.srcsite.siteDataBase.siteIngredientDataBase.Food;
import com.srcsite.siteDataBase.siteIngredientDataBase.Nutrients;

public class SiteToEntityIngredientAdapter implements SiteToEntityAdapter<Food, Ingredient> {
    private Ingredient createIngredient(Food food) {
        Nutrients nutrients = food.getNutrients();
        Ingredient ingredient =
                new Ingredient(
                        food.getName(),
                        nutrients.getEnergy(),
                        nutrients.getFat(),
                        nutrients.getProtein(),
                        nutrients.getCarbs(),
                        nutrients.getFiber(),
                        food.getImageURI(),
                        food.getFoodId());

        return ingredient;
    }

    @Override
    public Ingredient transform(Food food) {
        return SiteToEntityAdapter.findOrCreate(
                Ingredient.class,
                food.getFoodId(),
                new IngredientsRepositoryImplementation(),
                Ingredient::getOriginalId,
                this::createIngredient,
                food);
    }
}
