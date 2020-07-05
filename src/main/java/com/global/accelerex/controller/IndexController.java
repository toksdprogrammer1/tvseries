package com.global.accelerex.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String sayHello() {
        return "Hello and Welcome to the Global Accelerex TVseries application. Visit https://global-accelerex-tvseries.herokuapp.com/swagger-ui.html for the documentation";
    }

}
