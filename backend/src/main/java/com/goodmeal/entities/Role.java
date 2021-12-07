package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonApiResource(type = "role")
@Table(name = "Roles", schema = "goodmeal")
public class Role {

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String role;

    @ManyToMany(mappedBy = "roleSet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> userSet = new HashSet<>();

    public Role(String role, Set<User> userSet) {
        this.role = role;
        this.userSet = userSet;
    }

    public Role(){};
}
