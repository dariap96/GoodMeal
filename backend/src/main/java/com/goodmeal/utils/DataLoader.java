package com.goodmeal.utils;

import com.goodmeal.adapters.impl.SiteToEntityRecipeBaseAdapter;
import com.srcsite.edamrequest.impl.EdamIngredientRequest;
import com.srcsite.edamrequest.impl.EdamRecipeRequest;
import com.srcsite.siteDataBase.siteIngredientDataBase.SiteIngredientBase;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipeBase;

public interface DataLoader {
    int SLEEP_MS = 60_000;
    int UNSET_COUNT = -1;

    static int loadRecipes(
            SiteToEntityRecipeBaseAdapter recipeBaseAdapter,
            String query,
            String meal,
            String dish,
            String cuisine,
            int recipeCount,
            int allCount
    ) {
        int j = recipeCount;
        try {
            if (allCount <= UNSET_COUNT) {
                SiteRecipeBase recipeBase =
                        new EdamRecipeRequest(query, meal, dish, cuisine).sendRequest();
                allCount = recipeBase.getCount();
            }
            j += 1;
            for(; j < Math.sqrt(allCount); j++){
                SiteRecipeBase recipeBase =
                        new EdamRecipeRequest(query, meal, dish, cuisine).sendRequest();
                recipeBaseAdapter.transform(recipeBase);
            }
            return allCount;

        } catch (Exception exception) {
            exception.printStackTrace(System.out);
            exception.printStackTrace(System.out);
            try
            {
                //API cool down
                Thread.sleep(SLEEP_MS);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            return loadRecipes(recipeBaseAdapter, query, meal, dish, cuisine, j, allCount);
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
