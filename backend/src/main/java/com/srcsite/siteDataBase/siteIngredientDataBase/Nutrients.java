package com.srcsite.siteDataBase.siteIngredientDataBase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Nutrients {
    @JsonProperty("ENERC_KCAL")
    private float energy;
    @JsonProperty("PROCNT")
    private float protein;
    @JsonProperty("FAT")
    private float fat;
    @JsonProperty("CHOCDF")
    private float carbs;
    @JsonProperty("FIBTG")
    private float fiber;
}
