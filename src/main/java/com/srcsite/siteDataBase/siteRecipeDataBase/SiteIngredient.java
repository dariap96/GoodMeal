package com.srcsite.siteDataBase.siteRecipeDataBase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SiteIngredient {
    @JsonProperty("quantity")
    private double quantity;
    @JsonProperty("measure")
    private String measure;
    @JsonProperty("food")
    private String name;
    @JsonProperty("weight")
    private double weight;
    @JsonProperty("foodId")
    private String original_id;
    @JsonProperty("image")
    private String imageURI;
}
