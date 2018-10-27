package com.mpoznyak.controller;

import com.mpoznyak.dto.CompoundRoutePointDTO;
import com.mpoznyak.dto.OrderDTO;
import com.mpoznyak.model.Customer;
import com.mpoznyak.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by Max Poznyak
 * on 26/10/2018  at 21:07
 */

@Controller
public class OrderController {



    //TODO implement
    @PostMapping(value = "save-order")
    public String saveOrder(@ModelAttribute("order") Order order) {

        return null;
    }

    @PostMapping(value = "new-route")
    public String processNewPoint(@ModelAttribute("route-point")CompoundRoutePointDTO routePoint,
                                  @ModelAttribute("customer") Customer customer,
                                  Model model) {

        model.addAttribute("customer", customer);
        model.addAttribute("route-point", routePoint);
        model.addAttribute("order", new OrderDTO());
        return "new-order";
    }






}
