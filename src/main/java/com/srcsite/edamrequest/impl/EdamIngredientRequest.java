package com.srcsite.edamrequest.impl;

import com.srcsite.edamrequest.EdamRequest;
import com.srcsite.site_database_getter.site_ingredient_base.SiteIngredientBase;
import com.srcsite.site_database_getter.site_recipe_base.SiteRecipeBase;

public class EdamIngredientRequest extends EdamRequest {
    public static final String DEFAULT_INGREDIENT = "apple";
    private static final String BASE = "https://api.edamam.com/api/food-database/v2/parser?";

    private final String ingredient;

    public EdamIngredientRequest(String appId, String appKey, String ingredient) {
        super(appId, appKey, BASE);
        if(ingredient.isBlank())
            ingredient = DEFAULT_INGREDIENT;

        this.ingredient = ingredient;
    }

    @Override
    protected String getURI() {
        return getBase()
                + SEP
                + "app_id=" + getAppId()
                + SEP
                + "app_key=" + getAppKey()
                + SEP
                + "ingr=" + ingredient;
    }

    public SiteIngredientBase sendRequest() {
        return super.sendRequest(SiteIngredientBase.class);
    }
}
