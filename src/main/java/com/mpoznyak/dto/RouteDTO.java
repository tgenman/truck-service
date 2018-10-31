package com.mpoznyak.dto;

import com.mpoznyak.model.Cargo;
import com.mpoznyak.model.RoutePoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 27/10/2018  at 15:42
 */

public class RouteDTO {

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
}
