package com.mpoznyak.controller.rest;

import com.mpoznyak.dto.rest.CargoDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.service.CargoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 14:28
 */


@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/cargo")
public class CargoControllerRest {

    @Autowired
    private CargoService cargoService;

    @Loggable
    @GetMapping("/list")
    @Secured("ROLE_ADMIN")
    public List<CargoDTORest> getAllCargoes() {
        return cargoService.getAllCargoesDTORest();
    }

}
