package com.srcsite.edamrequest.impl;

import com.srcsite.edamrequest.EdamRequest;

import com.srcsite.site_database_getter.site_recipe_base.SiteRecipeBase;

public class EdamRecipeRequest extends EdamRequest {
    public static final int MIN_FROM = 0;
    public static final int MAX_FROM = 7000;
    public static final int MIN_TO = 0;
    public static final int MAX_TO = 7000;
    public static final int DEFAULT_FROM = 0;
    public static final int DEFAULT_TO = 100;
    public static final String DEFAULT_Q = "*";
    private static final String BASE = "https://api.edamam.com/search?";

    private final String query;
    private final int from;
    private final int to;


    public EdamRecipeRequest(String appId, String appKey, String query, int from, int to){
        super(appId, appKey, BASE);

        if(from > to ||
                from > MAX_FROM ||
                from < MIN_FROM ||
                to > MAX_TO ||
                to < MIN_TO
        ){
            from = DEFAULT_FROM;
            to = DEFAULT_TO;
        }

        if(query.isBlank()){
            query = DEFAULT_Q;
        }

        this.query = query;
        this.from = from;
        this.to = to;
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
                + "from=" + String.valueOf(from)
                + SEP
                + "to=" + String.valueOf(to);
    }

    public SiteRecipeBase sendRequest() {
        return super.sendRequest(SiteRecipeBase.class);
    }
}
