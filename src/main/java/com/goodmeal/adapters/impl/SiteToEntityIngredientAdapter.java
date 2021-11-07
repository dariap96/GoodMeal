package com.goodmeal.adapters.impl;

import com.goodmeal.adapters.SiteToEntityAdapter;
import com.goodmeal.entities.Ingredient;
import com.goodmeal.repositoriesImplementations.IngredientsRepositoryImplementation;
import com.srcsite.siteDataBase.siteIngredientDataBase.Food;
import com.srcsite.siteDataBase.siteIngredientDataBase.Nutrients;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class SiteToEntityIngredientAdapter implements SiteToEntityAdapter<Food, Ingredient> {
    private final IngredientsRepositoryImplementation ingredientsRepository;

    public SiteToEntityIngredientAdapter(IngredientsRepositoryImplementation repo){
        this.ingredientsRepository = repo;
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

        ingredientsRepository.create(ingredient);

        return ingredient;
    }

    @Override
    public Ingredient transform(Food food) {
        return SiteToEntityAdapter.findOrCreate(
                Ingredient.class,
                food.getFoodId(),
                ingredientsRepository,
                Ingredient::getOriginalId,
                this::createIngredient,
                food);
    }
}
