package com.mpoznyak.repository.api;

import com.mpoznyak.model.City;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 18:59
 */

public interface CityRepository {

    List<City> query();
}
