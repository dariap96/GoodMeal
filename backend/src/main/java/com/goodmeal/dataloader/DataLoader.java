package com.goodmeal.dataloader;

import com.goodmeal.adapters.impl.SiteToEntityRecipeBaseAdapter;
import com.goodmeal.services.impl.APIFoodKeysService;
import com.goodmeal.services.impl.APIRecipeKeysService;
import com.srcsite.siteDataBase.siteIngredientDataBase.SiteIngredientBase;

public interface DataLoader<RecipeRequestData, IngredientRequestData> {
    int SLEEP_MS = 60_000;
    int API_KEYS_ID = 1;

    public  int loadRecipes(
            APIRecipeKeysService apiRecipeKeysService,
            SiteToEntityRecipeBaseAdapter recipeBaseAdapter,
            RecipeRequestData requestData
    );

    public SiteIngredientBase loadIngredients(
            APIFoodKeysService apiFoodKeysService,
            IngredientRequestData requestData
    );
}
