package com.goodmeal.adapters.impl;

import com.goodmeal.adapters.SiteToEntityAdapter;
import com.goodmeal.entities.Ingredient;
import com.goodmeal.services.impl.IngredientsService;
import com.srcsite.siteDataBase.siteIngredientDataBase.Food;
import com.srcsite.siteDataBase.siteIngredientDataBase.Nutrients;

public class SiteToEntityIngredientAdapter implements SiteToEntityAdapter<Food, Ingredient> {

    private  final IngredientsService ingredientsService;
    public SiteToEntityIngredientAdapter(IngredientsService ingredientsService){
        this.ingredientsService = ingredientsService;
    }

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

        ingredientsService.getIngredientsRepository().save(ingredient);

        return ingredient;
    }

    @Override
    public Ingredient transform(Food food) {
        return SiteToEntityAdapter.findOrCreate(
                food.getName(),
                ingredientsService.getIngredientsRepository()::getByName,
                this::createIngredient,
                food);
    }
}
