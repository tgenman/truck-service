package com.mpoznyak.service;

import com.mpoznyak.dto.rest.ManagerDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.mapper.ManagerMapper;
import com.mpoznyak.model.Manager;
import com.mpoznyak.model.User;
import com.mpoznyak.repository.ManagerRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Max Poznyak
 * on 2018-12-03  at 15:01
 */

@Service
public class ManagerService {

    @Autowired
    private UserService userService;

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private ManagerRepository managerRepository;

    @Loggable
    @Transactional
    public void addNewManager(Manager manager) {
        managerRepository.add(manager);
    }

    @Loggable
    @Transactional
    public void addNewManager(ManagerDTORest managerDTORest) {
        Manager manager = managerMapper.map(managerDTORest);
        manager.setFirstName(managerDTORest.getFirstName());
        manager.setLastName(managerDTORest.getLastName());
        User user = new User();
        String password = new BCryptPasswordEncoder().encode(managerDTORest.getPassword());
        user.setCompanyId(managerDTORest.getUsername());
        user.setPassword(password);
        user.setRole("ROLE_MANAGER");
        userService.addNewUser(user);
        User userTemp = userService.findUserByCompanyId(String.valueOf(managerDTORest.getUsername()));
        manager.setUser(userTemp);
        managerRepository.add(manager);

    }

    @Loggable
    @Transactional
    public void deleteManager(Long id) {
        managerRepository.remove(id);
    }

    @Loggable
    @Transactional
    public List<Manager> getAllManagers() {
        return managerRepository.query();
    }

    @Loggable
    @Transactional
    public List<ManagerDTORest> getAllManagersDTORest() {
        List<Manager> managers =getAllManagers();
        List<ManagerDTORest> dtos = new ArrayList<>();

        for (Manager manager : managers) {
            ManagerDTORest dto = managerMapper.mapToDTORestFrom(manager);
            dtos.add(dto);
        }
        return dtos;
    }

    @Loggable
    @Transactional
    public void updateManager(ManagerDTORest managerDTORest) {
        Manager manager = managerMapper.map(managerDTORest);
        managerRepository.update(manager);
    }

    @Loggable
    @Transactional
    public void getCredentials() {

    }

    @Loggable
    @Transactional
    public ManagerDTORest getManagerDTORestById(Long id) {
        List<Manager> managers = getAllManagers();
        for (Manager manager : managers) {
            if (id == manager.getId()) {
                ManagerDTORest managerDTORest = managerMapper.mapToDTORestFrom(manager);
                return managerDTORest;
            }
        }
        return null;
    }



}
