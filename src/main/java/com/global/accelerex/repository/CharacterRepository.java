package com.global.accelerex.repository;

import com.global.accelerex.model.CharacterModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterModel, Long> {

    List<CharacterModel> findByLocationName(String locationName, Sort sort);

    List<CharacterModel> findByStatus(String status, Sort sort);

    List<CharacterModel> findByGender(String gender, Sort sort);

    List<CharacterModel> findByStatusAndGenderAndLocationName(String status, String gender, String locationName, Sort sort);

    List<CharacterModel> findByStatusAndGender(String status, String gender, Sort sort);

    List<CharacterModel> findByStatusAndLocationName(String status, String locationName, Sort sort);

    List<CharacterModel> findByGenderAndLocationName(String gender, String locationName, Sort sort);
}
