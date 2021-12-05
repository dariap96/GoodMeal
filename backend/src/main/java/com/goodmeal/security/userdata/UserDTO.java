package com.goodmeal.security.userdata;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class UserDTO {

    private String name;
    private String surname;
    private String login;
    private String email;
    private Date bday;
    private Set<String> roles;

    UserDTO(String name, String surname, String login, String email, Date bday, Set<String> roles) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.email = email;
        this.bday = bday;
        this.roles = roles;
    }

}
