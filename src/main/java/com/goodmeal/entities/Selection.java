package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@JsonApiResource(type = "selection")
@Entity
@Table(name = "Selections", schema = "goodmeal")

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
    @JoinTable(name = "Ingridients_Selections",
            joinColumns = @JoinColumn(name = "selection_id"),
            inverseJoinColumns = @JoinColumn(name = "ingridient_id"))
    private Set<Ingridient> ingridientSet = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "Recipes_Selections",
            joinColumns = @JoinColumn(name = "selection_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> recipeSet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSelectionName() {
        return selectionName;
    }

    public void setSelectionName(String selectionName) {
        this.selectionName = selectionName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Ingridient> getIngridientSet() {
        return ingridientSet;
    }

    public void setIngridientSet(Set<Ingridient> ingridientSet) {
        this.ingridientSet = ingridientSet;
    }

    public Set<Recipe> getRecipeSet() {
        return recipeSet;
    }

    public void setRecipeSet(Set<Recipe> recipeSet) {
        this.recipeSet = recipeSet;
    }

    public Selection(String selectionName, User user, Set<Ingridient> ingridientSet, Set<Recipe> recipeSet) {
        this.selectionName = selectionName;
        this.user = user;
        this.ingridientSet = ingridientSet;
        this.recipeSet = recipeSet;
    }

    public Selection() {
    }
}
