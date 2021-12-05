package com.srcsite.siteDataBase.siteIngredientDataBase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Food {
    @JsonProperty("foodId")
    private String foodId;
    @JsonProperty("label")
    private String name;
    @JsonProperty("nutrients")
    private Nutrients nutrients;
    @JsonProperty("category")
    private String category;
    @JsonProperty("categoryLabel")
    private String categoryLabel;
    @JsonProperty("image")
    private String imageURI;

    public String getName() {
        return name.toLowerCase();
    }
}
