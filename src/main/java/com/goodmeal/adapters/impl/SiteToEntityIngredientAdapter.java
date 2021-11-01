package com.goodmeal.adapters.impl;

import com.goodmeal.adapters.SiteToEntityAdapter;
import com.goodmeal.entities.Ingredient;
import com.srcsite.siteDataBase.siteIngredientDataBase.Food;
import com.srcsite.siteDataBase.siteIngredientDataBase.Nutrients;

public class SiteToEntityIngredientAdapter implements SiteToEntityAdapter<Food, Ingredient> {
    @Override
    public Ingredient transform(Food food) {
        Nutrients nutrients = food.getNutrients();
        return new Ingredient(
                food.getName(),
                nutrients.getEnergy(),
                nutrients.getFat(),
                nutrients.getProtein(),
                nutrients.getCarbs(),
                nutrients.getFiber(),
                food.getImageURI(),
                food.getFoodId()
        );
    }
}
