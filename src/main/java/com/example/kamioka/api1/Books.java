package com.example.kamioka.api1;

import jersey.repackaged.com.google.common.collect.ImmutableList;
import jersey.repackaged.com.google.common.collect.ImmutableMap;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api1")
public class Books {
    @RequestMapping(path = "/books", method = RequestMethod.GET)
    public Map get(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("--------------");
        System.out.println("v1/Books.get()");
        System.out.println("userDetails = " + userDetails);
        System.out.println("userDetails.getUsername() = " + userDetails.getUsername());
        System.out.println("userDetails.getPassword() = " + userDetails.getPassword());
        System.out.println("userDetails.getAuthorities() = " + userDetails.getAuthorities());
        System.out.println("--------------");
        String username = userDetails.getUsername();
        Collection<String> authorities = userDetails.getAuthorities().stream().map(Object::toString).collect(Collectors.toList());
        return ImmutableMap.builder()
                .put("buildBy", "spring")
                .put("method", "GET")
                .put("authorized", ImmutableMap.builder().put("username", username).put("authority", authorities).build())
                .put("books", ImmutableList.of("a", "b", "c")).build();
    }

    @RequestMapping(path = "/books", method = RequestMethod.POST)
    public Map post(@AuthenticationPrincipal UserDetails userDetails) {
        return ImmutableMap.builder()
                .put("buildBy", "spring")
                .put("method", "POST")
                .build();
    }
}
