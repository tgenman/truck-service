package com.mpoznyak.service;

import com.mpoznyak.dto.rest.TempShiftDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.mapper.TempShiftMapper;
import com.mpoznyak.model.TempShift;
import com.mpoznyak.repository.api.TempShiftRepository;
import com.mpoznyak.service.api.TempShiftService;
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
public class TempShiftServiceImpl implements TempShiftService {

    @Autowired
    private TempShiftRepository tempShiftRepository;

    @Autowired
    private TempShiftMapper tempShiftMapper;

    @Override
    @Loggable
    @Transactional
    public void saveNewTempShift(TempShift tempShift) {
        tempShiftRepository.add(tempShift);
    }

    @Override
    @Loggable
    @Transactional
    public void updateTempShift(TempShift tempShift) {
        tempShiftRepository.update(tempShift);
    }

    @Override
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
