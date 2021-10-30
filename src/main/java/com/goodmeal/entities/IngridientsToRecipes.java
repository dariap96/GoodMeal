package com.goodmeal.entities;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
class IngridientsToRecipesKey implements Serializable {

    @Column(name = "recipe_id")
    private Long recipeId;

    @Column(name = "ingridient_id")
    private Long ingridientId;

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Long getIngridient_id() {
        return ingridientId;
    }

    public void setIngridientId(Long ingridientId) {
        this.ingridientId = ingridientId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}

@Entity
@Table(name = "Ingridients_Recipes")
public class IngridientsToRecipes {

    @EmbeddedId
    IngridientsToRecipesKey id;

    @ManyToOne
    @MapsId("ingridientId")
    @JoinColumn(name = "ingridient_id")
    Ingridient ingridient;

    @ManyToOne
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id")
    Recipe recipe;

    private float quantity;

    private String measure;

    public IngridientsToRecipes(IngridientsToRecipesKey id, Ingridient ingridient, Recipe recipe, float quantity, String measure) {
        this.id = id;
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
}
