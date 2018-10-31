package com.mpoznyak.service;

import com.mpoznyak.model.Driver;
import com.mpoznyak.model.Shift;
import com.mpoznyak.repository.DriverRepository;
import com.mpoznyak.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 28/10/2018  at 01:48
 */

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    @Transactional
    public void newWeekShift(Shift shift) {
        shift.setTimeWeeklyElapsed(0);
        if (shift.getWeekCounter() > 3) {
            shift.setWeekCounter(1);
        } else {
            shift.setWeekCounter(shift.getWeekCounter() + 1);
        }

        shiftRepository.update(shift);
    }

    @Transactional
    public void newMonthShift(Shift shift) {
        shift.setTimeMonthlyElapsed(0);
        shift.setMonthStartAt(LocalDate.now());
        shift.setMonthEndAt(shift.getMonthStartAt().plusMonths(1));
        shiftRepository.update(shift);
    }

    @Transactional
    public void addShift(Shift shift) {
        shiftRepository.add(shift);
    }


}
