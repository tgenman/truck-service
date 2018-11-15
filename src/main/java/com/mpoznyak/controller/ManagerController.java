package com.mpoznyak.controller;

import com.mpoznyak.dto.DriverDTO;
import com.mpoznyak.dto.TruckDTO;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.Customer;
import com.mpoznyak.model.type.DriverStatus;
import com.mpoznyak.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.plugin.liveconnect.SecurityContextHelper;

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

    @Autowired
    private OrderService orderService;

    @Loggable
    @RequestMapping("/manager-page")
    public String showManagerPage(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getPrincipal();
        model.addAttribute("drivers", driverService.getAllDrivers());
        model.addAttribute("trucks", truckService.getAllTrucks());
        model.addAttribute("driverDTO", new DriverDTO());

        model.addAttribute("truckDTO", new TruckDTO());
        model.addAttribute("cities", cityService.getAllCitiesMap());
        model.addAttribute("truckStatus", truckService.getTrucksStatus());
        model.addAttribute("customer", new Customer());

        model.addAttribute("orders", orderService.getAllOrdersDTO());
        model.addAttribute("truckDetails", truckService.getTrucksDetails());
        model.addAttribute("driverStatus", DriverStatus.values());
        return "manager";
    }

    @Loggable
    @PostMapping(value = "/delete-driver")
    public String processDriverDeleteButton(@RequestParam("driverIdDelete") String id) {
        Long longId = Long.parseLong(id);
        System.out.println(id);
        driverService.deleteDriver(longId);
        return "redirect:manager/id";
    }




}
