package com.srcsite.site_database_getter.site_ingredient_base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
class Measure {
    @JsonProperty("label")
    public String label;
    @JsonProperty("weight")
    public double weight;
}
