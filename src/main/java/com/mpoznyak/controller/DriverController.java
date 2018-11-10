package com.mpoznyak.controller;

import com.mpoznyak.dto.DriverDTO;
import com.mpoznyak.dto.RouteDTO;
import com.mpoznyak.dto.RoutePointDTO;
import com.mpoznyak.model.Driver;
import com.mpoznyak.model.Order;
import com.mpoznyak.model.RoutePoint;
import com.mpoznyak.model.Truck;
import com.mpoznyak.model.type.DriverStatus;
import com.mpoznyak.model.type.OrderStatus;
import com.mpoznyak.model.type.RoutePointType;
import com.mpoznyak.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 02:26
 */
@Controller
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CityService cityService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private RoutePointService routePointService;

    @RequestMapping(value = "/newDriver")
    public String showNewDriverPage(Model model) {
        model.addAttribute("driverDTO", new DriverDTO());
        model.addAttribute("cities", cityService.getAllCitiesMap());
        model.addAttribute("trucks", truckService.getTrucksDetails());
        model.addAttribute("status", driverService.getAllDriverStatus());
        return "new-driver";
    }

    @RequestMapping(value = "/processNewDriverData", method = RequestMethod.POST)
    public String processNewDriver(@ModelAttribute("driver") DriverDTO driverDTO) {
        driverService.addDriver(driverDTO);
        return "redirect:managerPage";
    }

    @RequestMapping(value = "update-driver", method = RequestMethod.POST)
    public String processUpdateDriverData(@ModelAttribute("driver") DriverDTO driverDTO) {
        driverService.updateDriver(driverDTO);
        return "redirect:managerPage";
    }

    @PostMapping("/route-point-update")
    public String updateRoutePoint(@ModelAttribute("routePointDTO") RoutePointDTO routePointDTO,
                                   @RequestParam("driverId") Long driverId,
                                   @RequestParam("pointId") Long pointId,
                                   Model model)  {
        routePointDTO.setId(pointId);
        routePointDTO.setCompleted(true);
        routePointService.updateRoutePoint(routePointDTO);

        Long pointIds = pointId;
        Long driverIds = driverId;
        model.addAttribute("driverId", driverIds);
        model.addAttribute("pointId", pointIds);
        model.addAttribute("routePointDTO", new RoutePointDTO());
        return "redirect:driver";
    }

    @PostMapping("/driver-status-update")
    public String updateDriverStatus(@ModelAttribute("driver") Driver driverStatus,
                                     @RequestParam("driverId") Long driverId,
                                     Model model)  {
        List<Driver> drivers = driverService.getAllDrivers();
        Driver driver = null;
        for (Driver driver1 : drivers) {
            if (driverId == driver1.getId()) {
                driver = driver1;
            }
        }
        driver.setStatus(driverStatus.getStatus());
        driverService.updateDriver(driver);

        Long driverIds = driverId;
        model.addAttribute("driverId", driverIds);
        model.addAttribute("routePointDTO", new RoutePointDTO());
        return "redirect:driver";
    }

    @GetMapping("driver")
    public String showDriverPage(@ModelAttribute("driverId") Long driverId,
                                 Model model) {

        List<Driver> drivers = driverService.getAllDrivers();
        Driver driver = null;
        for (Driver driver1 : drivers) {
            if (driverId == driver1.getId()) {
                driver = driver1;
            }
        }
        Order order = driver.getOrder();
        if (order != null) {
            List<RoutePoint> points = orderService.getRoutePointsForOrder(order);
            model.addAttribute("routePoints", points);
        } else {
            model.addAttribute("routePoints", new ArrayList<>());

        }

        List<String> names = driverService.getAllDriverStatus();

        model.addAttribute("routePointDTO", new RoutePointDTO());
        model.addAttribute("driverStatus", DriverStatus.values());
        model.addAttribute("driver", driver);
        return "driver";
    }

    @PostMapping("finish-order")
    public String finishOrder(@RequestParam("driverId") long driverId, Model model) {
        List<Driver> drivers = driverService.getAllDrivers();
        Driver driver = null;
        for (Driver driver1 : drivers) {
            if (driverId == driver1.getId()) {
                driver = driver1;
            }
        }
        if (driver != null) {
            Order order = driver.getOrder();
            order.setStatus(OrderStatus.COMPLETED);
            orderService.updateOrder(order);
            List<Driver> driversForOrder = order.getDrivers();
            List<RoutePoint> points = routePointService.getRoutePoints();
            for (RoutePoint point : points) {
                if (point.getOrder().getId() == order.getId()) {

                    routePointService.updatePointStatusForOrder(point);
                }
            }
            for (Driver driver1 : driversForOrder) {
                driver1.setOrder(null);
                driver1.setStatus(DriverStatus.FREE);
                Truck truck = driver1.getTruck();
                if (truck != null) {
                    truck.setFree(true);
                    truckService.updateTruck(truck);
                }
                driver1.setTruck(null);
                driverService.updateDriver(driver1);
            }

        }

        model.addAttribute("driverId", driverId);
        return "redirect:driver";

    }
}
