package com.mpoznyak.service.api;

import com.mpoznyak.dto.rest.TempShiftDTORest;
import com.mpoznyak.model.TempShift;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 17:39
 */
public interface TempShiftService {

    void saveNewTempShift(TempShift tempShift);

    void updateTempShift(TempShift tempShift);

    List<TempShiftDTORest> getAllTempShiftsDTORest();
}
