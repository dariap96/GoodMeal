package com.srcsite.siteDataBase.siteIngredientDataBase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Hint {
    @JsonProperty("food")
    private Food food;
    @JsonProperty("measures")
    private List<Measure> measures;
}
