package com.mpoznyak.dto.rest;

/**
 * Created by Max Poznyak
 * on 11/19/18  at 18:43
 */

public class CityDTORest {

    private Long  id;
    private String name;
    private Double latitude;
    private Double longtitude;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }
}
