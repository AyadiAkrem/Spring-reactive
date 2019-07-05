package com.itdeep.reactive.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainCotroller {

    @RequestMapping(path = "/")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }
}
