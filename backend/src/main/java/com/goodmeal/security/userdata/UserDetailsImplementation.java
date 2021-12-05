package com.goodmeal.security.userdata;

import com.goodmeal.entities.Role;
import com.goodmeal.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toSet;

public class UserDetailsImplementation implements UserDetails {

    private User user;

    private Boolean isActive;

    private List<GrantedAuthority> grantedAuthorityList;

    public UserDetailsImplementation(User user) {
        this.user = user;
        this.isActive = true;

        this.grantedAuthorityList = new LinkedList<>();
        List<String> rolesList = new LinkedList<String>();
        rolesList.addAll(user.getRoleSet().stream().map(Role::getRole).collect(toSet()));

        for (String role : rolesList) {
            System.out.println("----- ROLES: ");
            GrantedAuthority grAuth = roleFormatter(role);
            System.out.println(grAuth.getAuthority());
            this.grantedAuthorityList.add(grAuth);
        }
    }

    private SimpleGrantedAuthority roleFormatter(String role) {
        return new SimpleGrantedAuthority("ROLE_" + role);
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
