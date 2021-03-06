package com.mpoznyak.controller.rest;

import com.mpoznyak.dto.rest.TempShiftDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.service.api.TempShiftService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 16:54
 */

@RestController
@RequestMapping("/api/temp-shift")
public class TempShiftControllerRest {

    @Autowired
    private TempShiftService tempShiftService;

    @GetMapping("/list")
    @Loggable
    @Secured("ROLE_ADMIN")
    public List<TempShiftDTORest> getAllTempShifts() {
        return tempShiftService.getAllTempShiftsDTORest();
    }
}
