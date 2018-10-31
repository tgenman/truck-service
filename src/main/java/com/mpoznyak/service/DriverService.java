package com.mpoznyak.service;

import com.mpoznyak.dto.DriverDTO;
import com.mpoznyak.mapper.DriverMapper;
import com.mpoznyak.model.Driver;
import com.mpoznyak.model.Shift;
import com.mpoznyak.model.type.DriverStatus;
import com.mpoznyak.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 02:33
 */

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private DriverMapper driverMapper;

    @Transactional
    public void addDriver(DriverDTO driverDTO) {
        Driver driver = driverMapper.map(driverDTO);
        driverRepository.add(driver);
    }

    public List<String> getAllDriverStatus() {
        List<String> driverStatusList = new ArrayList<>();
        for (DriverStatus truckStatus : DriverStatus.values()) {
            driverStatusList.add(truckStatus.name());
        }
        return driverStatusList;
    }

    @Transactional
    public List<Driver> getAllDrivers() {
        return driverRepository.queryExisted();
    }

    @Transactional
    public void deleteDriver(Long id) {
        driverRepository.remove(id);
    }

    @Transactional
    public void updateDriver(DriverDTO driverDTO) {
        Driver driver = driverMapper.map(driverDTO);
        driverRepository.update(driver);
    }

    @Transactional
    public LinkedHashMap<Long, Driver> getDriversForOrder(Integer orderDuration) {

        List<Driver> allDrivers = driverRepository.queryExisted();
        LinkedHashMap<Long, Driver> map = new LinkedHashMap<>();

        for (int i = 0; i < allDrivers.size(); i++) {

            Driver driver = allDrivers.get(i);
            Shift shift = allDrivers.get(i).getShift();
            Integer weekTime = shift.getTimeWeeklyElapsed();
            Integer monthTime = shift.getTimeMonthlyElapsed();
            if (shift.hasWeeklyRest() | ((weekTime + orderDuration) < 56
                    && (monthTime + orderDuration) < 176)) {
                map.put(driver.getId(), driver);
            }
        }
        return map;
    }

}
