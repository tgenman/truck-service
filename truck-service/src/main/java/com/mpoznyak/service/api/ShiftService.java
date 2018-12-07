package com.mpoznyak.service.api;

import com.mpoznyak.dto.rest.ShiftDTORest;
import com.mpoznyak.model.Shift;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 17:37
 */
public interface ShiftService {

    void newWeekShift(Shift shift);

    void setStartMonthShift(Shift shift);

    void setEndMonthShift(Shift shift);

    void addShift(Shift shift);

    void updateShift(Shift shift);

    List<ShiftDTORest> getAllShiftsDTORest();
}
