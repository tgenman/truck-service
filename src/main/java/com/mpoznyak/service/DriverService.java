package com.mpoznyak.service;

import com.mpoznyak.dto.DriverDTO;
import com.mpoznyak.dto.OrderDTO;
import com.mpoznyak.dto.RoutePointDTO;
import com.mpoznyak.dto.UserDTO;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.mapper.DriverMapper;
import com.mpoznyak.model.*;
import com.mpoznyak.model.type.DriverStatus;
import com.mpoznyak.model.type.OrderStatus;
import com.mpoznyak.model.type.Role;
import com.mpoznyak.repository.DriverRepository;
import com.mpoznyak.repository.ShiftRepository;
import com.mpoznyak.repository.UserRepository;
import com.mpoznyak.validator.form.DriverForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
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
    private UserService userService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RoutePointService routePointService;

    @Autowired
    private DriverMapper driverMapper;

    @Autowired TempShiftService tempShiftService;

    @Loggable
    @Transactional
    public void addDriverFromForm(DriverForm driverForm) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setFirstName(driverForm.getFirstName());
        driverDTO.setLastName(driverForm.getLastName());
        driverDTO.setCityId(driverForm.getCity());
        addDriver(driverDTO);
    }

    @Loggable
    @Transactional
    public void addDriver(DriverDTO driverDTO) {
        Shift shift = new Shift();
        shiftRepository.add(shift);
        shiftService.setStartMonthShift(shift);
        shiftService.setEndMonthShift(shift);
        driverDTO.setShift(shift);
        Driver driver = driverMapper.map(driverDTO);
        driverRepository.add(driver);
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword(driver.getFirstName());
        userDTO.setCompanyId("3000107"+ driver.getId());
        userDTO.setRole(Role.DRIVER.toString());
        userService.addNewUser(userDTO);
        User user = userService.findUserByCompanyId(String.valueOf("3000107" + driver.getId()));
        driver.setUser(user);
    }

    @Loggable
    public List<String> getAllDriverStatus() {
        List<String> driverStatusList = new ArrayList<>();
        for (DriverStatus driverStatus : DriverStatus.values()) {
            driverStatusList.add(driverStatus.name());
        }
        return driverStatusList;
    }

    @Loggable
    @Transactional
    public List<Driver> getAllDrivers() {
        return driverRepository.queryExisted();
    }

    @Loggable
    @Transactional
    public void deleteDriver(Long id) {
        driverRepository.remove(id);
    }

    @Loggable
    @Transactional
    public void updateDriver(DriverDTO driverDTO) {
        Driver driver = driverMapper.map(driverDTO);
        driverRepository.update(driver);
    }

    @Loggable
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
            Long monthTime = shift.getTimeMonthlyElapsed();
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

    @Loggable
    @Transactional
    public void updateDriver(Driver driver) {

        driverRepository.update(driver);
    }

    public Driver getDriverForId(Long driverId) {
        List<Driver> drivers = getAllDrivers();
        Driver driver = null;
        for (Driver driver1 : drivers) {
            if (driverId == driver1.getId()) {
                driver = driver1;
            }
        }
        return driver;
    }

    @Loggable
    @Transactional
    public void finishDriverOrder(Long driverId) {

        Driver driver = getDriverForId(driverId);
        if (driver != null) {
            Order order = driver.getOrder();
            order.setStatus(OrderStatus.COMPLETED);
            orderService.updateOrder(order);
            List<Driver> driversForOrder = order.getDrivers();
            List<RoutePoint> points = routePointService.getRoutePoints();
            for (RoutePoint point : points) {
                if (point.getOrder().getId() == order.getId()) {

                    routePointService.updatePointStatusForOrder(point);
                }
            }
            for (Driver driver1 : driversForOrder) {
                driver1.setOrder(null);
                driver1.setStatus(DriverStatus.FREE);
                Truck truck = driver1.getTruck();
                if (truck != null) {
                    truck.setFree(true);
                    truckService.updateTruck(truck);
                }
                driver1.setTruck(null);
                updateDriver(driver1);
            }

        }
    }

    @Loggable
    @Transactional
    public void startDriverShift(Long driverId) {

        Driver driver = getDriverForId(driverId);
        Order order = driver.getOrder();
        TempShift tempShift = order.getTempShift();
        Shift shift = driver.getShift();
        Long driverMonthTimeElapsed = shift.getTimeMonthlyElapsed();
        if (tempShift.getStartTempShift()) {
            tempShift.setStartTempShift(false);
            Long timeElapsed = Duration.between(tempShift.getStartDate(), LocalDateTime.now()).toHours();
            driverMonthTimeElapsed += timeElapsed;
            shift.setTimeMonthlyElapsed(driverMonthTimeElapsed);
            tempShift.setStartDate(null);
            shiftService.updateShift(shift);
            tempShiftService.updateTempShift(tempShift);
        } else {
            tempShift.setStartTempShift(true);
            tempShift.setStartDate(LocalDateTime.now());
            tempShiftService.updateTempShift(tempShift);
        }
    }

    @Loggable
    @Transactional
    public void updateDriverStatus(Long driverId, Driver driverStatus) {
        Driver driver = getDriverForId(driverId);
        driver.setStatus(driverStatus.getStatus());
        updateDriver(driver);
    }

    //TODO move to the another place
    public void updateRoutePoint(RoutePointDTO routePointDTO) {
        routePointService.updateRoutePoint(routePointDTO);
    }
}
