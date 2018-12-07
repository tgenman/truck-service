package com.mpoznyak.controller.rest;

import com.mpoznyak.dto.rest.CityDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.service.CityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 14:28
 */


@RestController
@RequestMapping("/api/city")
public class CItyControllerRest {

    @Autowired
    private CityService cityService;

    @Loggable
    @GetMapping("/list")
    @Secured("ROLE_ADMIN")
    public List<CityDTORest> getAllCities() {
        return cityService.getAllCitiesDTORest();
    }

}
