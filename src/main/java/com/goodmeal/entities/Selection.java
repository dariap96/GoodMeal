package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;



@JsonApiResource(type = "selection")
@Entity
@Table(name = "Selections")

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
}
