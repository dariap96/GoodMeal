package com.goodmeal.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class IngredientsToRecipesKey implements Serializable {

    @Column //(name = "recipe_id")
    private Long recipeId;

    @Column //(name = "ingredient_id")
    private Long ingredientId;

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, ingredientId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        IngredientsToRecipesKey that = (IngredientsToRecipesKey) o;
        return Objects.equals(recipeId, that.recipeId) &&
                Objects.equals(ingredientId, that.ingredientId);
    }

    public IngredientsToRecipesKey(Long recipeId, Long ingredientId) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
    }

    public IngredientsToRecipesKey() {
    }
}
