package com.goodmeal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;
import io.crnk.core.resource.annotations.SerializeType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@JsonApiResource(type = "role")
@Entity
@Table(name = "Roles", schema = "goodmeal")
@Getter
@Setter
public class Role {

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String role;

    @ManyToMany(mappedBy = "roleSet", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonApiRelation(serialize = SerializeType.ONLY_ID)
    private Set<User> userSet = new HashSet<>();

    public Role(String role, Set<User> userSet) {
        this.role = role;
        this.userSet = userSet;
    }
    public Role(){};
}
