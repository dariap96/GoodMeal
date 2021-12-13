package com.srcsite.siteDataBase.siteRecipeDataBase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class SiteRecipeBase {

    private int count;

    private int from;

    private int to;

    private List<Hit> hits;

    public List<SiteRecipe> getRecipes() {
        return hits.stream().map(Hit::getRecipe).collect(Collectors.toList());
    }
}

@Getter
class Hit {

    private SiteRecipe recipe;
}