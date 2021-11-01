package com.srcsite.site_database_getter.site_recipe_base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class SiteRecipe {
    @JsonProperty("label")
    private String name;
    @JsonProperty("uri")
    private String uri;
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
    private List<String> actions;
    @JsonProperty("ingredients")
    private List<Ingredient> ingredients;
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
