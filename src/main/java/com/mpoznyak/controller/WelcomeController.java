package com.mpoznyak.controller;

/**
 * Created by Max Poznyak
 * on 18/10/2018  at 20:13
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @RequestMapping("/index")
    public String showIndex() {
        return "index";
    }


}
