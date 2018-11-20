package com.mpoznyak.controller.rest;

import com.mpoznyak.dto.rest.RoutePointDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.service.RoutePointService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 16:30
 */

@RestController
@RequestMapping("/rest/route-point")
public class RoutePointControllerRest {

    @Autowired
    private RoutePointService routePointService;

    @GetMapping("/list")
    @Loggable
    public List<RoutePointDTORest> getAllRoutePoints() {
        return routePointService.getAllRoutePointsDTORest();
    }

}
