package com.global.accelerex.service;

import com.global.accelerex.dto.CharacterDTO;
import com.global.accelerex.dto.response.CharacterResponse;
import com.global.accelerex.exception.BadRequestException;
import com.global.accelerex.model.CharacterModel;
import com.global.accelerex.model.EpisodeModel;
import com.global.accelerex.model.LocationModel;
import com.global.accelerex.repository.CharacterRepository;
import com.global.accelerex.repository.EpisodeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private EpisodeRepository episodeRepository;


    public CharacterResponse addCharacter(CharacterDTO characterDTO){
        CharacterModel characterModel = mapDtoToCharacterModel(characterDTO);
        characterModel = characterRepository.save(characterModel);
        return mapCharacterModelToCharacterResponse(characterModel);
    }

    public List<CharacterResponse> getAllCharacters(){
        List<CharacterModel> characterModelList = characterRepository.findAll();
        List<CharacterResponse> characterResponseList = new ArrayList<>();
        if (characterModelList != null){
            characterResponseList = characterModelList.stream().map(
                    episodeModel -> mapCharacterModelToCharacterResponse(episodeModel)
            ).collect(Collectors.toList());
        }
        return characterResponseList;
    }

    private CharacterModel mapDtoToCharacterModel(CharacterDTO characterDTO){
        CharacterModel characterModel = new CharacterModel();
        BeanUtils.copyProperties(characterDTO, characterModel);
        LocationModel locationModel = new LocationModel();
        BeanUtils.copyProperties(characterDTO.getLocation(), locationModel);
        characterModel.setLocation(locationModel);
        characterModel.setCreated(LocalDateTime.ofInstant(new Date().toInstant(),
                ZoneId.systemDefault()));
        if (characterDTO.getEpisodes() != null){
            Set<EpisodeModel> episodeModelList = new HashSet<>();
                    characterDTO.getEpisodes().forEach(id -> {
                Optional<EpisodeModel> episodeModel = episodeRepository.findById(id);
                if (episodeModel.isPresent()) episodeModelList.add(episodeModel.get());
            });
            characterModel.setEpisodes(episodeModelList);
        }

        return characterModel;
    }

    private CharacterResponse mapCharacterModelToCharacterResponse(CharacterModel characterModel){
        CharacterResponse characterResponse = new CharacterResponse();
        BeanUtils.copyProperties(characterModel, characterResponse);
        characterResponse.setEpisodeCount(characterModel.getEpisodes() != null ? characterModel.getEpisodes().size(): 0);
        return characterResponse;
    }
}
