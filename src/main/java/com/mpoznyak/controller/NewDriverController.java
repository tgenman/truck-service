package com.mpoznyak.controller;

import com.mpoznyak.entity.Truck;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewDriverController {

    @RequestMapping("/newTruck")
    public String newTruck(Model theModel) {

        Truck truck = new Truck();

        theModel.addAttribute("truck", truck);

        return "new_driver";
    }

    @RequestMapping("/result")
    public String result(@ModelAttribute("truck") Truck truck) {


        return "";
    }
}
