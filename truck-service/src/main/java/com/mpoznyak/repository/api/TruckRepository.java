package com.mpoznyak.repository.api;

import com.mpoznyak.model.Truck;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 19:33
 */

public interface TruckRepository {

    void add(Truck truck);

    void add(Iterable<Truck> trucks);

    void remove(Truck truck);

    void remove(Long id);

    void update(Truck truck);

    List<Truck> query();
}
