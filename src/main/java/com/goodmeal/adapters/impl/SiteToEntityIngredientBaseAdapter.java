package com.goodmeal.adapters.impl;

import com.goodmeal.adapters.SiteToEntityAdapter;
import com.goodmeal.entities.Ingredient;
import com.goodmeal.repositoriesImplementations.IngredientsRepositoryImplementation;
import com.srcsite.siteDataBase.siteIngredientDataBase.Food;
import com.srcsite.siteDataBase.siteIngredientDataBase.Hint;
import com.srcsite.siteDataBase.siteIngredientDataBase.SiteIngredientBase;

import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;

public class SiteToEntityIngredientBaseAdapter implements SiteToEntityAdapter<SiteIngredientBase, List<Ingredient>> {

    private final IngredientsRepositoryImplementation ingredientsRepository;

    public SiteToEntityIngredientBaseAdapter(IngredientsRepositoryImplementation repo){
        this.ingredientsRepository = repo;
    }


    @Override
    public List<Ingredient> transform(SiteIngredientBase siteIngredientBase) {
        List<Ingredient> ingredientBase = new LinkedList<>();
        for (Hint siteIngredient : siteIngredientBase.getHints()) {
            Food food = siteIngredient.getFood();
            ingredientBase.add(new SiteToEntityIngredientAdapter(ingredientsRepository).transform(food));
        }
        return ingredientBase;
    }
}
