package com.goodmeal.adapters.impl;

import com.goodmeal.adapters.SiteToEntityAdapter;
import com.goodmeal.entities.Recipe;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipe;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipeBase;

import java.util.LinkedList;
import java.util.List;

public class SiteToEntityRecipeBaseAdapter implements SiteToEntityAdapter<SiteRecipeBase, List<Recipe>> {
    @Override
    public List<Recipe> transform(SiteRecipeBase siteRecipeBase) {
        List<Recipe> recipeBase = new LinkedList<>();
        for (SiteRecipe siteRecipe : siteRecipeBase.getRecipes()){
            recipeBase.add(new SiteToEntityRecipeAdapter().transform(siteRecipe));
        }
        return recipeBase;
    }
}
