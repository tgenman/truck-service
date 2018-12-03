package com.mpoznyak.mapper;

import com.mpoznyak.dto.DriverDTO;
import com.mpoznyak.dto.TruckDTO;
import com.mpoznyak.dto.rest.DriverDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.City;
import com.mpoznyak.model.Driver;
import com.mpoznyak.model.Truck;
import com.mpoznyak.model.type.DriverStatus;
import com.mpoznyak.model.type.TruckStatus;
import com.mpoznyak.repository.CityRepository;
import com.mpoznyak.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Max Poznyak
 * on 24/10/2018  at 01:22
 */

@Component
public class DriverMapper {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TruckRepository truckRepository;

    @Loggable
    public Driver map(DriverDTO driverDTO) {
        Driver driver = new Driver();
        driver.setId(driverDTO.getId());
        driver.setFirstName(driverDTO.getFirstName());
        driver.setLastName(driverDTO.getLastName());
        if (driverDTO.getStatus() != null) {
            driver.setStatus(driverDTO.getStatus());
        } else {
            driver.setStatus(DriverStatus.FREE);
        }
        if (driverDTO.getTruck() != null) {
            Truck truck = mapToTruck(driverDTO.getTruck());
            driver.setTruck(truck);
        }
        if (driverDTO.getCityId() != null) {
            City city = mapToCity(driverDTO.getCityId());
            driver.setCity(city);
        }
        driver.setShift(driverDTO.getShift());

        return driver;
    }

    private DriverStatus mapToDriverStatus(String status) {
        switch (status) {
            case "WEEKLY_REST":
                return DriverStatus.WEEKLY_REST;
            case "SHIFT_REST":
                return DriverStatus.SHIFT_REST;
            case "CARGO_OPERATIONS":
                return DriverStatus.CARGO_OPERATIONS;
            case "DRIVER":
                return DriverStatus.DRIVER;
            case "SECOND_DRIVER":
                return DriverStatus.SECOND_DRIVER;
            case "FREE":
                return DriverStatus.FREE;
        }
        return null;
    }

    @Loggable
    private City mapToCity(Long cityId) {
        List<City> cities = cityRepository.query();

        for (City city : cities) {
            if (city.getId() == cityId) {
                return city;
            }
        }
        return null;
    }

    @Loggable
    private City mapToCity(String cityName) {
        List<City> cities = cityRepository.query();

        for (City city : cities) {
            if (city.getName().equals(cityName)) {
                return city;
            }
        }
        return null;
    }

    private Truck mapToTruck(String truckDTO) {
        List<Truck> trucks = truckRepository.query();

        //TODO update code for checking brand and model too
        for (Truck truck : trucks) {
            if (truckDTO.contains(truck.getLicensePlate())) {
                return truck;
            }
        }
        return null;
    }

    @Loggable
    public DriverDTORest mapToDTORestFrom(Driver driver) {

        DriverDTORest driverDTO = new DriverDTORest();
        driverDTO.setId(driver.getId());
        driverDTO.setStatus(driver.getStatus().toString());
        driverDTO.setLastName(driver.getLastName());
        driverDTO.setFirstName(driver.getFirstName());
        driverDTO.setCity(driver.getCity().getName());
        if (driver.getOrder() != null) {
            driverDTO.setOrder(driver.getOrder().getId());
        }
        if (driver.getTruck() != null) {
            driverDTO.setTruck(driver.getTruck().getId());
        }
        if (driver.getShift() != null) {
            driverDTO.setShift(driver.getShift().getId());
        }

        return driverDTO;
    }

    @Loggable
    public DriverDTO mapToDriverDTOFrom(DriverDTORest driverDTORest) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(driverDTORest.getId());
        driverDTO.setFirstName(driverDTORest.getFirstName());
        driverDTO.setLastName(driverDTORest.getLastName());

        City city = mapToCity(driverDTORest.getCity());
        driverDTO.setCityId(city.getId());
        return driverDTO;
    }
}


