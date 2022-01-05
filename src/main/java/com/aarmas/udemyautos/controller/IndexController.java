package com.aarmas.udemyautos.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping()
    public String get() {
        return "Fuck you, get!";
    }

    @GetMapping("/request") /* http://localhost:8080/request?user=elver&password=galarga */
    public String loginWithRequest(@RequestParam("user") String user, @RequestParam("password") String password) {
        return "User: " + user + ", Password: " + password;
    }

    @GetMapping("/login/{user}/password/{password}") /* http://localhost:8080/login/elver/password/galarga */
    public String loginWithPath(@PathVariable("user") String user, @PathVariable("password") String password) {
        return "User: " + user + ", Password: " + password;
    }
}
