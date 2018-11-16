package com.mpoznyak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Max Poznyak
 * on 11/16/18  at 19:51
 */

@Controller
public class ExceptionController {

    @RequestMapping("/access-denied")
    public String throwAccessDenied() {
        return "/exceptions/access-denied";
    }
}
