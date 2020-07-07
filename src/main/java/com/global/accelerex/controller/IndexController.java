package com.global.accelerex.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.global.accelerex.dto.CharacterDTO;
import com.global.accelerex.dto.CommentDTO;
import com.global.accelerex.dto.EpisodeDTO;
import com.global.accelerex.dto.response.CharacterResponse;
import com.global.accelerex.dto.response.CommentResponse;
import com.global.accelerex.dto.response.EpisodeResponse;
import com.global.accelerex.service.CharacterService;
import com.global.accelerex.service.CommentService;
import com.global.accelerex.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String sayHello() {
        return "Hello and Welcome to the Global Accelerex TVseries application. Visit https://global-accelerex-tvseries.herokuapp.com/swagger-ui.html for the documentation";
    }

}
