package com.srcsite.edamrequest.impl;

import com.srcsite.edamrequest.APIKeys;
import com.srcsite.edamrequest.EdamRequest;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipeBase;
import lombok.AllArgsConstructor;

public class EdamRecipeRequest extends EdamRequest {

    public static final String DEFAULT_Q = "*";
    private static final String BASE = "https://api.edamam.com/api/recipes/v2?type=public";

    private final String query;
    private final String meal;
    private final String dish;
    private final String cuisine;

    public EdamRecipeRequest(
            String query,
            String meal,
            String dish,
            String cuisine
    ){
        super(APIKeys.APP_RECIPE_ID, APIKeys.APP_RECIPE_KEY, BASE);

        if(query.isBlank()){
            query = DEFAULT_Q;
        }
        this.query = query;
        this.meal = meal;
        this.dish = dish;
        this.cuisine = cuisine;
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

        if (!meal.isBlank()) {
            request.append(SEP)
                    .append("meal=")
                    .append(meal);
        }
        if (!dish.isBlank()) {
            request.append(SEP)
                    .append("dish=")
                    .append(dish);
        }
        if (!cuisine.isBlank()) {
            request.append(SEP)
                    .append("cuisine=")
                    .append(cuisine);
        }

        return request.append(SEP).append("random=true").toString();
    }

    public SiteRecipeBase sendRequest() {
        return super.sendRequest(SiteRecipeBase.class);
    }
}
