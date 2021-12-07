package com.srcsite.edamrequest.impl;

import com.srcsite.edamrequest.APIKeys;
import com.srcsite.edamrequest.EdamRequest;

import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipeBase;

public class EdamRecipeRequest extends EdamRequest {

    public static final int MIN_FROM = 0;
    public static final int MAX_FROM = 7000;
    public static final int MIN_TO = 0;
    public static final int MAX_TO = 7000;
    public static final int DEFAULT_FROM = 0;
    public static final int DEFAULT_TO = 100;
    public static final String DEFAULT_Q = "*";
    private static final String BASE = "https://api.edamam.com/api/recipes/v2?type=public";

    private final String query;

    public EdamRecipeRequest(String query){
        super(APIKeys.APP_RECIPE_ID, APIKeys.APP_RECIPE_KEY, BASE);

        if(query.isBlank()){
            query = DEFAULT_Q;
        }
        this.query = query;
    }

    @Override
    public String getURI() {
        return getBase()
                + SEP
                + "q=" + query
                + SEP
                + "app_id=" + getAppId()
                + SEP
                + "app_key=" + getAppKey()
                + SEP
                + "random=true";
    }

    public SiteRecipeBase sendRequest() {
        return super.sendRequest(SiteRecipeBase.class);
    }
}
