package com.mpoznyak.controller;

import com.mpoznyak.model.User;
import com.mpoznyak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Max Poznyak
 * on 20/10/2018  at 11:29
 */
@Controller
public class SignInController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/signin")
    public String showSignIn(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signin";
    }

    @RequestMapping(value = "processData", method = POST)
    public String processSubmit(@ModelAttribute("user") User user, Model model) {
        Long companyId = user.getCompanyId();
        String password = user.getPassword();
        List<User> users = userRepository.queryAll();
        for (User testUser : users) {
            if (testUser.getCompanyId() == companyId
                && testUser.getPassword().equals(password)) {
                return "manager_page";
            }
        }
        model.addAttribute("user", new User());
        return "try_signin";
    }
}
