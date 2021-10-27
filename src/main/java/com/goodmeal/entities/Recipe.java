package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@JsonApiResource(type = "recipe")
@Entity
@Table(name = "Recipes")
public class Recipe {

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "cook_time")
    private Integer cookTime; //in minutes

    @Column(name = "prep_time")
    private Integer prepTime; //in minutes

    @Column
    private String image;

    @Column
    private String originalId;

    @ManyToOne
    private Cuisine cuisine;

    @ManyToOne
    private Meal meal;

    @ManyToOne
    private Dish dish;

    @OneToMany(mappedBy = "recipe")
    private Set<IngridientsToRecipes> ingridientsSet = new HashSet<>();

    public Set<IngridientsToRecipes> getIngridientsSet() {
        return ingridientsSet;
    }

    public void setIngridientsSet(Set<IngridientsToRecipes> ingridientsSet) {
        this.ingridientsSet = ingridientsSet;
    }


    public Recipe() {}

    public Recipe(Long id, String name, Integer cookTime, Integer prepTime, String image, Cuisine cuisine, Meal meal, Dish dish, Set<IngridientsToRecipes> ingridientsSet, String originalId) {
        this.id = id;
        this.name = name;
        this.cookTime = cookTime;
        this.prepTime = prepTime;
        this.image = image;
        this.cuisine = cuisine;
        this.meal = meal;
        this.dish = dish;
        this.ingridientsSet = ingridientsSet;
        this.originalId = originalId;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public void setId(Long id) { this.id = id; }

    public Long getId() {
        return id;
    }


}
