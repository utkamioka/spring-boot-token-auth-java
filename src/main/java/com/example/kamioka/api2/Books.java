package com.example.kamioka.api2;

import jersey.repackaged.com.google.common.collect.ImmutableList;
import jersey.repackaged.com.google.common.collect.ImmutableMap;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/books")
public class Books {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map get() {
        System.out.println("--------------");
        System.out.println("v2/Books.get()");
        System.out.println("--------------");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        User user = (User) securityContext.getAuthentication().getPrincipal();
        System.out.println("user = " + user);
        System.out.println("user.getUsername() = " + user.getUsername());
        System.out.println("user.getPassword() = " + user.getPassword());
        System.out.println("user.getAuthorities() = " + user.getAuthorities());
        String username = user.getUsername();
        Collection<String> authorities = user.getAuthorities().stream().map(Object::toString).collect(Collectors.toList());
        return ImmutableMap.builder()
                .put("buildBy", "jersey")
                .put("method", "GET")
                .put("authorized", ImmutableMap.builder().put("username", username).put("authority", authorities).build())
                .put("books", ImmutableList.of("a", "b", "c")).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map post() {
        return ImmutableMap.builder()
                .put("buildBy", "jersey")
                .put("method", "POST")
                .build();
    }
}
