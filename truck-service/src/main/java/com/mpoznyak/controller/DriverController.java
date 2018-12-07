package com.mpoznyak.controller;

import com.mpoznyak.dto.RoutePointDTO;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.Driver;
import com.mpoznyak.model.RoutePoint;
import com.mpoznyak.model.type.DriverStatus;
import com.mpoznyak.service.api.DriverService;
import com.mpoznyak.service.api.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 02:26
 */
@Controller
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private OrderService orderService;

    @Loggable
    @GetMapping("{name}")
    public String showDriver(@PathVariable("name") String name, Model model) {

        Driver driver = driverService.getAuthenticatedDriver();
        List<RoutePoint> points = driverService.getRoutePointsForDriver(driver);
        model.addAttribute("routePoints", points);
        model.addAttribute("driverStatus", DriverStatus.values());
        model.addAttribute("routePointDTO", new RoutePointDTO());
        model.addAttribute("driver", driver);
        return "driver";
    }

    @Loggable
    @PostMapping("/route-point-update")
    public String updateRoutePoint(@ModelAttribute("routePointDTO") RoutePointDTO routePointDTO,
                                   @RequestParam("driverId") Long driverId,
                                   @RequestParam("pointId") Long pointId,
                                   Model model) {

        routePointDTO.setId(pointId);
        routePointDTO.setCompleted(true);
        driverService.updateRoutePoint(routePointDTO);

        Long pointIds = pointId;
        Long driverIds = driverId;
        model.addAttribute("driverId", driverIds);
        model.addAttribute("pointId", pointIds);
        model.addAttribute("routePointDTO", new RoutePointDTO());
        return "redirect:driver";
    }

    @Loggable
    @PostMapping("/driver-status-update")
    public String updateDriverStatus(@ModelAttribute("driver") Driver driverStatus,
                                     @RequestParam("driverId") Long driverId,
                                     Model model) {

        driverService.updateDriverStatus(driverId, driverStatus);

        Long driverIds = driverId;
        model.addAttribute("driverId", driverIds);
        model.addAttribute("routePointDTO", new RoutePointDTO());
        return "redirect:driver";
    }

    @Loggable
    @GetMapping("driver")
    public String showDriverPage(@ModelAttribute("driverId") Long driverId,
                                 Model model) {

        Driver driver = driverService.getDriverForId(driverId);
        List<RoutePoint> points = driverService.getRoutePointsForDriver(driver);
        model.addAttribute("routePoints", points);
        model.addAttribute("routePointDTO", new RoutePointDTO());
        model.addAttribute("driverStatus", DriverStatus.values());
        model.addAttribute("driver", driver);
        return "driver";
    }

    @Loggable
    @PostMapping("finish-order")
    public String finishOrder(@RequestParam("driverId") long driverId, Model model) {

        driverService.finishDriverOrder(driverId);

        model.addAttribute("driverId", driverId);
        return "redirect:driver";
    }

    @Loggable
    @PostMapping("start-shift")
    public String startDriverShift(@RequestParam("driverId") Long driverId, Model model) {

        driverService.startDriverShift(driverId);
        Long driverIds = driverId;
        model.addAttribute("driverId", driverIds);
        model.addAttribute("routePointDTO", new RoutePointDTO());
        return "redirect:driver";
    }


}
