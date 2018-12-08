package com.mpoznyak.exception.handling;

import com.mpoznyak.logging.annotation.Loggable;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by Max Poznyak
 * on 11/16/18  at 19:25
 */


@ControllerAdvice
public class CustomExceptionHandler {


    private static final Logger logger = Logger.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(NullPointerException.class)
    public String handleError(HttpServletRequest request, Exception e) {
        logger.error("[ERROR]: " + e.getMessage());
        return "redirect: abort-order";
    }
}
