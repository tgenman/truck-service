package com.mpoznyak.mapper;

import com.mpoznyak.dto.TruckDTO;
import com.mpoznyak.model.City;
import com.mpoznyak.model.Truck;
import com.mpoznyak.model.type.TruckStatus;
import com.mpoznyak.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 23:34
 */

@Component
public class TruckMapper {

    @Autowired
    CityRepository cityRepository;

    public Truck map(TruckDTO truckDTO) {
        Truck truck = new Truck();
        truck.setId(truckDTO.getId());
        truck.setBrand(truckDTO.getBrand());
        truck.setCapacity(truckDTO.getCapacity());
        truck.setLicensePlate(truckDTO.getLicensePlate());
        truck.setMaxDrivers(truckDTO.getMaxDrivers());
        truck.setModel(truckDTO.getModel());
        truck.setWorkingSession(truckDTO.getWorkingSession());

        TruckStatus status = mapToTruckStatus(truckDTO.getStatus());
        truck.setStatus(status);

        City city = mapToCity(truckDTO.getCity());
        truck.setCity(city);

        return truck;
    }

    private TruckStatus mapToTruckStatus(String status) {
        switch (status) {
            case "INOPERABLE":
                return TruckStatus.INOPERABLE;
            case "OPERABLE":
                return TruckStatus.OPERABLE;
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
}
