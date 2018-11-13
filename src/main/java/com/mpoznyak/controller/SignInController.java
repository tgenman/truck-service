package com.mpoznyak.controller;

import com.mpoznyak.dto.RoutePointDTO;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.Driver;
import com.mpoznyak.model.Order;
import com.mpoznyak.model.RoutePoint;
import com.mpoznyak.model.User;
import com.mpoznyak.model.type.DriverStatus;
import com.mpoznyak.model.type.Role;
import com.mpoznyak.service.DriverService;
import com.mpoznyak.service.OrderService;
import com.mpoznyak.service.UserSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Max Poznyak
 * on 20/10/2018  at 11:29
 */
@Controller
public class SignInController {

    @Autowired
    private UserSignInService userSignInService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private OrderService orderService;

    @Loggable
    @GetMapping("/sign-in")
    public String showSignIn(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    //TODO switch to oauth2 spring security

    @Loggable
    @RequestMapping(value = "/processAuthInput", method = POST)
    public String processSubmit(@ModelAttribute("user") User user, Model model) {

        Long companyId = user.getCompanyId();
        String password = user.getPassword();
        Role role = userSignInService.checkAuthInput(companyId, password);
        if (role==null) {
            role = Role.NOT_AUTHORIZED;
        }

        switch (role) {
            case DRIVER:
                List<Driver> drivers = driverService.getAllDrivers();
                Driver driver = null;
                for (Driver driver1 : drivers) {
                    if (companyId == driver1.getId()) {
                        driver = driver1;
                    }
                }
                Order order = driver.getOrder();
                if (order != null) {
                    List<RoutePoint> points = orderService.getRoutePointsForOrder(order);
                    model.addAttribute("routePoints", points);
                } else {
                    model.addAttribute("routePoints", new ArrayList<>());

                }
                model.addAttribute("driverStatus", DriverStatus.values());
                model.addAttribute("routePointDTO", new RoutePointDTO());
                model.addAttribute("driver", driver);
                return "driver";
            case MANAGER:
                return "redirect:managerPage";
            default:
                model.addAttribute("user", new User());
                return "try-sign-in";
        }


    }
}
