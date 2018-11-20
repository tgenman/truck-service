package com.mpoznyak.dto;

import com.mpoznyak.model.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 27/10/2018  at 15:42
 */

public class RouteDTO {

    private City carCity;

    private Long weight = 0L;

    private Boolean allDroppingLocationsSelected;

    public RouteDTO() {
        routePoints = new ArrayList<>();
    }

    private List<RoutePointDTO> routePoints;

    public List<RoutePointDTO> getRoutePoints() {
        return routePoints;
    }

    public void setRoutePoints(List<RoutePointDTO> routePoints) {
        this.routePoints = routePoints;
    }

    public void addRoutePoint(RoutePointDTO routePointDTO) {
        routePoints.add(routePointDTO);
    }

    public City getCarCity() {
        return carCity;
    }

    public void setCarCity(City carCity) {
        this.carCity = carCity;
    }

    public Boolean getAllDroppingLocationsSelected() {
        return allDroppingLocationsSelected;
    }

    public void setAllDroppingLocationsSelected(Boolean allDroppingLocationsSelected) {
        this.allDroppingLocationsSelected = allDroppingLocationsSelected;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }
}
