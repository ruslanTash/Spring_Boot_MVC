package com.example.weblibrary.security;

import org.springframework.security.core.GrantedAuthority;

public class SecurityGrantedAthorities implements GrantedAuthority {

    private String role;
    public SecurityGrantedAthorities (Role role){
        this.role = role.getRole();
    }
    @Override
    public String getAuthority() {
        return this.role;
    }
}
