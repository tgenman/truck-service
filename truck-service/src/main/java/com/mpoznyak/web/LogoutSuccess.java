package com.mpoznyak.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Created by Max Poznyak
 * on 2018-11-29  at 14:06
 */

@Component
public class LogoutSuccess implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Authentication authentication) {
        if (authentication != null && authentication.getDetails() != null) {
            try {
                httpServletRequest.getSession().invalidate();
            } catch (Exception e) {
                e.printStackTrace();
                e = null;
            }
        }

        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }

}
