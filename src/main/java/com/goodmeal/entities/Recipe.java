package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@JsonApiResource(type = "recipe")
@Entity
@Table(name = "Recipes", schema = "goodmeal")
@Getter
@Setter
public class Recipe {

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "time")
    private Integer time; //in minutes

    @Column
    private String image;

    @Column
    private String originalId;

    @Column(name = "actions_sequence")
    private String actionsSequence;

    @ManyToOne
    private Cuisine cuisine;

    @ManyToOne
    private Meal meal;

    @ManyToOne
    private Dish dish;

    @ManyToMany
    @JoinTable(name = "Labels_Recipes",
               joinColumns = @JoinColumn(name = "recipe_id"),
               inverseJoinColumns = @JoinColumn(name = "label_id"))
    private Set<HealthDietLabel> labelsSet = new HashSet<>();

    @OneToMany(mappedBy = "recipe")
    private Set<IngredientsToRecipes> ingredientsSet = new HashSet<>();

    @ManyToMany(mappedBy = "recipeSet")
    private Set<Selection> recipeSet = new HashSet<>();

    public Recipe() {}

    public Recipe(
            String name,
            Integer time,
            String image,
            String actionsSequence,
            Cuisine cuisine,
            Meal meal,
            Dish dish,
            String originalId
    ) {
        this.name = name;
        this.time = time;
        this.image = image;
        this.actionsSequence = actionsSequence;
        this.cuisine = cuisine;
        this.meal = meal;
        this.dish = dish;
        this.originalId = originalId;
    }
}
