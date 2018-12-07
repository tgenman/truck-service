package com.mpoznyak.controller;

import com.mpoznyak.logging.annotation.Loggable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Max Poznyak
 * on 20/10/2018  at 11:29
 */

@Controller
public class UserController {

    @Loggable
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

}
