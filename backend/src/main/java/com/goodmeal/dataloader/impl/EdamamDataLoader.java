package com.goodmeal.dataloader.impl;

import com.goodmeal.DTOs.UpdateRecipesDTO;
import com.goodmeal.adapters.impl.SiteToEntityRecipeBaseAdapter;
import com.goodmeal.dataloader.DataLoader;
import com.goodmeal.services.impl.APIFoodKeysService;
import com.goodmeal.services.impl.APIRecipeKeysService;
import com.srcsite.request.edamamrequest.impl.EdamamIngredientRequest;
import com.srcsite.request.edamamrequest.impl.EdamamRecipeRequest;
import com.srcsite.siteDataBase.siteIngredientDataBase.SiteIngredientBase;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipeBase;

public class EdamamDataLoader implements DataLoader<UpdateRecipesDTO, String> {

    @Override
    public int loadRecipes(
            APIRecipeKeysService apiRecipeKeysService,
            SiteToEntityRecipeBaseAdapter recipeBaseAdapter,
            UpdateRecipesDTO updateRecipesDTO
    ) {
        EdamamRecipeRequest request =
                new EdamamRecipeRequest(apiRecipeKeysService.findById((long) API_KEYS_ID).get());
        SiteRecipeBase recipeBase =
                request.sendRequest(
                        updateRecipesDTO.getQuery(),
                        updateRecipesDTO.getMeal(),
                        updateRecipesDTO.getDish(),
                        updateRecipesDTO.getCuisine()
                );
        int allCount = recipeBase.getCount();
        for(int j = 0; j < (int)Math.sqrt(allCount); j++){
            try {
                System.out.println("==================================================");
                System.out.println("Request " + j + " of " + (int) Math.sqrt(allCount));
                System.out.println("==================================================");
                recipeBase =
                        request.sendRequest(
                                updateRecipesDTO.getQuery(),
                                updateRecipesDTO.getMeal(),
                                updateRecipesDTO.getDish(),
                                updateRecipesDTO.getCuisine()
                        );
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

    @Override
    public SiteIngredientBase loadIngredients(
            APIFoodKeysService apiFoodKeysService,
            String name
    ) {
        try {
            return new EdamamIngredientRequest(apiFoodKeysService.findById((long) API_KEYS_ID).get())
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
