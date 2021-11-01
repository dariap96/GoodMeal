package com.srcsite.site_database_getter.site_ingredient_base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
class Nutrients {
    @JsonProperty("ENERC_KCAL")
    public int energy;
    @JsonProperty("PROCNT")
    public double protein;
    @JsonProperty("FAT")
    public double fat;
    @JsonProperty("CHOCDF")
    public double carbs;
    @JsonProperty("FIBTG")
    public double fiber;
}
