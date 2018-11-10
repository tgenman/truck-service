package com.mpoznyak.service;

import com.mpoznyak.dto.RoutePointDTO;
import com.mpoznyak.model.RoutePoint;
import com.mpoznyak.repository.RoutePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Max Poznyak
 * on 11/8/18  at 13:09
 */

@Service
public class RoutePointService {

    @Autowired
    private RoutePointRepository routePointRepository;

    @Transactional
    public void updateRoutePoint(RoutePoint routePoint) {
        routePointRepository.update(routePoint);
    }

    @Transactional
    public void updateRoutePoint(RoutePointDTO routePointDTO) {
        List<RoutePoint> pointList = routePointRepository.query();
        for (RoutePoint routePoint : pointList) {
            if (routePointDTO.getId() == routePoint.getId()) {
                routePoint.setCompleted(routePointDTO.getCompleted());
                routePointRepository.update(routePoint);
                break;
            }
        }
    }

    @Transactional
    public List<RoutePoint> getRoutePoints() {
        return routePointRepository.query();
    }
}
