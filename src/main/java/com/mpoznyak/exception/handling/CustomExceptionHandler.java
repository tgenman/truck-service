package com.mpoznyak.exception.handling;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Max Poznyak
 * on 11/16/18  at 19:25
 */

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException ex, Model model) {
        model.addAttribute("name", ex.getClass().getSimpleName());
        return "exceptions/null-pointer-exception";
    }
}
