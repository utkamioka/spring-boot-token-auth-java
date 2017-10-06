package com.example.kamioka;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Hello {
    @RequestMapping(path = "/hello")
    public Map get() {
        return ImmutableMap.of("msg", "Hello, Spring-boot");
    }

    @RequestMapping(path = "/hello/{name}")
    public Map get(@PathVariable("name") String name) {
        return ImmutableMap.of("msg", "Hello, " + name);
    }
}
