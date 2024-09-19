package com.example.helloraef;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/")
    public String helloo() {
        return "Hello raef!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello raef! ci/cd ";
    }
}
