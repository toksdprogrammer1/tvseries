package com.global.accelerex.service;

import com.global.accelerex.dto.CharacterDTO;
import com.global.accelerex.dto.param.CharacterFilterparameter;
import com.global.accelerex.dto.response.CharacterResponse;
import com.global.accelerex.exception.BadRequestException;
import com.global.accelerex.model.CharacterModel;
import com.global.accelerex.model.EpisodeModel;
import com.global.accelerex.model.LocationModel;
import com.global.accelerex.repository.CharacterRepository;
import com.global.accelerex.repository.EpisodeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort.Order;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

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

    public List<CharacterResponse> getAllCharacters(String[] sort, CharacterFilterparameter characterFilterparameter){
        List<Order> orders = new ArrayList<>();
        if (sort[0].contains(",")) {
            // will sort more than 2 fields
            // sortOrder="field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            // sort=[field, direction]
            orders.add(new Order(getSortDirection(sort[1]), sort[0]));
        }
        List<CharacterModel> characterModelList = findCharacters(characterFilterparameter, orders);

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

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("ascending")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("descending")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    private List<CharacterModel> findCharacters(CharacterFilterparameter characterFilterparameter, List<Order> orders){
        List<CharacterModel> characterModelList = new ArrayList<>();
        if (characterFilterparameter == null) characterModelList = characterRepository.findAll(Sort.by(orders));
        else if (characterFilterparameter.getStatus() != null && characterFilterparameter.getLocationName() == null
                && characterFilterparameter.getGender() == null)
            characterModelList = characterRepository.findByStatus(characterFilterparameter.getStatus(), Sort.by(orders));
        else if (characterFilterparameter.getGender() != null && characterFilterparameter.getLocationName() == null
                && characterFilterparameter.getStatus() == null)
            characterModelList = characterRepository.findByGender(characterFilterparameter.getGender(), Sort.by(orders));
        else if (characterFilterparameter.getLocationName() != null && characterFilterparameter.getStatus() == null
                && characterFilterparameter.getGender() == null)
            characterModelList =  characterRepository.findByLocationName(characterFilterparameter.getLocationName(), Sort.by(orders));
        else if (characterFilterparameter.getStatus() != null && characterFilterparameter.getLocationName() != null
                && characterFilterparameter.getGender() == null)
            characterModelList =  characterRepository.findByStatusAndLocationName(characterFilterparameter.getStatus(),
                    characterFilterparameter.getLocationName(), Sort.by(orders));
        else if (characterFilterparameter.getStatus() != null && characterFilterparameter.getLocationName() == null
                && characterFilterparameter.getGender() != null)
            characterModelList = characterRepository.findByStatusAndGender(characterFilterparameter.getStatus(),
                    characterFilterparameter.getGender(), Sort.by(orders));
        else if (characterFilterparameter.getStatus() == null && characterFilterparameter.getLocationName() != null
                && characterFilterparameter.getGender() != null)
            characterModelList = characterRepository.findByGenderAndLocationName(characterFilterparameter.getGender(),
                    characterFilterparameter.getLocationName(), Sort.by(orders));
        else if (characterFilterparameter.getStatus() != null && characterFilterparameter.getLocationName() != null
                && characterFilterparameter.getGender() != null)
            characterModelList = characterRepository.findByStatusAndGenderAndLocationName(characterFilterparameter.getStatus(),
                    characterFilterparameter.getGender(),
                    characterFilterparameter.getLocationName(), Sort.by(orders));
        else characterModelList = characterRepository.findAll(Sort.by(orders));

        return characterModelList;
    }

}
