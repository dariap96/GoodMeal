package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonApiResource(type = "meal")
@Table(name = "Meals", schema="goodmeal")
public class Meal {

    public static final String DEFAULT_NAME = "No meal type";

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @OneToMany(mappedBy = "meal")
    private Set<Recipe> recipes;

    public Meal(String type) {
        this.type = type;
    }

    public Meal() {};
}
