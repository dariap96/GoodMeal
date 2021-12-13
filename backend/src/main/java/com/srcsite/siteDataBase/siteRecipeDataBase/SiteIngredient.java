package com.srcsite.siteDataBase.siteRecipeDataBase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SiteIngredient {

    private float quantity;

    private String measure;

    @JsonProperty("food")
    private String name;

    private double weight;

    @JsonProperty("foodId")
    private String originalId;

    @JsonProperty("image")
    private String imageURI;
}
