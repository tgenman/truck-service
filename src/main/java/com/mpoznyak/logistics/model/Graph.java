package com.mpoznyak.logistics.model;

import com.mpoznyak.model.City;
import com.mpoznyak.model.Road;

import java.util.List;

/**
 * Created by Max Poznyak
 * on 30/10/2018  at 22:49
 */

public class Graph {

    protected final List<City> cities;
    protected final List<Road> roads;

    public Graph(List<City> cities, List<Road> roads) {
        this.cities = cities;
        this.roads = roads;
    }

    public List<City> getVertexes() {
        return cities;
    }

    public List<Road> getRoads() {
        return roads;
    }
}
