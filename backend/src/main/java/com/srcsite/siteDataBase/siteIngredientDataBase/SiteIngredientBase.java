package com.srcsite.siteDataBase.siteIngredientDataBase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class SiteIngredientBase{
    @JsonProperty("text")
    private String text;
    @JsonProperty("hints")
    private List<Hint> hints;
}

