package com.mpoznyak.controller.rest;

import com.mpoznyak.dto.DriverDTO;
import com.mpoznyak.dto.RoutePointDTO;
import com.mpoznyak.dto.rest.DriverDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.Driver;
import com.mpoznyak.model.Order;
import com.mpoznyak.model.RoutePoint;
import com.mpoznyak.model.type.DriverStatus;
import com.mpoznyak.service.CityService;
import com.mpoznyak.service.DriverService;
import com.mpoznyak.service.OrderService;
import com.mpoznyak.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 02:26
 */
@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/driver")
public class DriverControllerRest {

    private final DriverService driverService;


    @Autowired
    public DriverControllerRest(DriverService driverService) {
        this.driverService = driverService;
    }

    @Loggable
    @GetMapping("/list")
    @Secured("ROLE_ADMIN")
    public List<DriverDTORest> getAllDrivers()  {
        return driverService.getAllDriversDTO();
    }

}
