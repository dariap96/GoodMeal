package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@JsonApiResource(type = "ingredient")
@Entity
@Table(name="Ingredients", schema = "goodmeal")
@Getter
public class Ingredient implements Serializable {

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String name;
    
    @Column
    private Float energy;
    
    @Column
    private Float fat;
    
    @Column
    private Float protein;
    
    @Column
    private Float carbs;
    
    @Column
    private Float fiber;

    @Column
    private String image;

    @Column
    private String originalId;

    @OneToMany(mappedBy = "ingredient")
    private Set<IngredientsToRecipes> ingredientsSet = new HashSet<>();

    @ManyToMany(mappedBy = "ingredientSet")

    private Set<Selection> selectionSet = new HashSet<>();

    public Ingredient() {}

    public Ingredient(
            String name,
            Float energy,
            Float fat,
            Float protein,
            Float carbs,
            Float fiber,
            String image,
            String originalId
    ) {
        this.name = name;
        this.energy = energy;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
        this.fiber = fiber;
        this.image = image;
        this.originalId = originalId;
    }
}
