package com.mpoznyak.mapper;

import com.mpoznyak.dto.DriverDTO;
import com.mpoznyak.dto.TruckDTO;
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

    @Autowired
    public DriverMapper() {
    }

    public Driver map(DriverDTO driverDTO) {
        Driver driver = new Driver();
        driver.setId(driverDTO.getId());
        driver.setFirstName(driverDTO.getFirstName());
        driver.setLastName(driverDTO.getLastName());
        driver.setWorkedTime(driverDTO.getWorkedTime());

        DriverStatus status = mapToDriverStatus(driverDTO.getStatus());
        driver.setStatus(status);

        Truck truck = mapToTruck(driverDTO.getTruck());
        driver.setTruck(truck);

        City city = mapToCity(driverDTO.getCity());
        driver.setCity(city);

        return driver;
    }

    private DriverStatus mapToDriverStatus(String status) {
        switch (status) {
            case "REST":
                return DriverStatus.REST;
            case "WORKING_SESSION_WAIT":
                return DriverStatus.WORKING_SESSION_DRIVE;
            case "WORKING_SESSION_DRIVE":
                return DriverStatus.WORKING_SESSION_DRIVE;
        }
        return null;
    }

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
}


