package com.example.kamioka.auth;

import org.springframework.security.core.GrantedAuthority;

public class UserAuthority implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "ROLE_USER";
    }

    @Override
    public String toString() {
        return this.getAuthority();
    }
}
