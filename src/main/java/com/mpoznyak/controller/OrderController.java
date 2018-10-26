package com.mpoznyak.controller;

import com.mpoznyak.model.Order;
import org.springframework.stereotype.Controller;
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




}
