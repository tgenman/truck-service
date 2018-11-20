package com.mpoznyak.controller.rest;

import com.mpoznyak.dto.TruckDTO;
import com.mpoznyak.dto.rest.TruckDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.mapper.TruckMapper;
import com.mpoznyak.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 01:17
 */

@RestController
@RequestMapping("/rest/truck")
public class TruckControllerRest {

    @Autowired
    private TruckService truckService;

    @Loggable
    @GetMapping("/list")
    public List<TruckDTORest> getAllTrucks() {
        return truckService.getAllTrucksDTORest();
    }
}
