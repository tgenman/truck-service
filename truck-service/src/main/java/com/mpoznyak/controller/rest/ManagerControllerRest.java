package com.mpoznyak.controller.rest;

import com.mpoznyak.dto.rest.ManagerDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.service.api.ManagerService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 02:26
 */
@RestController
@RequestMapping("/api/manager")
public class ManagerControllerRest {

    private static final Logger log = Logger.getLogger(ManagerControllerRest.class);

    @Autowired
    private ManagerService managerService;

    @Loggable
    @GetMapping("/list")
    @Secured("ROLE_ADMIN")
    public List<ManagerDTORest> getAllDrivers() {
        return managerService.getAllManagersDTORest();
    }


    @Loggable
    @PutMapping("/new")
    @Secured("ROLE_ADMIN")
    public ResponseEntity addNewTruck(@RequestBody ManagerDTORest managerDTORest) {
        managerService.addNewManager(managerDTORest);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Loggable
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteDriver(@PathVariable Long id) {
        log.info("REQUEST FOR DELETING MANAGER WITH ID: " + id);
        managerService.deleteManager(id);
    }

    @Loggable
    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ManagerDTORest getDriverById(@PathVariable Long id) {
        return managerService.getManagerDTORestById(id);
    }

    @Loggable
    @PostMapping("/update")
    @Secured("ROLE_ADMIN")
    public void updateDriver(@RequestBody ManagerDTORest managerDTORest) {
        managerService.updateManager(managerDTORest);
    }

}
