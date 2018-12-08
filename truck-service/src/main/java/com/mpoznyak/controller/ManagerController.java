package com.mpoznyak.controller;

import com.mpoznyak.dto.DriverDTO;
import com.mpoznyak.dto.TruckDTO;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.Customer;
import com.mpoznyak.model.type.DriverStatus;
import com.mpoznyak.service.api.CityService;
import com.mpoznyak.service.api.DriverService;
import com.mpoznyak.service.api.OrderService;
import com.mpoznyak.service.api.TruckService;
import com.mpoznyak.validator.form.DriverForm;
import com.mpoznyak.validator.form.TruckForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Created by Max Poznyak
 * on 24/10/2018  at 12:05
 */

@Controller
@RequestMapping("management")
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
    @GetMapping("/manager")
    public String showManagerPage(Model model) {

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
    @PostMapping(value = "driver/delete")
    public String processDriverDeleteButton(@RequestParam("id") Long id) {
        Long longId = id;
        driverService.deleteDriver(longId);
        return "redirect:/management/manager";
    }

    @Loggable
    @GetMapping(value = "driver/new")
    public String showNewDriverPage(Model model) {

        model.addAttribute("driverForm", new DriverForm());
        model.addAttribute("cities", cityService.getAllCitiesMap());
        return "new-driver";
    }

    @Loggable
    @PostMapping(value = "driver/new/process")
    public String processNewDriver(@ModelAttribute("driverForm") @Valid DriverForm driverForm,
                                   BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("cities", cityService.getAllCitiesMap());
            return "new-driver";
        }

        driverService.addDriverFromForm(driverForm);
        return "redirect:/management/manager";
    }

    @Loggable
    @GetMapping("truck/new")
    public String showNewTruckPage(Model model) {
        model.addAttribute("truckForm", new TruckForm());
        model.addAttribute("cities", cityService.getAllCitiesMap());
        model.addAttribute("truckStatus", truckService.getTrucksStatus());
        return "new-truck";
    }

    @Loggable
    @RequestMapping("truck/new/process")
    public String processNewTruckData(@ModelAttribute("truckForm") @Valid TruckForm truckForm,
                                      BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("cities", cityService.getAllCitiesMap());
            model.addAttribute("truckStatus", truckService.getTrucksStatus());
            return "new-truck";
        }

        truckService.saveNewTruck(truckForm);
        return "redirect:/management/manager";
    }

    @Loggable
    @RequestMapping(value = "truck/update", method = RequestMethod.POST)
    public String processUpdateDriverData(@ModelAttribute("truckDTO") TruckDTO truckDTO) {

        truckService.updateTruck(truckDTO);
        return "redirect:/management/manager";
    }

    @Loggable
    @PostMapping(value = "truck/delete")
    public String processTruckDeleteButton(@RequestParam("truckIdDelete") String id) {
        Long longId = Long.parseLong(id);
        truckService.deleteTruck(longId);
        return "redirect:/management/manager";
    }

    @Loggable
    @RequestMapping(value = "driver/update", method = RequestMethod.POST)
    public String processUpdateDriverData(@ModelAttribute("driver") DriverDTO driverDTO) {

        driverService.updateDriver(driverDTO);
        return "redirect:/management/manager";
    }

    @Loggable
    @PostMapping("order/delete")
    public String deleteOrder(@RequestParam("orderId") Long id) {
        orderService.deleteOrder(id);
        return "redirect:/management/manager";
    }




}
