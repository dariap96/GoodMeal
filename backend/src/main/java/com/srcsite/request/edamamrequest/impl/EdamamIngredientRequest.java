package com.srcsite.request.edamamrequest.impl;

import com.goodmeal.entities.APIFoodKey;
import com.goodmeal.services.impl.APIFoodKeysService;
import com.srcsite.request.edamamrequest.EdamamRequest;
import com.srcsite.siteDataBase.siteIngredientDataBase.SiteIngredientBase;
import org.springframework.beans.factory.annotation.Autowired;

public class EdamamIngredientRequest extends EdamamRequest {

    public static final String DEFAULT_INGREDIENT = "apple";

    @Autowired
    private APIFoodKeysService apiFoodKeysService;

    private String ingredient;

    public EdamamIngredientRequest(APIFoodKey apiFoodKey) {
        super(apiFoodKey.getBase(), apiFoodKey.getAppId(), apiFoodKey.getAppKey());
    }

    @Override
    public String getURI() {
        return getBase()
                + SEP
                + "app_id=" + getAppId()
                + SEP
                + "app_key=" + getAppKey()
                + SEP
                + "ingr=" + ingredient;
    }


    public SiteIngredientBase sendRequest(String ingredient) {
        if(ingredient.isBlank())
            ingredient = DEFAULT_INGREDIENT;

        this.ingredient = ingredient;
        return super.sendRequest(SiteIngredientBase.class);
    }
}
