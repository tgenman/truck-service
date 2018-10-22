package com.mpoznyak.controller;

/**
 * Created by Max Poznyak
 * on 18/10/2018  at 20:13
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "home")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String showHomePage() {

        return "home";
    }


}
