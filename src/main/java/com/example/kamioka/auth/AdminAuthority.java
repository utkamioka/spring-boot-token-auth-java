package com.example.kamioka.auth;

import org.springframework.security.core.GrantedAuthority;

public class AdminAuthority implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "ROLE_ADMIN";
    }

    @Override
    public String toString() {
        return this.getAuthority();
    }
}
