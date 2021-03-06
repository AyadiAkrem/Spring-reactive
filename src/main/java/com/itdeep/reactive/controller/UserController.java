package com.itdeep.reactive.controller;

import com.itdeep.reactive.entities.User;
import com.itdeep.reactive.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(path = "")
    public Mono<String> displayUsers(Model model) {
        //Simulate big list of data, streaming it every 2 second delay
        Flux<User> userFlux = userRepository.findAll().delayElements(Duration.ofSeconds(2));
        model.addAttribute("users", new ReactiveDataDriverContextVariable(userFlux, 1));
        model.addAttribute("templateFile", "pages/user");
        return Mono.just("index");
    }

    @GetMapping(path = "/add")
    public Mono<String> addUser(@ModelAttribute User user, Model model) {
        model.addAttribute("templateFile", "pages/userform");
        return Mono.just("index");
    }

    @PostMapping("/add")
    Mono<String> signup(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return addUser(user, model);
        }
        return Mono.just(user)
                .doOnSuccess(u -> u.setPassword(passwordEncoder.encode(u.getPassword())))
                .flatMap(this.userRepository::save)
                .then(displayUsers(model));
    }
}
