package com.mpoznyak.repository.api;

import com.mpoznyak.model.RoutePoint;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 19:27
 */
public interface RoutePointRepository {

    void add(RoutePoint routePoint);

    void add(Iterable<RoutePoint> routePoints);

    void remove(RoutePoint routePoint);

    void remove(Long id);

    void removeFull(RoutePoint routePoint);

    List<RoutePoint> query();

    List<RoutePoint> queryExisted();

    void update(RoutePoint routePoint);
}
