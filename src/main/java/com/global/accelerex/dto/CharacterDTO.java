package com.global.accelerex.dto;

import com.global.accelerex.utils.Gender;
import com.global.accelerex.utils.Status;
import com.global.accelerex.utils.validator.EnumValidator;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class CharacterDTO {



    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @EnumValidator(enumClass= Status.class,  message = "Status can either be ACTIVE, DEAD or UNKNOWN" )
    private String status;
    private String stateOfOrigin;
    @NotBlank
    @EnumValidator(enumClass= Gender.class,  message = "Gender can either be MALE or FEMALE" )
    private String gender;
    @Valid
    private LocationDTO location;

    private Set<Long> episodes = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStateOfOrigin() {
        return stateOfOrigin;
    }

    public void setStateOfOrigin(String stateOfOrigin) {
        this.stateOfOrigin = stateOfOrigin;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public Set<Long> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Set<Long> episodes) {
        this.episodes = episodes;
    }
}
