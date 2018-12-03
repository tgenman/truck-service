package com.mpoznyak.service;

import com.mpoznyak.dto.rest.CityDTORest;
import com.mpoznyak.dto.rest.DriverDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.mapper.CItyMapper;
import com.mpoznyak.model.City;
import com.mpoznyak.model.Driver;
import com.mpoznyak.repository.CityRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 19:37
 */

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CItyMapper cItyMapper;

    @Loggable
    @Transactional
    public LinkedHashMap<Long, City> getAllCitiesMap() {
        List<City> cityList = cityRepository.query();
        LinkedHashMap<Long, City>  cityMap = new LinkedHashMap<>();
        for (City city : cityList) {
            cityMap.put(city.getId(), city);
        }
        return cityMap;
    }

    @Loggable
    @Transactional
    public List<City> getAllCitiesList() {
        return cityRepository.query();
    }

    @Loggable
    @Transactional
    public List<CityDTORest> getAllCitiesDTORest() {
        List<City> cities = cityRepository.query();
        List<CityDTORest> dtos = new ArrayList<>();

        for (City city : cities) {
            CityDTORest dto = cItyMapper.mapToDTORestFrom(city);
            dtos.add(dto);
        }
        return dtos;
    }
}
