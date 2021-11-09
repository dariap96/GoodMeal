package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonApiResource(type = "meal")
@Entity
@Table(name = "Meals", schema="goodmeal")
@Getter
public class Meal {

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @OneToMany(mappedBy = "meal")
    private Set<Recipe> recipes= new HashSet<>();

    public Meal(String type) {
        this.type = type;
    }

    public Meal(){};
}
