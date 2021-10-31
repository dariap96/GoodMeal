package com.goodmeal.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table (name = "Ingridients_Recipes", schema = "goodmeal")
public class IngridientsToRecipes {

    @EmbeddedId
    IngridientsToRecipesKey id;

    @ManyToOne
    @MapsId("ingridientId")
    //@JoinColumn(name = "ingridient_id")
    Ingridient ingridient;

    @ManyToOne
    @MapsId("recipeId")
    //@JoinColumn(name = "recipe_id")
    Recipe recipe;

    @Column(name = "quantity")
    private float quantity;

    @Column(name = "measure")
    private String measure;

    public IngridientsToRecipes(Ingridient ingridient, Recipe recipe, float quantity, String measure) {
        //this.id = new IngridientsToRecipesKey(ingridient.getId(),recipe.getId());
        this.ingridient = ingridient;
        this.recipe = recipe;
        this.quantity = quantity;
        this.measure = measure;
    }


    public IngridientsToRecipes() {

    }

    public IngridientsToRecipesKey getId() {
        return id;
    }

    public void setId(IngridientsToRecipesKey id) {
        this.id = id;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Ingridient getIngridient() {
        return ingridient;
    }

    public void setIngridient(Ingridient ingridient) {
        this.ingridient = ingridient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public float getquantity() {
        return quantity;
    }

    public void setquantity(float quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        IngridientsToRecipes that = (IngridientsToRecipes) o;
        return Objects.equals(recipe, that.recipe) &&
                Objects.equals(ingridient, that.ingridient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipe, ingridient);
    }
}
