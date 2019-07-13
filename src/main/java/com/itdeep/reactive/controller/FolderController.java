package com.itdeep.reactive.controller;

import com.itdeep.reactive.entities.User;
import com.itdeep.reactive.service.StorageService;
import com.itdeep.reactive.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/upload")
public class FolderController {

    private final StorageService storageService;

    private final UserService userService;

    public FolderController(StorageService storageService, UserService userService) {
        this.storageService = storageService;
        this.userService = userService;
    }

    private static Flux<String> partFluxDescription(Flux<? extends Part> partsFlux) {
        return partsFlux
                .log()
                .map(FolderController::partListDescription);
    }

    private static String partListDescription(Part part) {
        return part instanceof FilePart ? part.name() + ":" + ((FilePart) part).filename() : part.name();
    }

//    private static String partMapDescription(MultiValueMap<String, Part> partsMap) {
//        return partsMap.keySet().stream().sorted()
//                .map(key -> partListDescription(partsMap.get(key)))
//                .collect(Collectors.joining(",", "Map[", "]"));
//    }

    @GetMapping
    public Mono<String> addUser(@ModelAttribute User user, Model model) {
        model.addAttribute("templateFile", "pages/file-upload");
        return Mono.just("index");
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Mono<String> requestBodyFlux(@RequestBody Flux<Part> parts, Model model) {
        Flux<String> stringMono = partFluxDescription(parts);
        model.addAttribute("files", new ReactiveDataDriverContextVariable(stringMono, 1));
        model.addAttribute("templateFile", "pages/files");
        return Mono.just("index");
    }


}
