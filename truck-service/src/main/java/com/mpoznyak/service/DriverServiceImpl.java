package com.mpoznyak.service;

import com.mpoznyak.dto.DriverDTO;
import com.mpoznyak.dto.OrderDTO;
import com.mpoznyak.dto.RoutePointDTO;
import com.mpoznyak.dto.UserDTO;
import com.mpoznyak.dto.rest.DriverDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.mapper.DriverMapper;
import com.mpoznyak.model.Driver;
import com.mpoznyak.model.Order;
import com.mpoznyak.model.RoutePoint;
import com.mpoznyak.model.Shift;
import com.mpoznyak.model.TempShift;
import com.mpoznyak.model.Truck;
import com.mpoznyak.model.User;
import com.mpoznyak.model.type.DriverStatus;
import com.mpoznyak.model.type.OrderStatus;
import com.mpoznyak.model.type.Role;
import com.mpoznyak.repository.api.DriverRepository;
import com.mpoznyak.repository.api.ShiftRepository;
import com.mpoznyak.service.api.DriverService;
import com.mpoznyak.service.api.MessageEmitter;
import com.mpoznyak.service.api.OrderService;
import com.mpoznyak.service.api.RoutePointService;
import com.mpoznyak.service.api.ShiftService;
import com.mpoznyak.service.api.TempShiftService;
import com.mpoznyak.service.api.TruckService;
import com.mpoznyak.service.api.UserService;
import com.mpoznyak.validator.form.DriverForm;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 02:33
 */

@Service
public class DriverServiceImpl implements DriverService {

    private static final Logger logger = Logger.getLogger(DriverServiceImpl.class);

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

    @Autowired
    private TempShiftService tempShiftService;

    @Autowired
    private MessageEmitter messageEmitter;

    @Override
    @Loggable
    @Transactional
    public void addDriverFromForm(DriverForm driverForm) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setFirstName(driverForm.getFirstName());
        driverDTO.setLastName(driverForm.getLastName());
        driverDTO.setCityId(driverForm.getCity());
        driverDTO.setUsername(driverForm.getUsername());
        driverDTO.setPassword(driverForm.getPassword());
        addDriver(driverDTO);
    }

    @Override
    @Loggable
    @Transactional
    public void addDriver(DriverDTORest driverDTORest) {
        DriverDTO driverDTO = driverMapper.mapToDriverDTOFrom(driverDTORest);
        addDriver(driverDTO);
    }

    @Override
    @Loggable
    @Transactional
    public Driver getAuthenticatedDriver() {
        org.springframework.security.core.userdetails.User user = userService.getAuthenticatedUser();
        List<Driver> drivers = getAllDrivers();
        for (Driver driver : drivers) {
            if (user.getUsername().equals(driver.getUser().getCompanyId())) {
                return driver;
            }
        }
        return null;
    }

    @Override
    @Loggable
    public List<RoutePoint> getRoutePointsForDriver(Driver driver) {
        Order order = driver.getOrder();
        if (order != null) {
            return orderService.getRoutePointsForOrder(order);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
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
        userDTO.setPassword(driverDTO.getPassword());
        userDTO.setCompanyId(driverDTO.getUsername());
        userDTO.setRole(Role.DRIVER.toString());
        userService.addNewUser(userDTO);
        User user = userService.findUserByCompanyId(String.valueOf(driverDTO.getUsername()));
        driver.setUser(user);

        messageEmitter.produceMessage("Created a new driver");

    }

    @Override
    @Loggable
    @Transactional
    public List<Driver> getAllDrivers() {
        return driverRepository.queryExisted();
    }

    @Override
    @Loggable
    @Transactional
    public void deleteDriver(Long id) {
        driverRepository.remove(id);
        messageEmitter.produceMessage("Deleted a driver", id);
    }

    @Override
    @Loggable
    @Transactional
    public void updateDriver(DriverDTO driverDTO) {
        Driver driver = driverMapper.map(driverDTO);
        driverRepository.update(driver);
        messageEmitter.produceMessage("Update a driver", driver.getId());
    }

    @Override
    @Loggable
    @Transactional
    public LinkedHashMap<Long, Driver> getDriversForOrder(Long orderDuration, OrderDTO orderDTO) {

        List<Driver> allDrivers = driverRepository.queryExisted();
        logger.info("getDriversForOrder: orderDuration: " + orderDuration);
        logger.info("getDriversForOrder: allDrivers" + allDrivers);
        LinkedHashMap<Long, Driver> map = new LinkedHashMap<>();
        List<Truck> trucks = truckService.getAllTrucks();
        Truck truck = null;

        for (Truck truck1 : trucks) {
            if (truck1.getId() == orderDTO.getTruck()) {
                truck = truck1;
                break;
            }
        }
        logger.info("getDriversForOrder: truck license and city" + truck.getLicensePlate() + " " + truck.getCity());

        int countDrivers = 0;
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

                logger.info("driver get city: " + driver.getCity().getName() + " truck getcity"
                        + truck.getCity().getName() + " driver get order: " + driver.getOrder());
                if (driver.getCity().equals(truck.getCity())
                        && driver.getOrder() == null) {
                    logger.info("getDriversForOrder: map put driver" + driver.getFirstName() + driver.getLastName());
                    if (truck.getMaxDrivers() == countDrivers) {
                        break;
                    }
                    map.put(driver.getId(), driver);
                    countDrivers++;
                }
            }
        }
        return map;
    }

    @Override
    @Loggable
    @Transactional
    public void updateDriver(Driver driver) {
        driverRepository.update(driver);
        messageEmitter.produceMessage("Update a driver", driver.getId());
    }

    @Override
    @Loggable
    @Transactional
    public void updateDriver(DriverDTORest driver) {
        DriverDTO driver1 = driverMapper.mapToDriverDTOFrom(driver);
        updateDriver(driver1);
    }

    @Override
    @Loggable
    @Transactional
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

    @Override
    @Loggable
    @Transactional
    public DriverDTORest getDriverDTORestById(Long id) {
        Driver driver = getDriverForId(id);
        DriverDTORest dto = driverMapper.mapToDTORestFrom(driver);
        return dto;
    }

    @Override
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

    @Override
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

    @Override
    @Loggable
    @Transactional
    public void updateDriverStatus(Long driverId, Driver driverStatus) {
        Driver driver = getDriverForId(driverId);
        driver.setStatus(driverStatus.getStatus());
        updateDriver(driver);
    }

    @Override
    @Loggable
    public void updateRoutePoint(RoutePointDTO routePointDTO) {
        routePointService.updateRoutePoint(routePointDTO);
    }

    @Override
    @Loggable
    @Transactional
    public List<DriverDTORest> getAllDriversDTO() {
        List<Driver> driverEntities = driverRepository.queryExisted();
        List<DriverDTORest> driverDTOS = new ArrayList<>();

        for (Driver driverEntity : driverEntities) {
            DriverDTORest dto = driverMapper.mapToDTORestFrom(driverEntity);
            driverDTOS.add(dto);
        }
        return driverDTOS;

    }
}
