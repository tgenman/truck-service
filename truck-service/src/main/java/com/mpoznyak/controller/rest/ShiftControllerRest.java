package com.mpoznyak.controller.rest;

import com.mpoznyak.dto.rest.ShiftDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.service.api.ShiftService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 16:42
 */

@RestController
@RequestMapping("/api/shift")
public class ShiftControllerRest {

    @Autowired
    private ShiftService shiftService;

    @GetMapping("/list")
    @Loggable
    @Secured("ROLE_ADMIN")
    public List<ShiftDTORest> getAllShifts() {
        return shiftService.getAllShiftsDTORest();
    }
}
