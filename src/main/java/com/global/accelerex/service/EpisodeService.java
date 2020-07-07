package com.global.accelerex.service;

import com.global.accelerex.dto.EpisodeDTO;
import com.global.accelerex.dto.response.EpisodeResponse;
import com.global.accelerex.exception.ResourceNotFoundException;
import com.global.accelerex.model.CharacterModel;
import com.global.accelerex.model.EpisodeModel;
import com.global.accelerex.repository.CharacterRepository;
import com.global.accelerex.repository.EpisodeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EpisodeService {

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private CharacterRepository characterRepository;

    public EpisodeResponse addEpisode(EpisodeDTO episodeDTO){
        EpisodeModel episodeModel = mapDtoToEpisodeModel(episodeDTO);
        episodeModel = episodeRepository.save(episodeModel);
        return mapEpisodeModelToEpisodeResponse(episodeModel);
    }

    public List<EpisodeResponse> getAllEpisodes(){
        List<EpisodeModel> episodeModelList = episodeRepository.findAll(Sort.by("releaseDate"));
        List<EpisodeResponse> episodeResponseList = new ArrayList<>();
        if (episodeModelList != null){
            episodeResponseList = episodeModelList.stream().map(
                    episodeModel -> mapEpisodeModelToEpisodeResponse(episodeModel)
            ).collect(Collectors.toList());
        }
        return episodeResponseList;
    }

    public List<EpisodeResponse> getCharacterEpisodes(Long characterId){
        if (characterId == null) throw new ResourceNotFoundException("Character Id  can not be null");
        Optional<CharacterModel> characterModel = characterRepository.findById(characterId);
        if (!characterModel.isPresent()) throw new ResourceNotFoundException("Character id " + characterId + " not found");

        Set<EpisodeModel> episodeModelList = characterModel.get().getEpisodes();
        List<EpisodeResponse> episodeResponseList = new ArrayList<>();
        if (episodeModelList != null){
            episodeResponseList = episodeModelList.stream().map(
                    episodeModel -> mapEpisodeModelToEpisodeResponse(episodeModel)
            ).collect(Collectors.toList());
        }
        return episodeResponseList;
    }

    private EpisodeModel mapDtoToEpisodeModel(EpisodeDTO episodeDTO){
        EpisodeModel episodeModel = new EpisodeModel();
        BeanUtils.copyProperties(episodeDTO, episodeModel);
        episodeModel.setCreated(LocalDateTime.ofInstant(new Date().toInstant(),
                ZoneId.systemDefault()));
        return episodeModel;
    }

    private EpisodeResponse mapEpisodeModelToEpisodeResponse(EpisodeModel episodeModel){
        EpisodeResponse episodeResponse = new EpisodeResponse();
        BeanUtils.copyProperties(episodeModel, episodeResponse);
        episodeResponse.setCommentsCount(episodeModel.getComments() != null ? episodeModel.getComments().size(): 0);
        return episodeResponse;
    }
}
