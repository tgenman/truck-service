package com.mpoznyak.service;

import com.mpoznyak.model.Driver;
import com.mpoznyak.model.Shift;
import com.mpoznyak.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Max Poznyak
 * on 28/10/2018  at 01:48
 */

@Service
public class ShiftService {

    @Autowired
    private DriverRepository driverRepository;


    public List<Driver> getDriversForOrder(Long orderDuration) {

        List<Driver> allDrivers = driverRepository.queryExisted();

        for (int i = 0; i < allDrivers.size(); i++) {

            Shift shift = allDrivers.get(i).getShift();
            if (shift.hasWeeklyRest()) {
                allDrivers.remove(i);
            } else if ((shift.getTimeWeeklyElapsed() + orderDuration) > 56
                    && (shift.getTimeMonthlyElapsed() + orderDuration) > 176) {
                allDrivers.remove(i);
            }
        }
        return allDrivers;
    }

    //TODO method for refreshing shift for each driver if 56 and 176 hours were expired

}
