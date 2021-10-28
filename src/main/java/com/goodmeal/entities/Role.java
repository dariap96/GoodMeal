package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;



@JsonApiResource(type = "role")
@Entity
@Table(name = "Roles")
public class Role {

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String role;

    @ManyToMany(mappedBy = "role")
    private Set<User> userSet = new HashSet<>();

}
