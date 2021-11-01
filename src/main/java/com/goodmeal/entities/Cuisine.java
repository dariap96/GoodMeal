package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@JsonApiResource(type = "cuisine")
@Entity
@Table(name = "Cuisine", schema = "goodmeal")
@Getter
@Setter
public class Cuisine {

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @OneToMany(mappedBy = "cuisine")
    private Set<Recipe> recipes;

    public Cuisine(String type) {
        this.type = type;
    }
    public Cuisine(){}
}
