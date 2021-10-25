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

    @Column
    private String cuisine;

    @Column(name = "cook_time")
    private Integer cookTime; //in minutes

    @Column(name = "prep_time")
    private Integer prepTime; //in minutes

    //private Meal meal;

    //private Dish dish;

    @OneToMany(mappedBy = "recipe")
    private Set<IngridientsToRecipes> ingridientsSet = new HashSet<IngridientsToRecipes>();

    public Set<IngridientsToRecipes> getIngridientsSet() {
        return ingridientsSet;
    }

    public void setIngridientsSet(Set<IngridientsToRecipes> ingridientsSet) {
        this.ingridientsSet = ingridientsSet;
    }


    public Recipe() {}


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
