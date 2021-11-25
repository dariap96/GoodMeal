package com.goodmeal.security.userdata;

import com.goodmeal.entities.Role;
import com.goodmeal.entities.User;
import com.goodmeal.repositoriesImplementations.UsersRepositoryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImplementation implements UserDetailsService{


    @Autowired
    private UsersRepositoryImplementation userRepository;

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

        user.setRoleSet(forceRoleSetup());

        // if you need to add users to database manually uncomment following
        String oldPass = user.getPassword();
        user.setPassword(passwordEncoder.encode(oldPass));

        System.out.println("password: " + user.getPassword());

        return new UserDetailsImplementation(user);
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.getUserByLogin(user.getLogin());

        if (userFromDB != null) {
            return false;
        }

        // if you need to add users to database manually comment line below
        //user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoleSet(null);
        user.setSelectionSet(null);

        userRepository.save(user);
        return true;
    }

    public Set<Role> forceRoleSetup() {
        Role r = new Role("USER", null);
        Set<Role> set = new HashSet<>();
        set.add(r);
        return set;
    }
}
