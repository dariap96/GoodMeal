package com.srcsite.siteDataBase.siteIngredientDataBase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Measure {

    @JsonProperty("label")
    private String label;

    @JsonProperty("weight")
    private double weight;
}
