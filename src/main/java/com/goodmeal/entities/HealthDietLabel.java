package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonApiResource(type = "healthDietLabel")
@Entity
@Table(name = "Health_Diet_Label")
public class HealthDietLabel {
    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String label;

    @ManyToOne
    private HdLabelType hdLabelType;

    @ManyToMany(mappedBy = "healthDietLabel")
    private Set<Recipe> recipesSet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public HdLabelType getHdLabelType() {
        return hdLabelType;
    }

    public void setHdLabelType(HdLabelType hdLabelType) {
        this.hdLabelType = hdLabelType;
    }

    public Set<Recipe> getRecipesSet() {
        return recipesSet;
    }

    public void setRecipesSet(Set<Recipe> recipesSet) {
        this.recipesSet = recipesSet;
    }

    public HealthDietLabel(Long id, String label, HdLabelType hdLabelType, Set<Recipe> recipesSet) {
        this.id = id;
        this.label = label;
        this.hdLabelType = hdLabelType;
        this.recipesSet = recipesSet;
    }

    public HealthDietLabel() {
    }
}
