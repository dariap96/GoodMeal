package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@JsonApiResource(type = "recipe")
@Table(name = "Recipes", schema="goodmeal")
public class Recipe {

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "time_")
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Labels_Recipes",
               schema = "goodmeal",
               joinColumns = @JoinColumn(name = "recipe_id"),
               inverseJoinColumns = @JoinColumn(name = "label_id"))
    private Set<HealthDietLabel> labelsSet;

    @OneToMany(mappedBy = "recipe")
    private Set<IngredientsToRecipes> ingredientsSet;

    @ManyToMany(mappedBy = "recipeSet", fetch = FetchType.LAZY)
    private Set<Selection> selectionSet;

    @OneToMany(mappedBy = "recipe")
    private Set<RecipesRating> ratingSet;

    public Recipe() {}

    public Recipe(
            String name,
            Integer time,
            String image,
            String actionsSequence,
            Cuisine cuisine,
            Meal meal,
            Dish dish,
            String originalId,
            Set<HealthDietLabel> labels
    ) {
        this.name = name;
        this.time = time;
        this.image = image;
        this.actionsSequence = actionsSequence;
        this.cuisine = cuisine;
        this.meal = meal;
        this.dish = dish;
        this.originalId = originalId;
        this.labelsSet = labels;
    }

    public Double getRating(){
        if(ratingSet.size() == 0){
            return null;
        }

        return ratingSet.stream().collect(Collectors.averagingInt(RecipesRating::getRating));
    }
}
