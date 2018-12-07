package com.mpoznyak.service;

import com.mpoznyak.dto.UserDTO;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.User;
import com.mpoznyak.repository.api.UserRepository;
import com.mpoznyak.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Max Poznyak
 * on 11/14/18  at 13:03
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    @Loggable
    public User findUserByCompanyId(String companyId) {
        List<User> users = userRepository.queryAll();
        for (User user : users) {
            if (user.getCompanyId().equals(companyId))
                return user;
        }
        return null;
    }

    @Loggable
    @Override
    public org.springframework.security.core.userdetails.User getAuthenticatedUser() {
        return (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }

    @Override
    @Loggable
    @Transactional
    public void addNewUser(User user) {
        userRepository.add(user);
    }

    @Override
    @Loggable
    @Transactional
    public void addNewUser(UserDTO userDTO) {
        User user = new User();
        user.setCompanyId(userDTO.getCompanyId());
        String passwordEncoded = new BCryptPasswordEncoder().encode(userDTO.getPassword());
        user.setPassword(passwordEncoded);
        user.setRole(userDTO.getRole());
        userRepository.add(user);
    }
}
