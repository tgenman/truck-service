package com.mpoznyak.controller.rest;

import com.mpoznyak.dto.rest.TruckDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.service.TruckService;
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

import java.util.List;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 01:17
 */

@RestController
@RequestMapping("/api/truck")
public class TruckControllerRest {

    @Autowired
    private TruckService truckService;

    @Loggable
    @GetMapping("/list")
    @Secured("ROLE_ADMIN")
    public List<TruckDTORest> getAllTrucks() {
        return truckService.getAllTrucksDTORest();
    }


    @Loggable
    @PutMapping("/new")
    @Secured("ROLE_ADMIN")
    public ResponseEntity addNewTruck(@RequestBody TruckDTORest truckDTORest) {
        truckService.saveNewTruck(truckDTORest);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Loggable
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteTruck(@PathVariable Long id) {
        truckService.deleteTruck(id);
    }

    @Loggable
    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public TruckDTORest getTruckById(@PathVariable Long id) {
        return truckService.getTruckDTORestById(id);
    }

    @Loggable
    @PostMapping("/update")
    @Secured("ROLE_ADMIN")
    public void updateTruck(@RequestBody TruckDTORest truckDTORest) {
        truckService.updateTruck(truckDTORest);
    }
}
