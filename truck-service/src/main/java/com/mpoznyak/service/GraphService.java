package com.mpoznyak.service;

import com.mpoznyak.dto.OrderDTO;
import com.mpoznyak.dto.RouteDTO;
import com.mpoznyak.dto.RoutePointDTO;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.logistics.algorithm.DijkstraAlgorithm;
import com.mpoznyak.logistics.model.Graph;
import com.mpoznyak.model.City;
import com.mpoznyak.model.Road;
import com.mpoznyak.model.Truck;
import com.mpoznyak.model.type.RoutePointType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Max Poznyak
 * on 31/10/2018  at 00:19
 */

@Service
public class GraphService {

    private static final String TAG = GraphService.class.getSimpleName();

    private static final Logger logger = Logger.getLogger(GraphService.class);

    @Autowired
    private CityService cityService;

    @Autowired
    private RoadService roadService;

    @Autowired
    private TruckService truckService;


    @Loggable
    public LinkedList<City> getPath(Graph graph, int from, int to) {
        DijkstraAlgorithm algorithm = new DijkstraAlgorithm(graph);
        algorithm.execute(graph.getVertexes().get(from));
        List<City> cities = graph.getVertexes();
        City city = cities.get(to);
        return algorithm.getPath(city);
    }


    @Loggable
    public List<City> getGraphVertices() {
        List<City> cities = cityService.getAllCitiesList();
        return cities;
    }

    @Loggable
    public List<Road> getGraphEdges() {
        List<Road> roads = roadService.getRoads();
        return roads;
    }

    @Loggable
    private ArrayDeque<LinkedList<City>> buildRoute(RouteDTO theRoute, OrderDTO orderDTO) {

        logger.info("GraphService.class: called buildRoute(args...) ["
                + " routePoints list size = " + theRoute.getRoutePoints().size()
                + ", truck Id = " + orderDTO.getTruck()
                + "]");

        //insert car from orderDTO as first location
        Truck truck = new Truck();
        truck.setId(orderDTO.getTruck());
        List<Truck> trucks = truckService.getAllTrucks();

        int carCityIndex = trucks.indexOf(truck);
        City carCity = trucks.get(carCityIndex).getCity();

        List<RoutePointDTO> routePoints = theRoute.getRoutePoints();
        List<City> cities = getGraphVertices();
        List<Road> roads = getGraphEdges();
        Graph graph = new Graph(cities, roads);
        ArrayDeque<LinkedList<City>> path = new ArrayDeque<>();

        RoutePointDTO carPoint = new RoutePointDTO();
        carPoint.setCityId(carCity.getId());
        carPoint.setType(RoutePointType.CAR_LOCATION);
        routePoints.add(0, carPoint);

        int i = 0;
        for (int j = 0; j < routePoints.size(); j++) {

            RoutePointDTO startPoint = routePoints.get(j);
            City from = new City();
            from.setId(startPoint.getCityId());
            City to = new City();
            RoutePointDTO endPoint;

            if ((j + 1) < routePoints.size()) {
                endPoint = routePoints.get(j + 1);
                to.setId(endPoint.getCityId());
            } else {
                break;
            }


            int indexFrom = cities.indexOf(from);
            int indexTo = cities.indexOf(to);
            LinkedList<City> $path = getPath(graph, indexFrom, indexTo);
            path.addLast($path);

        }
        return path;
    }

    @Loggable
    private Long calculateRouteDistance(ArrayDeque<LinkedList<City>> path) {

        Long distance = 0L;
        List<Road> roads = getGraphEdges();
        for (LinkedList<City> cities : path) {

            Iterator iterator = cities.iterator();
            City to = null;
            City from = cities.get(0);

            //to get element with 1 index
            iterator.next();

            while (iterator.hasNext()) {
                to = (City) iterator.next();

                if (from.equals(to)) {
                    continue;
                }
                for (Road road : roads) {
                    if (road.getFrom().equals(from)
                            && road.getTo().equals(to)) {
                        distance += road.getDistance();
                    }
                }

                if (iterator.hasNext()) {
                    from = to;
                } else {
                    break;
                }
            }

        }
        return distance;
    }


    @Loggable
    private Long calculateRouteTime(Long distance, RouteDTO routeDTO) {

        //average speed 50 mph/h
        //average time per route point - 1 h

        long routeTime = 0L;

        //minus 1 because of 1 car initial route point

        int countRoutePoints = routeDTO.getRoutePoints().size() - 1;
        long timeOnRoads = distance / 50;
        long timeOnPoints = (long) countRoutePoints;

        routeTime = timeOnPoints + timeOnRoads;
        return routeTime;
    }

    @Loggable
    public Long getRouteTime(RouteDTO routeDTO, OrderDTO orderDTO) {
        ArrayDeque<LinkedList<City>> path = buildRoute(routeDTO, orderDTO);
        Long distance = calculateRouteDistance(path);
        logger.info(TAG + ": distance = " + distance);
        Long time = calculateRouteTime(distance, routeDTO);
        logger.info(TAG + ": time = " + time);
        return time;

    }


}
