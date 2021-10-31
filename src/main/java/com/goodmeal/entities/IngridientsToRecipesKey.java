package com.goodmeal.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IngridientsToRecipesKey implements Serializable {

    //@Column(name = "recipe_id")
    private Long recipeId;

    //@Column(name = "ingridient_id")
    private Long ingridientId;

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Long getIngridientId() {
        return ingridientId;
    }

    public void setIngridientId(Long ingridientId) {
        this.ingridientId = ingridientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, ingridientId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        IngridientsToRecipesKey that = (IngridientsToRecipesKey) o;
        return Objects.equals(recipeId, that.recipeId) &&
                Objects.equals(ingridientId, that.ingridientId);
    }

    public IngridientsToRecipesKey(Long recipeId, Long ingridientId) {
        this.recipeId = recipeId;
        this.ingridientId = ingridientId;
    }

    public IngridientsToRecipesKey() {
    }

    ;
}
