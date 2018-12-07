package com.mpoznyak.repository.api;

import com.mpoznyak.model.Driver;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 19:03
 */

public interface DriverRepository {

    void add(Driver driver);

    void add(Iterable<Driver> drivers);

    void remove(Driver driver);

    void remove(Long id);

    void update(Driver driver);

    List<Driver> query();

    List<Driver> queryExisted();


}
