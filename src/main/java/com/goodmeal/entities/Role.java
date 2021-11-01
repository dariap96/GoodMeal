package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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

    // ------ CAUSES ERROR ------ щас должно работать
//    @ManyToMany(mappedBy = "roleSet")
//    private Set<User> userSet = new HashSet<>();

     //public Set<User> getUserSet() { return userSet; }

    //public void setUserSet(Set<User> userSet) { this.userSet = userSet; }

    public Role(String role, Set<User> userSet) {
        this.role = role;
        //this.userSet = userSet;
    }
    public Role(){};
}
