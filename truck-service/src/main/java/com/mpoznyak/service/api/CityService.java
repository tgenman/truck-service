package com.mpoznyak.service.api;

import com.mpoznyak.dto.rest.CityDTORest;
import com.mpoznyak.model.City;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 16:51
 */

public interface CityService {

    LinkedHashMap<Long, City> getAllCitiesMap();

    List<City> getAllCitiesList();

    List<CityDTORest> getAllCitiesDTORest();
}
