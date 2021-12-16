package com.srcsite.edamrequest.impl;

import com.goodmeal.entities.APIRecipeKey;
import com.goodmeal.services.impl.APIRecipeKeysService;
import com.srcsite.edamrequest.EdamRequest;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipeBase;
import org.springframework.beans.factory.annotation.Autowired;

public class EdamRecipeRequest extends EdamRequest {

    public static final String DEFAULT_Q = "*";
    private static final String BASE = "https://api.edamam.com/api/recipes/v2?type=public";

    @Autowired
    APIRecipeKeysService apiRecipeKeysService;
    
    private String query;
    private String meal;
    private String dish;
    private String cuisine;

    public EdamRecipeRequest(APIRecipeKey apiRecipeKey){
        super(apiRecipeKey.getBase(), apiRecipeKey.getAppId(), apiRecipeKey.getAppKey());
    }

    @Override
    public String getURI() {
        StringBuilder request = new StringBuilder(
                getBase()
                        + SEP
                        + "q=" + query
                        + SEP
                        + "app_id=" + getAppId()
                        + SEP
                        + "app_key=" + getAppKey()

        );

        if (meal != null && !meal.isBlank()) {
            request.append(SEP)
                    .append("meal=")
                    .append(meal);
        }
        if (dish != null && !dish.isBlank()) {
            request.append(SEP)
                    .append("dish=")
                    .append(dish);
        }
        if (cuisine != null && !cuisine.isBlank()) {
            request.append(SEP)
                    .append("cuisine=")
                    .append(cuisine);
        }

        return request.append(SEP).append("random=true").toString();
    }


    public SiteRecipeBase sendRequest(
            String query,
            String meal,
            String dish,
            String cuisine
    ) {
        if(query.isBlank()){
            query = DEFAULT_Q;
        }
        this.query = query;
        this.meal = meal;
        this.dish = dish;
        this.cuisine = cuisine;

        return super.sendRequest(SiteRecipeBase.class);
    }
}
