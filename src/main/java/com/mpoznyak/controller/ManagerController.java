package com.mpoznyak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Max Poznyak
 * on 21/10/2018  at 21:36
 */
@Controller
public class ManagerController {

    @RequestMapping(name = "/showDriverTab")
    public String showDriverTab() {
        return "driver_tab";
    }
}
