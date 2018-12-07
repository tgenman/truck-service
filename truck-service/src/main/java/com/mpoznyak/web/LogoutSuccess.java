package com.mpoznyak.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Created by Max Poznyak
 * on 2018-11-29  at 14:06
 */

@Component
public class LogoutSuccess implements LogoutSuccessHandler {

    private final static Logger logger = Logger.getLogger(LogoutSuccess.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Authentication authentication) {
        if (authentication != null && authentication.getDetails() != null) {
            try {
                httpServletRequest.getSession().invalidate();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }

}
