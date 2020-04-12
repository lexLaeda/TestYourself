package com.test.yourself.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*",allowedHeaders = "http://localhost:8080")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("gegeg");
        return "HELLO EVGENIIA";
    }
}
