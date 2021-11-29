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
public class RecipesRatingKey implements Serializable {

    @Column
    private Long recipeId;

    @Column
    private Long userId;

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        RecipesRatingKey that = (RecipesRatingKey) o;
        return Objects.equals(recipeId, that.recipeId) &&
                Objects.equals(userId, that.userId);
    }

    public RecipesRatingKey(Long recipeId, Long userId) {
        this.recipeId = recipeId;
        this.userId = userId;
    }

    public RecipesRatingKey() {
    }
}
