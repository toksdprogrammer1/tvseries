package com.global.accelerex.controller;

import com.global.accelerex.dto.response.CommentResponse;
import com.global.accelerex.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<CommentResponse> getAllComments(){
        return commentService.getAllComments();
    }

}
