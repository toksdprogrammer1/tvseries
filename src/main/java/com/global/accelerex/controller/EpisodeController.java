package com.global.accelerex.controller;

import com.global.accelerex.dto.CommentDTO;
import com.global.accelerex.dto.EpisodeDTO;
import com.global.accelerex.dto.response.CommentResponse;
import com.global.accelerex.dto.response.EpisodeResponse;
import com.global.accelerex.service.CommentService;
import com.global.accelerex.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/episodes")
public class EpisodeController {

    @Autowired
    private EpisodeService episodeService;

    @Autowired
    private CommentService commentService;

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EpisodeResponse addEpisode(@Validated @RequestBody EpisodeDTO episodeDTO){
        return episodeService.addEpisode(episodeDTO);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("{episodeId}/comments")
    public CommentResponse addCommentToEpisode(@Validated @RequestBody CommentDTO commentDTO, @PathVariable Long episodeId){
        return commentService.addComment(episodeId, commentDTO);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{episodeId}/comments")
    public List<CommentResponse> getEpisodeComments(@PathVariable Long episodeId){
        return commentService.getEpisodeComments(episodeId);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<EpisodeResponse> getAllEpisodes(){
        return episodeService.getAllEpisodes();
    }

}
