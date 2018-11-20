package com.mpoznyak.mapper;

import com.mpoznyak.dto.rest.ShiftDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.Shift;
import org.springframework.stereotype.Component;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 16:44
 */

@Component
public class ShiftMapper {

    @Loggable
    public ShiftDTORest mapToDTORestFrom(Shift shift) {
        ShiftDTORest shiftDTORest = new ShiftDTORest();
        shiftDTORest.setId(shift.getId());
        shiftDTORest.setMonthEndAt(shift.getMonthStartAt().toString());
        shiftDTORest.setMonthStartAt(shift.getMonthStartAt().toString());
        shiftDTORest.setTimeMonthlyElapsed(shift.getTimeMonthlyElapsed());
        shiftDTORest.setTimeWeeklyElapsed(Long.valueOf(shift.getTimeWeeklyElapsed()));
        shiftDTORest.setWeeklyRest(shift.getWeeklyRest());
        return shiftDTORest;
    }
}
