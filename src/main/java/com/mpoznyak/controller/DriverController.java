package com.mpoznyak.controller;

import com.mpoznyak.dto.DriverDTO;
import com.mpoznyak.model.Driver;
import com.mpoznyak.model.type.DriverStatus;
import com.mpoznyak.service.CityService;
import com.mpoznyak.service.DriverService;
import com.mpoznyak.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 02:26
 */
@Controller
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private CityService cityService;

    @Autowired
    private TruckService truckService;

    @RequestMapping(value = "/newDriver")
    public String showNewDriverPage(Model model) {
        Driver driver = new Driver();
        model.addAttribute("driverDTO", new DriverDTO());
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("trucks", truckService.getAllTrucks());
        model.addAttribute("status", driverService.getAllDriverStatus());
        return "new-driver";
    }

    @RequestMapping(value = "/processNewDriverData", method = RequestMethod.POST)
    public String processNewDriver(@ModelAttribute("driver") DriverDTO driverDTO) {
        driverService.processNewDriverData(driverDTO);
        return "manager";
    }
}
