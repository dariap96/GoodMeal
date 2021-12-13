package com.srcsite.siteDataBase.siteIngredientDataBase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Measure {

    private String label;

    private double weight;
}
