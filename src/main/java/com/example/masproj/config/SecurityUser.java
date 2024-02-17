package com.example.masproj.config;

import com.example.masproj.model.RegisteredMember;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class SecurityUser implements UserDetails {

    RegisteredMember registeredMember;

    public SecurityUser(RegisteredMember registeredMember) {
        this.registeredMember = registeredMember;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String roleName = "ROLE_" + registeredMember.getClass().getSimpleName();

        return Arrays.asList(new SimpleGrantedAuthority(roleName));

    }

    @Override
    public String getPassword() {
        return registeredMember.getPassword();
    }

    @Override
    public String getUsername() {
        return registeredMember.getEmail();
    }

    public long getId() {return  registeredMember.getId();}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
