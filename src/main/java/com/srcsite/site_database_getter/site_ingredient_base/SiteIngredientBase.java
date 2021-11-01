package com.srcsite.site_database_getter.site_ingredient_base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class SiteIngredientBase{
    @JsonProperty("text")
    public String text;
    @JsonProperty("hints")
    public List<Hint> hints;
}

