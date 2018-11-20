package com.mpoznyak.controller.rest;

import com.mpoznyak.dto.rest.ShiftDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.service.ShiftService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 16:42
 */

@RestController
@RequestMapping("/rest/shift")
public class ShiftControllerRest {

    @Autowired
    private ShiftService shiftService;

    @GetMapping("/list")
    @Loggable
    public List<ShiftDTORest> getAllShifts() {
        return shiftService.getAllShiftsDTORest();
    }
}
