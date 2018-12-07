package com.mpoznyak.service.api;

import com.mpoznyak.dto.rest.ManagerDTORest;
import com.mpoznyak.model.Manager;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 17:18
 */

public interface ManagerService {

    void addNewManager(Manager manager);

    void addNewManager(ManagerDTORest managerDTORest);

    void deleteManager(Long id);

    List<Manager> getAllManagers();

    List<ManagerDTORest> getAllManagersDTORest();

    void updateManager(ManagerDTORest managerDTORest);

    void getCredentials();

    ManagerDTORest getManagerDTORestById(Long id);
}
