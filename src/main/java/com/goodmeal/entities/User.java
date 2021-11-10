package com.goodmeal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonApiResource(type = "user")
@Entity
@Table(name = "Users", schema = "goodmeal")
@Getter
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

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "Users_Roles", schema = "goodmeal",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roleSet = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Selection> selectionSet = new HashSet<>();

    public Set<Selection> getSelectionSet() { return selectionSet; }

    public void setSelectionSet(Set<Selection> selectionSet) { this.selectionSet = selectionSet; }

    public Set<Role> getRole() { return roleSet; }

    public void setRole(Set<Role> roleSet) { this.roleSet = roleSet; }

    public String getLogin() { return login; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

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

    public User() {}
}