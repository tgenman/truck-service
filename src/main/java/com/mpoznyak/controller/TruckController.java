package com.mpoznyak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 14:23
 */

@Controller
public class TruckController {

    @RequestMapping("newTruck")
    public String showNewTruckPage() {
        return "new-truck";
    }
}
