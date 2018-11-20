package com.mpoznyak.mapper;

import com.mpoznyak.dto.rest.TempShiftDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.TempShift;
import org.springframework.stereotype.Component;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 16:59
 */

@Component
public class TempShiftMapper {

    @Loggable
    public TempShiftDTORest mapToDTORestFrom(TempShift tempShift) {
        TempShiftDTORest dto = new TempShiftDTORest();
        dto.setId(tempShift.getId());
        if (tempShift.getStartDate() != null) {
            dto.setStartDate(tempShift.getStartDate().toString());
        }
        dto.setStartTempShift(tempShift.getStartTempShift());

        return dto;
    }
}
