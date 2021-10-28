package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@JsonApiResource(type = "ingridient")
@Entity
@Table(name = "Ingrigients")
public class Ingridient {

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
    private Float sodium;

    @Column
    private String image;

    @Column
    private String originalId;

    @OneToMany(mappedBy = "ingridient")
    private Set<IngridientsToRecipes> ingridientsSet = new HashSet<>();

    @ManyToMany(mappedBy = "ingridient")
    private Set<Selection> selectionSet = new HashSet<>();

//    public Set<IngridientsToRecipes> getIngridientsSet() {
//        return ingridientsSet;
//    }
//
//    public void setIngridientsSet(Set<IngridientsToRecipes> ingridientsSet) {
//        this.ingridientsSet = ingridientsSet;
//    }

    public Ingridient() {}
    
    public Ingridient(Long id, String name, Float energy, Float fat, Float protein, Float carbs, Float sodium, String image, String originalId) {
        this.id = id;
        this.name = name;
        this.energy = energy;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
        this.sodium = sodium;
        this.image = image;
        this.originalId = originalId;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getEnergy() {
        return energy;
    }

    public void setEnergy(Float energy) {
        this.energy = energy;
    }

    public Float getFat() {
        return fat;
    }

    public void setFat(Float fat) {
        this.fat = fat;
    }

    public Float getProtein() {
        return protein;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public Float getCarbs() {
        return carbs;
    }

    public void setCarbs(Float carbs) {
        this.carbs = carbs;
    }

    public Float getSodium() {
        return sodium;
    }

    public void setSodium(Float sodium) {
        this.sodium = sodium;
    }

}
