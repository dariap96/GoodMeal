package com.srcsite.site_database_getter.site_ingredient_base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
class Hint {
    @JsonProperty("food")
    public Food food;
    @JsonProperty("measures")
    public List<Measure> measures;
}
