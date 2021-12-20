package com.srcsite.edamrequest.impl;

import com.goodmeal.entities.APIFoodKey;
import com.goodmeal.services.impl.APIFoodKeysService;
import com.srcsite.edamrequest.EdamRequest;
import com.srcsite.siteDataBase.siteIngredientDataBase.SiteIngredientBase;
import org.springframework.beans.factory.annotation.Autowired;

public class EdamIngredientRequest extends EdamRequest {

    public static final String DEFAULT_INGREDIENT = "apple";

    @Autowired
    private APIFoodKeysService apiFoodKeysService;

    private String ingredient;

    public EdamIngredientRequest(APIFoodKey apiFoodKey) {
        super(apiFoodKey.getBase(), apiFoodKey.getAppId(), apiFoodKey.getAppKey());
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


    public SiteIngredientBase sendRequest(String ingredient) {
        if(ingredient.isBlank())
            ingredient = DEFAULT_INGREDIENT;

        this.ingredient = ingredient;
        return super.sendRequest(SiteIngredientBase.class);
    }
}
