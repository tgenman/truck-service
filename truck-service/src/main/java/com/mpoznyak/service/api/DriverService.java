package com.mpoznyak.service.api;

import com.mpoznyak.dto.DriverDTO;
import com.mpoznyak.dto.OrderDTO;
import com.mpoznyak.dto.RoutePointDTO;
import com.mpoznyak.dto.rest.DriverDTORest;
import com.mpoznyak.model.Driver;
import com.mpoznyak.model.RoutePoint;
import com.mpoznyak.validator.form.DriverForm;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 17:02
 */

public interface DriverService {

    void addDriverFromForm(DriverForm driverForm);

    void addDriver(DriverDTORest driverDTORest);

    void addDriver(DriverDTO driverDTO);

    List<Driver> getAllDrivers();

    List<RoutePoint> getRoutePointsForDriver(Driver driver);

    void deleteDriver(Long id);

    void updateDriver(DriverDTO driverDTO);

    LinkedHashMap<Long, Driver> getDriversForOrder(Long orderDuration, OrderDTO orderDTO);

    void updateDriver(Driver driver);

    void updateDriver(DriverDTORest driver);

    Driver getDriverForId(Long driverId);

    DriverDTORest getDriverDTORestById(Long id);

    void finishDriverOrder(Long driverId);

    void startDriverShift(Long driverId);

    void updateDriverStatus(Long driverId, Driver driverStatus);

    void updateRoutePoint(RoutePointDTO routePointDTO);

    List<DriverDTORest> getAllDriversDTO();
    Driver getAuthenticatedDriver();
}
