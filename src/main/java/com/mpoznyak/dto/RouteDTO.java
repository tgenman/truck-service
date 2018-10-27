package com.mpoznyak.dto;

import java.util.List;

/**
 * Created by Max Poznyak
 * on 27/10/2018  at 15:42
 */

public class RouteDTO {

    List<CompoundRoutePointDTO> routePoints;

    public List<CompoundRoutePointDTO> getRoutePoints() {
        return routePoints;
    }

    public void setRoutePoints(List<CompoundRoutePointDTO> routePoints) {
        this.routePoints = routePoints;
    }
}
