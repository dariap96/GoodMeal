package com.srcsite.siteDataBase.siteRecipeDataBase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class SiteRecipe {
    @JsonProperty("label")
    private String name;
    @JsonProperty("shareAs")
    private String siteRecipeURI;
    @JsonProperty("url")
    private String originalRecipeURI;
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
    private List<String> cuisine;
    @JsonProperty("mealType")
    private List<String> meal;
    @JsonProperty("dishType")
    private List<String> dish;
}
