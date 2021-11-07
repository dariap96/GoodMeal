package com.goodmeal.security;

import com.goodmeal.entities.Role;
import com.goodmeal.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class UserDetailsImplementation implements UserDetails {

    private User user;

    private Boolean isActive;

    private List<GrantedAuthority> grantedAuthorityList;

    public UserDetailsImplementation(User user) {
        this.user = user;
        this.isActive = true;

        List<Role> rolesList = new LinkedList<Role>();
        rolesList.addAll(user.getRole());

        for (Role role : rolesList) {
            System.out.println("----- ROLES: ");
            GrantedAuthority grAuth = new SimpleGrantedAuthority(role.getRole());
            System.out.println(grAuth.getAuthority());
            this.grantedAuthorityList.add(grAuth);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return grantedAuthorityList; }

    @Override
    public String getPassword() { return this.user.getPassword(); }

    @Override
    public String getUsername() { return this.user.getLogin(); }

    @Override
    public boolean isAccountNonExpired() { return this.isActive; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
