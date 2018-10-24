package com.mpoznyak.controller;

import com.mpoznyak.service.DriverService;
import com.mpoznyak.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Max Poznyak
 * on 24/10/2018  at 12:05
 */
@Controller
public class ManagerController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private TruckService truckService;

    @RequestMapping("managerPage")
    public String showManagerPage(Model model) {

        model.addAttribute("drivers", driverService.getAllDrivers());
        model.addAttribute("trucks", truckService.getAllTrucks());
        return "manager";

    }
}
