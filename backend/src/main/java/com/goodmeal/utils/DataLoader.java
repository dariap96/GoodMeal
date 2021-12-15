package com.goodmeal.utils;

import com.goodmeal.adapters.impl.SiteToEntityRecipeBaseAdapter;
import com.srcsite.edamrequest.impl.EdamIngredientRequest;
import com.srcsite.edamrequest.impl.EdamRecipeRequest;
import com.srcsite.siteDataBase.siteIngredientDataBase.SiteIngredientBase;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipeBase;

public interface DataLoader {
    int SLEEP_MS = 60_000;

    static int loadRecipes(
            SiteToEntityRecipeBaseAdapter recipeBaseAdapter,
            String query,
            String meal,
            String dish,
            String cuisine,
            int recipeCount
    ) {
        int j = recipeCount;
        try {
            SiteRecipeBase recipeBase =
                    new EdamRecipeRequest(query, meal, dish, cuisine).sendRequest();
            j += 1;
            for(; j < recipeBase.getCount() / 10; j++){
                recipeBase =
                        new EdamRecipeRequest(query, meal, dish, cuisine).sendRequest();
                recipeBaseAdapter.transform(recipeBase);
            }
            return recipeBase.getCount();

        } catch (Exception exception) {
            try
            {
                //API cool down
                Thread.sleep(SLEEP_MS);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            return loadRecipes(recipeBaseAdapter, query, meal, dish, cuisine, j);
        }
    }

    static SiteIngredientBase loadIngredients(
            String name
    ) {
        try {
            return new EdamIngredientRequest(name).sendRequest();
        } catch (Exception exception) {

            try {
                //API cool down
                Thread.sleep(SLEEP_MS);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            return loadIngredients(name);
        }
    }

}
