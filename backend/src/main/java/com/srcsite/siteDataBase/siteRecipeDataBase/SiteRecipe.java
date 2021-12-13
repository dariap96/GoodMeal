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

    private List<String> cautions;

    private List<String> ingredientLines;

    @JsonProperty("ingredients")
    private List<SiteIngredient> siteIngredients;

    private double calories;

    private double totalWeight;

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
