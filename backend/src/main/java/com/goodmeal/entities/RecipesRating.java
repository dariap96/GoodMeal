package com.goodmeal.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Recipes_Rating", schema = "goodmeal")
@Getter
@Setter
public class RecipesRating {

    @EmbeddedId
    RecipesRatingKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id")
    Recipe recipe;

    @Column(name = "rating")
    private int rating;

    @Column(name = "review")
    private String review;

    public RecipesRating(
            Recipe recipe,
            User user,
            int rating,
            String review
    ) {
        this.id = new RecipesRatingKey(recipe.getId(), user.getId());
        this.recipe = recipe;
        this.user = user;
        this.rating = rating;
        this.review = review;
    }

    public RecipesRating() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        RecipesRating that = (RecipesRating) o;
        return Objects.equals(recipe, that.recipe) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipe, user);
    }
}