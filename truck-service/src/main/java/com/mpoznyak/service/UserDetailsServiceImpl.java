package com.mpoznyak.service;

import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.User;
import com.mpoznyak.model.type.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Max Poznyak
 * on 11/14/18  at 02:46
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Loggable
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userService.findUserByCompanyId(id);

        if (user == null) {
            throw new UsernameNotFoundException("No workers with id " + id);
        }



        String companyId = user.getCompanyId();
        String password = null;

        //TODO after implementing admin - delete condition "123"

        if (user.getCompanyId().equals("123") || user.getCompanyId().equals("admin")) {
            password = new BCryptPasswordEncoder().encode(user.getPassword());
        } else {
            password = user.getPassword();
        }
        String role = user.getRole();

        List<SimpleGrantedAuthority> authList = getAuthorities(role);

        return new org.springframework.security.core.userdetails.User(companyId, password, true,
                true,
                true,
                true, authList);

    }

    private List<SimpleGrantedAuthority> getAuthorities(String role) {
        List<SimpleGrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority(role));
        return auths;
    }
}

