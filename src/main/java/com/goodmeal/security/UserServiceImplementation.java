package com.goodmeal.security;

import com.goodmeal.entities.Role;
import com.goodmeal.entities.User;
import com.goodmeal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(userName);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        System.out.println("USER: ");
        System.out.println("login: " + user.getLogin());

        Role r = new Role("USER", null);
        Set<Role> set = new HashSet<>();
        set.add(r);
        user.setRole(set);

        return new UserDetailsImplementation(user);
    }

}
