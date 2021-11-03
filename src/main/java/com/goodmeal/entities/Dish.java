package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@JsonApiResource(type = "dish")
@Entity
@Table(name = "Dishes", schema = "goodmeal")
@Getter
public class Dish {

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @OneToMany(mappedBy = "dish")
    private Set<Recipe> recipes = new HashSet<>();

    public Dish(String type) {
        this.type = type;
    }

    public Dish(){};

}
