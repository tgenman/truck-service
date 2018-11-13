package com.mpoznyak.service;

import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.City;
import com.mpoznyak.repository.CityRepository;
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
}
