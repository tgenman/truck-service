package com.mpoznyak.service.api;

import com.mpoznyak.dto.OrderDTO;
import com.mpoznyak.dto.RouteDTO;
import com.mpoznyak.dto.RoutePointDTO;
import com.mpoznyak.dto.rest.OrderDTORest;
import com.mpoznyak.model.Cargo;
import com.mpoznyak.model.City;
import com.mpoznyak.model.Driver;
import com.mpoznyak.model.Order;
import com.mpoznyak.model.RoutePoint;
import com.mpoznyak.model.Truck;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 17:24
 */
public interface OrderService {

    Long getRouteTime(RouteDTO routeDTO, OrderDTO orderDTO);

    LinkedHashMap<Long, Driver> getDriversForOrder(Long time, OrderDTO orderDTO);

    LinkedHashMap<Long, Truck> getTrucksForOrder(Long weight);

    void setDropLocations(RouteDTO routeDTO, List<RoutePointDTO> points);

    Long getWeight(RoutePointDTO routePointDTO, List<RoutePointDTO> points, Long weight);

    void newRoutePoint(RoutePointDTO routePointDTO, RouteDTO routeDTO, List<RoutePointDTO> points);

    LinkedHashMap<Long, City> getAllCitiesMap();

    void saveOrder(RouteDTO routeDTO, OrderDTO orderDTO);

    List<OrderDTO> getAllOrdersDTO();

    List<RoutePoint> getRoutePointsForOrder(Order order);

    List<Cargo> getCargoesForOrder(OrderDTO orderDTO);

    void deleteOrder(Long id);

    void updateOrder(Order order);

    List<OrderDTORest> getAllOrdersDTORest();

    void deleteRoutePoint(String routePoint, RouteDTO routeDTO);
}
