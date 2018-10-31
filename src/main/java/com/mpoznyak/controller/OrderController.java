package com.mpoznyak.controller;

import com.mpoznyak.dto.OrderDTO;
import com.mpoznyak.dto.RouteDTO;
import com.mpoznyak.dto.RoutePointDTO;
import com.mpoznyak.logistics.algorithm.DijkstraAlgorithm;
import com.mpoznyak.logistics.model.Graph;
import com.mpoznyak.model.City;
import com.mpoznyak.model.Road;
import com.mpoznyak.model.RoutePoint;
import com.mpoznyak.model.Truck;
import com.mpoznyak.model.type.RoutePointType;
import com.mpoznyak.service.CityService;
import com.mpoznyak.service.DriverService;
import com.mpoznyak.service.GraphService;
import com.mpoznyak.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Max Poznyak
 * on 26/10/2018  at 21:07
 */

@Controller
@SessionAttributes({"routeDTO", "orderDTO"})
public class OrderController {

    @Autowired
    private CityService cityService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private GraphService graphService;

    @Autowired
    private TruckService truckService;

    @ModelAttribute("routeDTO")
    public RouteDTO getRouteDTO() {
        return new RouteDTO();
    }

    @ModelAttribute("orderDTO")
    public OrderDTO getOrderDTO() {
        return new OrderDTO();
    }


    //TODO implement
    @PostMapping(value = "save-order")
    public String saveOrder(@ModelAttribute("routeDTO") RouteDTO routeDTO,
                            @ModelAttribute("orderDTO") OrderDTO orderDTO) {



        return "manager";
    }

    @PostMapping(value = "verify-order")
    public String verifyOrder(@ModelAttribute("routeDTO") RouteDTO routeDTO,
                            @ModelAttribute("point") RoutePointDTO point,
                            @ModelAttribute("orderDTO") OrderDTO orderDTO,
                            Model model) {

        List<RoutePointDTO> routePoints = routeDTO.getRoutePoints();
        LinkedHashMap<Integer, LinkedList<City>> map = new LinkedHashMap<>();
        List<City> cities = graphService.getGraphVertices();
        List<Road> roads = graphService.getGraphEdges();

        Graph graph = new Graph(cities,roads);

        int i = 0;
        for (int j = 0; j < routePoints.size(); j++) {

            RoutePointDTO routePoint1 = routePoints.get(j);
            routePoint1.setRouteSequenceIndex(i++);
            City from = new City();
            from.setId(routePoint1.getCityId());
            City to = new City();

            //TODO to service and implement and implement logic for the same city delivery
            //TODO first point depends on truck position
            //TODO Abort order
            if ((j+1) < routePoints.size()) {
                RoutePointDTO routePoint2 = routePoints.get(j+1);
                routePoint2.setRouteSequenceIndex(i++);
                to.setId(routePoint2.getCityId());
            } else {
                break;
            }

            map.put(routePoint1.getRouteSequenceIndex(),graphService.getPath(graph,cities.indexOf(from),cities.indexOf(to)));

        }

        //TODO optimize the route and pass it
        model.addAttribute("routeDTO", routeDTO);
        //TODO analyze trucks and drivers

        model.addAttribute("drivers", driverService.getDriversForOrder(10));
        model.addAttribute("trucks", truckService.getTrucksForOrder(15));

        model.addAttribute("orderDTO", orderDTO);
        return "order-confirmation";

    }

    @GetMapping(value = "/new-order")
    public String showOrderFirstPage(@ModelAttribute("routeDTO") RouteDTO routeDTO,
                                 @ModelAttribute("orderDTO") OrderDTO orderDTO,
                                 Model model) {
        model.addAttribute("route-point", new RoutePointDTO());
        model.addAttribute("cities", cityService.getAllCitiesMap());
        return "order-start";
    }

    @PostMapping(value = "/order-main")
    public String showOrderMainPage(@ModelAttribute("routeDTO") RouteDTO routeDTO,
                                @ModelAttribute("route-point") RoutePointDTO routePointDTO,
                                @ModelAttribute("orderDTO") OrderDTO orderDTO,
                                Model model) {
        orderDTO.setCustomer(routePointDTO.getCustomerName());
        routeDTO.addRoutePoint(routePointDTO);
        model.addAttribute("route-point", new RoutePointDTO());
        model.addAttribute("order", new OrderDTO());
        model.addAttribute("drivers", driverService.getDriversForOrder(10));
        model.addAttribute("cities", cityService.getAllCitiesMap());
        return "order-main";
    }

    @PostMapping(value = "/order-new-point")
    public String addNewPointToOrder(@ModelAttribute("routeDTO") RouteDTO routeDTO,
                                @ModelAttribute("route-point") RoutePointDTO routePointDTO,
                                @ModelAttribute("orderDTO") OrderDTO orderDTO,
                                Model model) {
        model.addAttribute("route-point", new RoutePointDTO());
        model.addAttribute("order", new OrderDTO());

        model.addAttribute("cities", cityService.getAllCitiesMap());
        return "order-main";
    }

    @PostMapping(value = "/orderTruck")
    public String selectTruck(@ModelAttribute("routeDTO") RouteDTO routeDTO,
                              @ModelAttribute("route-point") RoutePointDTO routePointDTO,
                              @ModelAttribute("orderDTO") OrderDTO orderDTO,
                              Model model) {

        Integer weight = 0;

        routePointDTO.setType(RoutePointType.DROP_OFF);
        routeDTO.addRoutePoint(routePointDTO);

        List<RoutePointDTO> routePoints = routeDTO.getRoutePoints();

        for (RoutePointDTO routePoint : routePoints) {
            if (routePoint.getType() != RoutePointType.DROP_OFF) {
                weight += routePoint.getCargoDTO().getWeight();
            }
        }


        model.addAttribute("trucks", truckService.getTrucksForOrder(weight));
        model.addAttribute("order", new OrderDTO());
        return "order-truck";
    }


}
