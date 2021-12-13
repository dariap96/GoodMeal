package com.srcsite.siteDataBase.siteIngredientDataBase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Hint {

    private Food food;

    private List<Measure> measures;
}
