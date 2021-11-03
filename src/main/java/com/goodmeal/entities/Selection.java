package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@JsonApiResource(type = "selection")
@Entity
@Table(name = "Selections", schema = "goodmeal")
@Getter
public class Selection {

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="selection_name")
    private String selectionName;

    @ManyToOne
    @JoinColumn(name = "id_Users")
    private User user;

    @ManyToMany
    @JoinTable(name = "Ingredients_Selections",
            joinColumns = @JoinColumn(name = "selection_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredientSet = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "Recipes_Selections",
            joinColumns = @JoinColumn(name = "selection_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> recipeSet = new HashSet<>();

    public Selection(String selectionName, User user, Set<Ingredient> ingredientSet, Set<Recipe> recipeSet) {
        this.selectionName = selectionName;
        this.user = user;
        this.ingredientSet = ingredientSet;
        this.recipeSet = recipeSet;
    }

    public Selection() {
    }
}
