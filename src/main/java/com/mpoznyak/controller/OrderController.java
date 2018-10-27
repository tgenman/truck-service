package com.mpoznyak.controller;

import com.mpoznyak.dto.OrderDetailsDTO;
import com.mpoznyak.dto.OrderDTO;
import com.mpoznyak.model.City;
import com.mpoznyak.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by Max Poznyak
 * on 26/10/2018  at 21:07
 */

@Controller
public class OrderController {

    @Autowired
    private CityService cityService;


    //TODO implement
    @PostMapping(value = "save-order")
    public String saveOrder(@ModelAttribute("orderDetails") OrderDetailsDTO orderDetails) {


        return "redirect:managerPage";
    }

    @GetMapping(value = "/new-order")
    public String processNewPoint(Model model) {
        model.addAttribute("orderDetails", new OrderDetailsDTO());
        model.addAttribute("cities", cityService.getAllCities());
        return "new-order";
    }






}
