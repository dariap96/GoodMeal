package com.srcsite.siteDataBase.siteIngredientDataBase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Food {

    private String foodId;

    @JsonProperty("label")
    private String name;

    private Nutrients nutrients;

    private String category;

    private String categoryLabel;

    @JsonProperty("image")
    private String imageURI;

    public String getName() {
        return name.toLowerCase();
    }
}
