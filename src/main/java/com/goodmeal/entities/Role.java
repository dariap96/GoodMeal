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

    // ------ CAUSES ERROR ------
//    @ManyToMany(mappedBy = "roleSet")
//    private Set<User> userSet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public Set<User> getUserSet() { return userSet; }

//    public void setUserSet(Set<User> userSet) { this.userSet = userSet; }

    public Role(Long id, String role, Set<User> userSet) {
        this.id = id;
        this.role = role;
        //this.userSet = userSet;
    }
    public Role(){};
}
