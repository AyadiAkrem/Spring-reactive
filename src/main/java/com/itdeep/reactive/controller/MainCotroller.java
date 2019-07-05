package com.itdeep.reactive.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainCotroller {

    @RequestMapping(path = "/")
    public String index(Model model) {
        model.addAttribute("templateFile", "pages/dashboard");
        return "index";
    }

    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(path = "/logout")
    public String logout() {
        return "logout";
    }
}
