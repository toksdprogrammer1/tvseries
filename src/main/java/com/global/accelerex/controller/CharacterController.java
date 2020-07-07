package com.global.accelerex.controller;


import com.global.accelerex.dto.CharacterDTO;
import com.global.accelerex.dto.response.CharacterResponse;
import com.global.accelerex.dto.response.EpisodeResponse;
import com.global.accelerex.service.CharacterService;
import com.global.accelerex.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @Autowired
    private EpisodeService episodeService;

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CharacterResponse addCharacter(@Validated @RequestBody CharacterDTO characterDTO){
        return characterService.addCharacter(characterDTO);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/characters")
    public List<CharacterResponse> getAllCharacters(@RequestParam(defaultValue = "gender,ascending") String[] sort){
        return characterService.getAllCharacters(sort);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{characterId}/episodes")
    public List<EpisodeResponse> getCharacterEpisodes(@PathVariable Long characterId){
        return episodeService.getCharacterEpisodes(characterId);
    }
}
