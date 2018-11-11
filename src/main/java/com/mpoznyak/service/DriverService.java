package com.mpoznyak.service;

import com.mpoznyak.dto.DriverDTO;
import com.mpoznyak.dto.OrderDTO;
import com.mpoznyak.mapper.DriverMapper;
import com.mpoznyak.model.Driver;
import com.mpoznyak.model.Shift;
import com.mpoznyak.model.Truck;
import com.mpoznyak.model.User;
import com.mpoznyak.model.type.DriverStatus;
import com.mpoznyak.model.type.Role;
import com.mpoznyak.repository.DriverRepository;
import com.mpoznyak.repository.ShiftRepository;
import com.mpoznyak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private ShiftRepository shiftRepository;

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TruckService truckService;

    @Autowired
    private DriverMapper driverMapper;

    @Transactional
    public void addDriver(DriverDTO driverDTO) {
        Shift shift = new Shift();
        shiftRepository.add(shift);
        shiftService.setStartMonthShift(shift);
        shiftService.setEndMonthShift(shift);
        driverDTO.setShift(shift);
        Driver driver = driverMapper.map(driverDTO);
        driverRepository.add(driver);
        User user = new User();
        user.setDriver(driver);
        user.setCompanyId(driver.getId());
        user.setPassword(driver.getId() + driver.getFirstName());
        user.setRole(Role.DRIVER);
        userRepository.add(user);
    }

    public List<String> getAllDriverStatus() {
        List<String> driverStatusList = new ArrayList<>();
        for (DriverStatus driverStatus : DriverStatus.values()) {
            driverStatusList.add(driverStatus.name());
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
    public LinkedHashMap<Long, Driver> getDriversForOrder(Long orderDuration, OrderDTO orderDTO) {

        List<Driver> allDrivers = driverRepository.queryExisted();
        LinkedHashMap<Long, Driver> map = new LinkedHashMap<>();
        List<Truck> trucks = truckService.getAllTrucks();
        Truck truck = null;

        for (Truck truck1 : trucks) {
            if (truck1.getId() == orderDTO.getTruck()) {
                truck = truck1;
                break;
            }
        }

        for (int i = 0; i < allDrivers.size(); i++) {

            Driver driver = allDrivers.get(i);
            Shift shift = allDrivers.get(i).getShift();
            LocalDateTime endDate = shift.getMonthEndAt();
            LocalDateTime orderDate = orderDTO.getDate();
            Integer monthTime = shift.getTimeMonthlyElapsed();
            if ((driver.getStatus().equals(DriverStatus.FREE) && (monthTime + orderDuration) <= 176)
                    || (!shift.hasWeeklyRest()
                    && orderDate.plusHours(orderDuration).isAfter(endDate)
                    && (monthTime + orderDuration <=176))) {

                if (176 - monthTime < truck.getWorkingSession()) {
                    continue;
                }

                if (driver.getCity().equals(truck.getCity())
                        && driver.getOrder() == null) {
                    map.put(driver.getId(), driver);
                }
            }
        }
        return map;
    }

    @Transactional
    public void updateDriver(Driver driver) {

        driverRepository.update(driver);
    }

}
