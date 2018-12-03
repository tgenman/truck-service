package com.mpoznyak.mapper;

import com.mpoznyak.dto.rest.CityDTORest;
import com.mpoznyak.dto.rest.CustomerDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.City;
import com.mpoznyak.model.Customer;
import org.springframework.stereotype.Component;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 15:03
 */

@Component
public class CItyMapper {

    @Loggable
    public CityDTORest mapToDTORestFrom(City city) {
        CityDTORest dto = new CityDTORest();

        dto.setId(city.getId());
        dto.setName(city.getName());
        dto.setLatitude(city.getLatitude());
        dto.setLongtitude(city.getLongtitude());
        return dto;
    }
}
