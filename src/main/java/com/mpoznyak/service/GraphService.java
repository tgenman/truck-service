package com.mpoznyak.service;

import com.mpoznyak.logistics.algorithm.DijkstraAlgorithm;
import com.mpoznyak.logistics.model.Graph;
import com.mpoznyak.model.City;
import com.mpoznyak.model.Road;
import com.mpoznyak.model.RoutePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 31/10/2018  at 00:19
 */

@Service
public class GraphService {

    @Autowired
    private CityService cityService;

    @Autowired
    private RoadService roadService;


    public LinkedList<City> getPath(Graph graph, int from, int to) {
        DijkstraAlgorithm algorithm = new DijkstraAlgorithm(graph);
        algorithm.execute(graph.getVertexes().get(from));
        List<City> cities = graph.getVertexes();
        City city = cities.get(to);
        return algorithm.getPath(city);
    }


    public List<City> getGraphVertices() {
        List<City> cities = cityService.getAllCitiesList();
        return cities;
    }

    public List<Road> getGraphEdges() {
        List<Road> roads = roadService.getRoads();

        return roads;
    }
}
