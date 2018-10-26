package com.mpoznyak.controller;

import com.mpoznyak.dto.DriverDTO;
import com.mpoznyak.service.CityService;
import com.mpoznyak.service.DriverService;
import com.mpoznyak.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private CityService cityService;

    @GetMapping("/managerPage")
    public String showManagerPage(Model model) {

        model.addAttribute("drivers", driverService.getAllDrivers());
        model.addAttribute("trucks", truckService.getAllTrucks());
        model.addAttribute("driverDTO", new DriverDTO());

        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("truckDetails", truckService.getTrucksDetails());
        model.addAttribute("status", driverService.getAllDriverStatus());
        return "manager";
    }

    @PostMapping(value = "/delete-driver")
    public String processDriverDeleteButton(@RequestParam("driverIdDelete") String id) {
        Long longId = Long.parseLong(id);
        System.out.println(id);
        driverService.deleteDriver(longId);
        return "redirect:managerPage";
    }


}
