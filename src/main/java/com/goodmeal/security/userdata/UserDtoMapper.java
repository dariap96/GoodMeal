package com.goodmeal.security.userdata;

import com.goodmeal.entities.Role;
import com.goodmeal.entities.User;

import java.util.Date;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class UserDtoMapper {

    public UserDTO toDTO (User user) {

        String name = user.getName();
        String surname = user.getSurname();
        String login = user.getLogin();
        String email = user.getEmail();
        Date bday = user.getBday();
        Set<String> roles = user.getRoleSet().stream().map(Role::getRole).collect(toSet());

        return new UserDTO(name, surname, login, email, bday, roles);
    }
}
