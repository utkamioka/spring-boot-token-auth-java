package com.example.kamioka.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MyAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        System.out.println("----------------------------------------------------");
        System.out.println("MyAuthenticationUserDetailsService.loadUserDetails()");
        System.out.println("----------------------------------------------------");

        Object credentials = token.getCredentials();
        if (Objects.isNull(credentials) || Objects.toString(credentials).isEmpty()) {
            System.out.println(">>> UsernameNotFoundException - ユーザが見つかりません");
            throw new UsernameNotFoundException("ユーザが見つかりません");
        }

        if (!credentials.toString().startsWith("Bearer ")) {
            System.out.println(">>> UsernameNotFoundException - 不明な認証スキームです");
            throw new UsernameNotFoundException("不明な認証スキームです");
        }

        String accessToken = credentials.toString().substring("Bearer ".length());

        if (accessToken.equals("xxx")) {
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new UserAuthority());
            return new User("user01", "", authorities);
        }

        if (accessToken.equals("yyy")) {
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new UserAuthority());
            authorities.add(new AdminAuthority());
            return new User("admin01", "", authorities);
        }

        if (accessToken.equals("zzz")) {
            return new User("guest01", "__***__",
                    true,  // user enabled
                    true,  // account non expired
                    true,  // credential non expired
                    true,  // account non locked
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_GUEST")));
        }

        System.out.println(">>> UsernameNotFoundException - ユーザが見つかりません");
        throw new UsernameNotFoundException("ユーザが見つかりません");
    }
}
