package com.srcsite.siteDataBase.siteRecipeDataBase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class SiteRecipe {

    public static final String ID_SEPARATOR = "#";
    public static final String IL_SEPARATOR = "\n";

    @JsonProperty("label")
    private String name;

    @JsonProperty("uri")
    private String originalRecipeURI;

    @JsonProperty("shareAs")
    private String siteRecipeURI;

    @JsonProperty("url")
    private String originalRecipeURL;

    @JsonProperty("image")
    private String imageURI;

    @JsonProperty("yield")
    private double portions;

    @JsonProperty("dietLabels")
    private List<String> diets;

    @JsonProperty("healthLabels")
    private List<String> healths;

    @JsonProperty("cautions")
    private List<String> cautions;

    @JsonProperty("ingredientLines")
    private List<String> ingredientLines;

    @JsonProperty("ingredients")
    private List<SiteIngredient> siteIngredients;

    @JsonProperty("calories")
    private double calories;

    @JsonProperty("totalWeight")
    private double totalWeight;

    @JsonProperty("totalTime")
    private double cookTime;

    @JsonProperty("cuisineType")
    private List<String> cuisines;

    @JsonProperty("mealType")
    private List<String> meals;

    @JsonProperty("dishType")
    private List<String> dishes;

    public String getOriginalId(){
        return originalRecipeURI.split(ID_SEPARATOR)[1];
    }
}
