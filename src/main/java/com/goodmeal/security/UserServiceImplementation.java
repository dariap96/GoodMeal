package com.goodmeal.security;

import com.goodmeal.entities.Role;
import com.goodmeal.entities.User;
import com.goodmeal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public class UserServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.getUserByLogin(login);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        System.out.println("----- USER: ");
        System.out.println("login: " + user.getLogin());

        Role r = new Role("USER", null);
        Set<Role> set = new HashSet<>();
        set.add(r);
        user.setRole(set);

        String oldPass = user.getPassword();
        user.setPassword(passwordEncoder.encode(oldPass));

        System.out.println("password: " + user.getPassword());

        return new UserDetailsImplementation(user);
    }

}
