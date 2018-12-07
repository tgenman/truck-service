package com.mpoznyak.service;

import com.mpoznyak.dto.rest.ShiftDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.mapper.ShiftMapper;
import com.mpoznyak.model.Shift;
import com.mpoznyak.repository.api.ShiftRepository;
import com.mpoznyak.service.api.ShiftService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 28/10/2018  at 01:48
 */

@Service
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private ShiftMapper shiftMapper;

    @Override
    @Loggable
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

    @Override
    @Loggable
    @Transactional
    public void setStartMonthShift(Shift shift) {
        shift.setTimeMonthlyElapsed(0L);
        shift.setMonthStartAt(LocalDateTime.now());
        shiftRepository.update(shift);
    }

    @Override
    @Loggable
    @Transactional
    public void setEndMonthShift(Shift shift) {
        LocalDateTime startDate = shift.getMonthStartAt();
        LocalDateTime endDate = startDate.toLocalDate()
                .with(TemporalAdjusters.lastDayOfMonth())
                .atTime(23,59,59);
        shift.setMonthEndAt(endDate);
        shiftRepository.update(shift);
    }

    @Override
    @Loggable
    @Transactional
    public void addShift(Shift shift) {
        shiftRepository.add(shift);
    }

    @Override
    @Loggable
    @Transactional
    public void updateShift(Shift shift) {
        shiftRepository.update(shift);
    }

    @Override
    @Loggable
    @Transactional
    public List<ShiftDTORest> getAllShiftsDTORest() {
        List<Shift> shifts = shiftRepository.query();
        List<ShiftDTORest> dtos = new ArrayList<>();

        for (Shift shift : shifts) {
            ShiftDTORest shiftDTORest = shiftMapper.mapToDTORestFrom(shift);
            dtos.add(shiftDTORest);
        }

        return dtos;
    }


}
