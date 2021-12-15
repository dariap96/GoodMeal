package com.goodmeal.security.userdata;

import com.goodmeal.entities.Role;
import com.goodmeal.entities.User;
import com.goodmeal.repositoriesImplementations.RolesRepositoryImplementation;
import com.goodmeal.repositoriesImplementations.UsersRepositoryImplementation;
import com.goodmeal.services.impl.RolesService;
import com.goodmeal.services.impl.UsersService;
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
    PasswordEncoder passwordEncoder;

    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = usersService.getUserByLogin(login);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        // if you need to add users to database manually uncomment following
        String oldPass = user.getPassword();
        user.setPassword(passwordEncoder.encode(oldPass));

        return new UserDetailsImplementation(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = usersService.getUserById(id);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new UserDetailsImplementation(user);
    }

    public boolean saveUser(User user) {
        User userFromDB = usersService.getUserByLogin(user.getLogin());

        if (userFromDB != null) {
            return false;
        }

        // if you need to add users to database manually comment line below
        //user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = rolesService.getRoleByRole("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(userRole);

        user.setRoleSet(roleSet);
        user.setSelectionSet(null);

        usersService.saveUser(user);
        return true;
    }
}
