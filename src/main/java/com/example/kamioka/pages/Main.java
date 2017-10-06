package com.example.kamioka.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/pages")
public class Main {
    @RequestMapping(path = "/index")
    public String index() {
        return "index";
    }
}
