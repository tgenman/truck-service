package com.mpoznyak.service;

import com.mpoznyak.dto.rest.TempShiftDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.mapper.TempShiftMapper;
import com.mpoznyak.model.TempShift;
import com.mpoznyak.repository.TempShiftRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Max Poznyak
 * on 11/11/18  at 03:35
 */
@Service
public class TempShiftService {

    @Autowired
    private TempShiftRepository tempShiftRepository;

    @Autowired
    private TempShiftMapper tempShiftMapper;

    @Loggable
    @Transactional
    public void saveNewTempShift(TempShift tempShift) {
        tempShiftRepository.add(tempShift);
    }

    @Loggable
    @Transactional
    public void updateTempShift(TempShift tempShift) {
        tempShiftRepository.update(tempShift);
    }

    @Loggable
    @Transactional
    public List<TempShiftDTORest> getAllTempShiftsDTORest() {
        List<TempShift> tempShifts = tempShiftRepository.query();
        List<TempShiftDTORest> dtos = new ArrayList<>();

        for (TempShift tempShift : tempShifts) {
            TempShiftDTORest dto = tempShiftMapper.mapToDTORestFrom(tempShift);
            dtos.add(dto);
        }

        return dtos;
    }
}
