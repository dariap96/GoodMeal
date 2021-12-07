package com.goodmeal.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table (name = "Ingredients_Recipes", schema = "goodmeal")
public class IngredientsToRecipes {

    @EmbeddedId
    IngredientsToRecipesKey id;

    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    Ingredient ingredient;

    @ManyToOne
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id")
    Recipe recipe;

    @Column(name = "quantity")
    private float quantity;

    @Column(name = "measure")
    private String measure;

    public IngredientsToRecipes(Ingredient ingredient, Recipe recipe, float quantity, String measure) {
        this.id = new IngredientsToRecipesKey(ingredient.getId(),recipe.getId());
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.quantity = quantity;
        this.measure = measure;
    }

    public IngredientsToRecipes() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        IngredientsToRecipes that = (IngredientsToRecipes) o;
        return Objects.equals(recipe, that.recipe) &&
                Objects.equals(ingredient, that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipe, ingredient);
    }
}
