package com.mpoznyak.service;

import com.mpoznyak.dto.UserDTO;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.service.api.AuthenticationService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

/**
 * Created by Max Poznyak
 * on 11/14/18  at 14:36
 */

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    @Loggable
    public UsernamePasswordAuthenticationToken authenticate(UserDTO userDTO) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getCompanyId());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, userDTO.getPassword(), userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        return usernamePasswordAuthenticationToken;
    }

    @Override
    @Loggable
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    @Loggable
    public void logOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = getAuthentication();
        if (authentication != null)
            new SecurityContextLogoutHandler().logout(request, response, authentication);
    }
}
