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
public class TVSeriesController {

    @Autowired
    private EpisodeService episodeService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public String sayHello() {
        return "Hello and Welcome to the Global Accelerex TVseries application. Visit https://global-accelerex-tvseries.herokuapp.com/swagger-ui.html for the documentation";
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/episodes")
    public EpisodeResponse addEpisode(@Validated @RequestBody EpisodeDTO episodeDTO){
        return episodeService.addEpisode(episodeDTO);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/characters")
    public CharacterResponse addCharacter(@Validated @RequestBody CharacterDTO characterDTO){
        return characterService.addCharacter(characterDTO);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/episodes/{episodeId}/comments")
    public CommentResponse addCommentToEpisode(@Validated @RequestBody CommentDTO commentDTO, @PathVariable Long episodeId){
        return commentService.addComment(episodeId, commentDTO);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/episodes/{episodeId}/comments")
    public List<CommentResponse> getEpisodeComments(@PathVariable Long episodeId){
        return commentService.getEpisodeComments(episodeId);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/episodes")
    public List<EpisodeResponse> getAllEpisodes(){
        return episodeService.getAllEpisodes();
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/comments")
    public List<CommentResponse> getAllComments(){
        return commentService.getAllComments();
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/characters")
    public List<CharacterResponse> getAllCharacters(){
        return characterService.getAllCharacters();
    }
}
