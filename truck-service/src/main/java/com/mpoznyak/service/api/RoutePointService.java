package com.mpoznyak.service.api;

import com.mpoznyak.dto.RoutePointDTO;
import com.mpoznyak.dto.rest.RoutePointDTORest;
import com.mpoznyak.model.RoutePoint;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 17:35
 */

public interface RoutePointService {

    void updateRoutePoint(RoutePoint routePoint);

    void updatePointStatusForOrder(RoutePoint routePoint);

    void updateRoutePoint(RoutePointDTO routePointDTO);

    List<RoutePoint> getRoutePoints();

    List<RoutePointDTORest> getAllRoutePointsDTORest();

    void deleteRoutePoint(RoutePoint routePoint);

    void addRoutePoints(Iterable<RoutePoint> points);
}
