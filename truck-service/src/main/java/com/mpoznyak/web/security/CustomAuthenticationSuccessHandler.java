package com.mpoznyak.web.security;

import com.mpoznyak.logging.annotation.Loggable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Max Poznyak
 * on 11/16/18  at 03:22
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Loggable
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ROLE_MANAGER".equals(auth.getAuthority())){
                httpServletResponse.sendRedirect("/management/manager");
            }

            if ("ROLE_DRIVER".equals(auth.getAuthority())) {
                String name = authentication.getName();
                httpServletResponse.sendRedirect("/driver/" + name);
            }

            if ("ROLE_ADMIN".equals(auth.getAuthority())){
                httpServletResponse.sendRedirect("/management/manager");
            }

        }
    }

}
