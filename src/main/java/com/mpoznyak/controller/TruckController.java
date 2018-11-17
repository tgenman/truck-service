package com.mpoznyak.controller;

import com.mpoznyak.dto.TruckDTO;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.service.CityService;
import com.mpoznyak.service.DriverService;
import com.mpoznyak.service.TruckService;
import com.mpoznyak.validator.form.TruckForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 14:23
 */

@Controller
public class TruckController {

    @Autowired
    private TruckService truckService;

    @Autowired
    private CityService cityService;


}
