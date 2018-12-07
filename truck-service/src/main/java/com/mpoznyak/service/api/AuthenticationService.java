package com.mpoznyak.service.api;

import com.mpoznyak.dto.UserDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 16:36
 */

public interface AuthenticationService {

    UsernamePasswordAuthenticationToken authenticate(UserDTO userDTO);

    Authentication getAuthentication();

    void logOut(HttpServletRequest request, HttpServletResponse response);

}
