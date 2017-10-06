package com.example.kamioka.auth;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class MyPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("MyPreAuthenticatedProcessingFilter.getPreAuthenticatedPrincipal()");
        System.out.println("-----------------------------------------------------------------");
        return "";
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("MyPreAuthenticatedProcessingFilter.getPreAuthenticatedCredentials()");
        String authorization = request.getHeader("Authorization");
        System.out.println("authorization = " + authorization);
        System.out.println("-------------------------------------------------------------------");
        return authorization == null ? "" : authorization;
    }
}
