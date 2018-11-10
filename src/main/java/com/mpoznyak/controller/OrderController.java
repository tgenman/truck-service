package com.mpoznyak.controller;

import com.mpoznyak.dto.CargoDTO;
import com.mpoznyak.dto.OrderDTO;
import com.mpoznyak.dto.RouteDTO;
import com.mpoznyak.dto.RoutePointDTO;
import com.mpoznyak.model.Cargo;
import com.mpoznyak.model.Driver;
import com.mpoznyak.model.RoutePoint;
import com.mpoznyak.model.type.RoutePointType;
import com.mpoznyak.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.*;

/**
 * Created by Max Poznyak
 * on 26/10/2018  at 21:07
 */

@Controller
@SessionAttributes({"routeDTO", "orderDTO", "cargoes"})
public class OrderController {

    private static final Logger logger = Logger.getLogger(OrderController.class);
    private static final String TAG = OrderController.class.getSimpleName();

    @Autowired
    private OrderService orderService;

    @ModelAttribute("routeDTO")
    public RouteDTO getRouteDTO() {
        return new RouteDTO();
    }

    @ModelAttribute("orderDTO")
    public OrderDTO getOrderDTO() {
        return new OrderDTO();
    }

    @ModelAttribute("cargoes")
    public List<CargoDTO> getCargosDTO() {
        return new ArrayList<>();
    }


    //TODO implement
    @PostMapping(value = "save-order")
    public String saveOrder(@ModelAttribute("routeDTO") RouteDTO routeDTO,
                            @ModelAttribute("orderDTO") OrderDTO orderDTO,
                            SessionStatus sessionStatus) {

        orderService.saveOrder(routeDTO, orderDTO);
        sessionStatus.setComplete();

        return "redirect:managerPage";
    }

    @PostMapping(value = "abort-order")
    public String abortOrder(SessionStatus sessionStatus) {

        sessionStatus.setComplete();

        return "redirect:managerPage";
    }

    @PostMapping(value = "select-driver")
    public String selectDrivers(@ModelAttribute("routeDTO") RouteDTO routeDTO,
                              @ModelAttribute("point") RoutePointDTO point,
                              @ModelAttribute("orderDTO") OrderDTO orderDTO,
                              Model model) {

        Long time = orderService.getRouteTime(routeDTO, orderDTO);
        LinkedHashMap<Long, Driver> drivers = orderService.getDriversForOrder(time, orderDTO);

        logger.info(TAG + ": called selectDrivers(args...)" +
                "{time = " + time + " hours" + "\n" +
                "drivers map = " +drivers.values() +
                "}");

        model.addAttribute("routeDTO", routeDTO);
        model.addAttribute("drivers", drivers);
        model.addAttribute("orderDTO", orderDTO);

        return "select-driver";

    }

    @GetMapping(value = "new-order")
    public String showOrderFirstPage(@ModelAttribute("routeDTO") RouteDTO routeDTO,
                                     @ModelAttribute("orderDTO") OrderDTO orderDTO,
                                     Model model) {
        model.addAttribute("route-point", new RoutePointDTO());
        model.addAttribute("cities", orderService.getAllCitiesMap());
        return "order-start";
    }

    //TODO implement
    @PostMapping(value = "delete-point")
    public String deletePoint(@ModelAttribute("routeDTO") RouteDTO routeDTO,
                              @RequestParam("pointDelete") String routePoint,
                              Model model) {

        logger.info(TAG + ": called deletePoint(" + routeDTO + ", " + routePoint +")");

        List<RoutePointDTO> points = routeDTO.getRoutePoints();
        RoutePointDTO deletePoint = null;
        for (RoutePointDTO point : points) {
            if (point.toString().equals(routePoint)) {
                deletePoint = point;
                if (deletePoint.getType().equals(RoutePointType.PICK_UP)) {
                    if (point.getCargoDTO() == null) {
                        logger.info(TAG + ": condition [ point.getCargoDTO == null ] returns true, " +
                                "point.getType() equals " + point.getType());
                        points.remove(deletePoint);
                        break;
                    }
                    Long weight = routeDTO.getWeight();
                    logger.info(TAG + ": deletePoint(args...) initial weight = " + weight);
                    weight -= deletePoint.getCargoDTO().getWeight();
                    routeDTO.setWeight(weight);
                    logger.info(TAG + ": deletePoint(args...) final weight = " + weight);
                }
                if (deletePoint.getType().equals(RoutePointType.DROP_OFF)) {
                    for (RoutePointDTO routePointDTO : points) {
                        List<String> cargoes = deletePoint.getCargoesForDroppingOff();
                        for (String cargo : cargoes) {
                            if (routePointDTO.getCargoDTO().toString().equals(cargo)) {
                                routePointDTO.getCargoDTO().setDropLocationSelected(false);
                                logger.info("deleteRoutePoint(): drop off point cargo location selected:" +
                                        routePointDTO.getCargoDTO().getDropLocationSelected());

                            }

                        }

                    }
                }
                points.remove(point);
                break;
            }
        }

        model.addAttribute("route-point", new RoutePointDTO());
        return "redirect:order";
    }

    @GetMapping(value = "/order")
    public String showOrderPage(@ModelAttribute("routeDTO") RouteDTO routeDTO,
                                @ModelAttribute("route-point") RoutePointDTO routePointDTO,
                                @ModelAttribute("orderDTO") OrderDTO orderDTO,
                                Model model) {
        List<RoutePointDTO> points = routeDTO.getRoutePoints();
        orderService.setDropLocations(routeDTO, points);
        Long weight = routeDTO.getWeight();

        model.addAttribute("route-point", new RoutePointDTO());
        model.addAttribute("trucks", orderService.getTrucksForOrder(weight));
        model.addAttribute("cities", orderService.getAllCitiesMap());
        return "order";
    }

    @PostMapping(value = "order-main")
    public String processNewRoutePoint(@ModelAttribute("routeDTO") RouteDTO routeDTO,
                                    @ModelAttribute("route-point") RoutePointDTO routePointDTO,
                                    @ModelAttribute("orderDTO") OrderDTO orderDTO,
                                    Model model) {

        if (orderDTO.getCustomer() == null) {
            orderDTO.setCustomer(routePointDTO.getCustomerName());
        }
        List<RoutePointDTO> points = routeDTO.getRoutePoints();
        orderService.newRoutePoint(routePointDTO, routeDTO, points);

        routeDTO.addRoutePoint(routePointDTO);
        points = routeDTO.getRoutePoints();
        Long weight = routeDTO.getWeight();

        weight = orderService.getWeight(routePointDTO, points, weight);

        routeDTO.setWeight(weight);

        orderService.setDropLocations(routeDTO, points);

        model.addAttribute("route-point", new RoutePointDTO());
        model.addAttribute("trucks", orderService.getTrucksForOrder(weight));
        model.addAttribute("cities", orderService.getAllCitiesMap());
        return "redirect:order";
    }

    @PostMapping("/delete-order")
    public String deleteOrder(@RequestParam("orderId") Long id) {
        orderService.deleteOrder(id);
        return "redirect:managerPage";
    }
}

