package com.mpoznyak.service.api;

import com.mpoznyak.dto.UserDTO;
import com.mpoznyak.model.User;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 18:05
 */
public interface UserService {

    User findUserByCompanyId(String companyId);

    void addNewUser(User user);

    void addNewUser(UserDTO userDTO);

    org.springframework.security.core.userdetails.User getAuthenticatedUser();
}
