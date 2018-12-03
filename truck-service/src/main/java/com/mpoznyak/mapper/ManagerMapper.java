package com.mpoznyak.mapper;

import com.mpoznyak.dto.rest.CargoDTORest;
import com.mpoznyak.dto.rest.ManagerDTORest;
import com.mpoznyak.model.Cargo;
import com.mpoznyak.model.Manager;
import org.springframework.stereotype.Component;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 14:33
 */

@Component
public class ManagerMapper {

    public Manager map(ManagerDTORest managerDTORest) {
        Manager manager = new Manager();
        if (managerDTORest.getId() != null)
            manager.setId(managerDTORest.getId());
        manager.setFirstName(managerDTORest.getFirstName());
        manager.setLastName(managerDTORest.getLastName());
        return manager;
    }

    public ManagerDTORest mapToDTORestFrom(Manager manager) {
        ManagerDTORest dto = new ManagerDTORest();
        dto.setId(manager.getId());
        dto.setFirstName(manager.getFirstName());
        dto.setLastName(manager.getLastName());
        dto.setUserId(manager.getUser().getId());
        return dto;
    }
}
