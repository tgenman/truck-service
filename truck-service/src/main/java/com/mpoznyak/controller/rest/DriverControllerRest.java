package com.mpoznyak.controller.rest;

import com.mpoznyak.dto.rest.DriverDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.service.DriverService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 02:26
 */
@RestController
@RequestMapping("/api/driver")
public class DriverControllerRest {

    private static final Logger log = Logger.getLogger(DriverControllerRest.class);
    @Autowired
    private DriverService driverService;

    @Loggable
    @GetMapping("/list")
    @Secured("ROLE_ADMIN")
    public List<DriverDTORest> getAllDrivers() {
        return driverService.getAllDriversDTO();
    }

    @Loggable
    @PutMapping("/new")
    @Secured("ROLE_ADMIN")
    public ResponseEntity addNewDriver(@RequestBody DriverDTORest driverDTORest) {
        driverService.addDriver(driverDTORest);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Loggable
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteDriver(@PathVariable Long id) {
        log.info("REQUEST FOR DELETING DRIVER WITH ID: " + id);
        driverService.deleteDriver(id);
    }

    @Loggable
    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public DriverDTORest getDriverById(@PathVariable Long id) {
        return driverService.getDriverDTORestById(id);
    }

    @Loggable
    @PostMapping("/update")
    @Secured("ROLE_ADMIN")
    public void updateDriver(@RequestBody DriverDTORest driverDTORest) {
        driverService.updateDriver(driverDTORest);
    }

}
