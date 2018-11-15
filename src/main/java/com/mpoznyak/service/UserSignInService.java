package com.mpoznyak.service;

import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.User;
import com.mpoznyak.model.type.Role;
import com.mpoznyak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 01:28
 */

@Service
public class UserSignInService {

    @Autowired
    UserRepository userRepository;

    /*
    @Loggable
    @Transactional
    public Role checkAuthInput(Long companyId, String password) {

        List<User> users = userRepository.queryAll();
        Role role = null;
        for (User testUser : users) {
            if (testUser.getCompanyId() == companyId
                    && testUser.getPassword().equals(password)) {
                role = testUser.getRole();
            }
        }
        return role;
    }
    */
}
