package com.mpoznyak.controller;

import com.mpoznyak.dto.TruckDTO;
import com.mpoznyak.service.CityService;
import com.mpoznyak.service.DriverService;
import com.mpoznyak.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @Autowired
    private DriverService driverService;

    @RequestMapping("newTruck")
    public String showNewTruckPage(Model model) {
        model.addAttribute("truckDTO", new TruckDTO());
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("truckStatus", truckService.getTrucksStatus());
        return "new-truck";
    }

    @RequestMapping("processNewTruckData")
    public String processNewTruckData(@ModelAttribute("truck") TruckDTO truckDTO, Model model) {
        truckService.saveNewTruckData(truckDTO);
        return "redirect:managerPage";
    }
}
