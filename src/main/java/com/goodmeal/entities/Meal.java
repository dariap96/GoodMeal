package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@JsonApiResource(type = "meal")
@Entity
@Table(name = "Meals")

public class Meal {

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column String type;

    @OneToMany(mappedBy = "meal")
    private Set<Recipe> recipes= new HashSet<>();

}
