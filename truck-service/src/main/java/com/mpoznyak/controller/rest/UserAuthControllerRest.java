package com.mpoznyak.controller.rest;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Max Poznyak
 * on 2018-11-28  at 17:36
 */

@RestController
@RequestMapping("/api")
public class UserAuthControllerRest {

    @GetMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
