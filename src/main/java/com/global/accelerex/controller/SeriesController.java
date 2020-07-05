package com.global.accelerex.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tvseries")
public class SeriesController {

    @ResponseBody
    @GetMapping
    String home() {
        return "Hello World!";
    }

}
