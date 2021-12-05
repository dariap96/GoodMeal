package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;
import io.crnk.core.resource.annotations.SerializeType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonApiResource(type = "selection")
@Entity
@Table(name = "Selections", schema = "goodmeal")
@Getter
@Setter
public class Selection {

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selection_id")
    private Long id;

    @Column(name="selection_name")
    private String selectionName;

    @ManyToOne
    @JoinColumn(name = "id_Users")
    //@JsonApiRelation(idField = "id", serialize = SerializeType.ONLY_ID)
    private User user;

    //@JsonApiRelation(serialize = SerializeType.LAZY)
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(name = "Ingredients_Selections", schema = "goodmeal",
            joinColumns = @JoinColumn(name = "selection_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredientSet = new HashSet<>();

    //@JsonApiRelation(serialize = SerializeType.LAZY)
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(name = "Recipes_Selections", schema = "goodmeal",
            joinColumns = @JoinColumn(name = "selection_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> recipeSet = new HashSet<>();

    public Selection(String selectionName, User user, Set<Ingredient> ingredientSet, Set<Recipe> recipeSet) {
        this.selectionName = selectionName;
        this.user = user;
        this.ingredientSet = ingredientSet;
        this.recipeSet = recipeSet;
    }

     public Selection(String selectionName) {
        this.selectionName = selectionName;
     }

    public Selection() {}
}
