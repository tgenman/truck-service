package com.mpoznyak.dto;

import java.util.List;

/**
 * Created by Max Poznyak
 * on 27/10/2018  at 15:42
 */

public class RouteDTO {

    List<OrderDetailsDTO> routePoints;

    public List<OrderDetailsDTO> getRoutePoints() {
        return routePoints;
    }

    public void setRoutePoints(List<OrderDetailsDTO> routePoints) {
        this.routePoints = routePoints;
    }
}
