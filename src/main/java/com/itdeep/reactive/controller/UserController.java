package com.itdeep.reactive.controller;

import com.itdeep.reactive.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping(path = "/")
    public String home(Model model) {
        List<User> users = new ArrayList<>();

        users.add(new User("akrem", "ayadi"));
        users.add(new User("ons", "dridi"));
        users.add(new User("aisha", "ayadi"));
        users.add(new User("alex", "devalck"));
        //Simulate big list of data, streaming it every 2 second delay
        Flux<User> userFlux = Flux.fromIterable(users).delayElements(Duration.ofSeconds(2));
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(userFlux, 1);

        model.addAttribute("users", reactiveDataDrivenMode);

        model.addAttribute("templateFile", "pages/user");
        return "index";
    }
}
