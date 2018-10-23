package com.mpoznyak.controller;

import com.mpoznyak.model.User;
import com.mpoznyak.service.UserSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Max Poznyak
 * on 20/10/2018  at 11:29
 */
@Controller
public class SignInController {

    @Autowired
    UserSignInService userSignInService;


    @GetMapping("/signIn")
    public String showSignIn(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "sign-in";
    }

    @RequestMapping(value = "/processAuthInput", method = POST)
    public String processSubmit(@ModelAttribute("user") User user, Model model) {

        Long companyId = user.getCompanyId();
        String password = user.getPassword();
        boolean authorized = userSignInService.checkAuthInput(companyId, password);

        if (authorized) {
            return "manager";
        } else {
            model.addAttribute("user", new User());
            return "try-sign-in";
        }
    }
}
