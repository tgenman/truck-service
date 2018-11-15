package com.mpoznyak.controller;

import com.mpoznyak.dto.UserDTO;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.service.AuthenticationService;
import com.mpoznyak.service.DriverService;
import com.mpoznyak.service.OrderService;
import com.mpoznyak.service.UserSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Max Poznyak
 * on 20/10/2018  at 11:29
 */

@Controller
public class UserController {

    @Autowired
    private UserSignInService userSignInService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthenticationService authenticationService;

    @Loggable
    @GetMapping("/login")
    public String showLogin(Model model) {
        return "login";
    }

    @Loggable
    @GetMapping("/user")
    public void showUser(Model model) {

    }
}
