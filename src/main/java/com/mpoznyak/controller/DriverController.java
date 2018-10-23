package com.mpoznyak.controller;

import com.mpoznyak.model.Driver;
import com.mpoznyak.service.DriverService;
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

    @RequestMapping(value = "/newDriver")
    public String showNewDriverPage(Model model) {
        Driver driver = new Driver();
        model.addAttribute("driver", driver);
        return "new-driver";
    }

    //TODO add list of cities, add cities and roads. Update JSP
    @RequestMapping(value = "/processNewDriverData", method = RequestMethod.POST)
    public String processNewDriver(@ModelAttribute("driver") Driver driver) {
        driverService.processNewDriverData(driver);
        return "manager";
    }
}
