package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@JsonApiResource(type = "user")
@Entity
@Table(name = "Users")

public class User {

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private Date bday;

    // ------ CAUSES ERROR ------ щас должно работать
    @ManyToMany
  @JoinTable(name = "Users_Roles",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roleSet = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Selection> selectionSet = new HashSet<>();

    public Set<Role> getRole() { return roleSet; }

    public void setRole(Set<Role> roleSet) { this.roleSet = roleSet; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBday() {
        return bday;
    }

    public void setBday(Date bday) {
        this.bday = bday;
    }

    public Set<Selection> getSelectionSet() {
        return selectionSet;
    }

    public void setSelectionSet(Set<Selection> selectionSet) {
        this.selectionSet = selectionSet;
    }

    public User(String login, String password, String name, String surname, String email, Date bday, Set<Role> roleSet, Set<Selection> selectionSet) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.bday = bday;
        this.roleSet = roleSet;
        this.selectionSet = selectionSet;
    }

    public User() {
    }
}
