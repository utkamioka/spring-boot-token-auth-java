package com.example.kamioka.api1;

import jersey.repackaged.com.google.common.collect.ImmutableList;
import jersey.repackaged.com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Books {
    @RequestMapping(path = "/api1/books")
    public Map get() {
        return ImmutableMap.builder()
                .put("buildBy", "spring")
                .put("books", ImmutableList.of("a", "b", "c")).build();
    }
}
