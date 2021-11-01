package com.srcsite.site_database_getter.site_ingredient_base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
class Food {
    @JsonProperty("foodId")
    public String foodId;
    @JsonProperty("label")
    public String name;
    @JsonProperty("nutrients")
    public Nutrients nutrients;
    @JsonProperty("category")
    public String category;
    @JsonProperty("categoryLabel")
    public String categoryLabel;
    @JsonProperty("image")
    public String imageURI;
}
