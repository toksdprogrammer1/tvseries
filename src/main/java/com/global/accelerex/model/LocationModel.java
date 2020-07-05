package com.global.accelerex.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class LocationModel {

    @NotNull
    private String name;
    @NotNull
    private double latitude;
    @NotNull
    private double longitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
