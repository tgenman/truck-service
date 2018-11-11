package com.mpoznyak.service;

import com.mpoznyak.model.TempShift;
import com.mpoznyak.repository.TempShiftRepository;
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

    @Transactional
    public void saveNewTempShift(TempShift tempShift) {
        tempShiftRepository.add(tempShift);
    }

    @Transactional
    public void updateTempShift(TempShift tempShift) {
        tempShiftRepository.update(tempShift);
    }
}
