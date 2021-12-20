package com.goodmeal.utils;

import com.goodmeal.adapters.impl.SiteToEntityRecipeBaseAdapter;
import com.goodmeal.services.impl.APIFoodKeysService;
import com.goodmeal.services.impl.APIRecipeKeysService;
import com.srcsite.edamrequest.EdamRequest;
import com.srcsite.edamrequest.impl.EdamIngredientRequest;
import com.srcsite.edamrequest.impl.EdamRecipeRequest;
import com.srcsite.siteDataBase.siteIngredientDataBase.SiteIngredientBase;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipeBase;

public interface DataLoader {
    int SLEEP_MS = 60_000;
    int API_KEYS_ID = 1;

    static int loadRecipes(
            APIRecipeKeysService apiRecipeKeysService,
            SiteToEntityRecipeBaseAdapter recipeBaseAdapter,
            String query,
            String meal,
            String dish,
            String cuisine
    ) {
        EdamRecipeRequest request =
                new EdamRecipeRequest(apiRecipeKeysService.findById((long) API_KEYS_ID).get());
        SiteRecipeBase recipeBase =
                request.sendRequest(query, meal, dish, cuisine);
        int allCount = recipeBase.getCount();
        for(int j = 0; j < (int)Math.sqrt(allCount); j++){
            try {
                System.out.println("==================================================");
                System.out.println("Request " + j + " of " + (int) Math.sqrt(allCount));
                System.out.println("==================================================");
                recipeBase =
                        request.sendRequest(query, meal, dish, cuisine);
                recipeBaseAdapter.transform(recipeBase);
            } catch (Exception exception) {
                exception.printStackTrace(System.out);
                exception.printStackTrace(System.out);
                try {
                    //API cool down
                    Thread.sleep(SLEEP_MS);
                }
                catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                j--;
            }
        }
        return allCount;
    }

    static SiteIngredientBase loadIngredients(
            APIFoodKeysService apiFoodKeysService,
            String name
    ) {
        try {
            return new EdamIngredientRequest(apiFoodKeysService.findById((long) API_KEYS_ID).get())
                    .sendRequest(name);
        } catch (Exception exception) {

            try {
                //API cool down
                Thread.sleep(SLEEP_MS);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            return loadIngredients(apiFoodKeysService, name);
        }
    }
}
