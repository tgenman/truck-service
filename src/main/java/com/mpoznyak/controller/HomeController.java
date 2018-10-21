package com.mpoznyak.controller;

/**
 * Created by Max Poznyak
 * on 18/10/2018  at 20:13
 */

import com.mpoznyak.configuration.Constants;
import com.mpoznyak.model.User;
import com.mpoznyak.model.type.UserType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Controller
@RequestMapping({"/", "home"})
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String showHomePage() {

        return "home";
    }


}
