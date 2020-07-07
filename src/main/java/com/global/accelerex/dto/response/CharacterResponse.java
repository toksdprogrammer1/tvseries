package com.global.accelerex.dto.response;


import com.global.accelerex.model.AuditModel;
import com.global.accelerex.model.LocationModel;

public class CharacterResponse extends AuditModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String status;
    private String stateOfOrigin;
    private String gender;
    private LocationModel location;
    private long episodeCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public long getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(long episodeCount) {
        this.episodeCount = episodeCount;
    }
}
