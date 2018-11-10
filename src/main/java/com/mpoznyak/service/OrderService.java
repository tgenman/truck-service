package com.mpoznyak.service;

import com.mpoznyak.controller.OrderController;
import com.mpoznyak.dto.CargoDTO;
import com.mpoznyak.dto.OrderDTO;
import com.mpoznyak.dto.RouteDTO;
import com.mpoznyak.dto.RoutePointDTO;
import com.mpoznyak.model.*;
import com.mpoznyak.model.type.DriverStatus;
import com.mpoznyak.model.type.OrderStatus;
import com.mpoznyak.model.type.RoutePointType;
import com.mpoznyak.repository.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Max Poznyak
 * on 05/11/2018  at 21:40
 */

@Service
public class OrderService {

    private static final Logger logger = Logger.getLogger(OrderController.class);
    private static final String TAG = OrderController.class.getSimpleName();

    //TODO move all repos to particular services

    @Autowired
    private CityService cityService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private GraphService graphService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RoutePointRepository routePointRepository;

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private CargoRepository cargoRepository;


    public Long getRouteTime(RouteDTO routeDTO, OrderDTO orderDTO) {
        Long time = graphService.getRouteTime(routeDTO, orderDTO);
        return time;
    }

    public LinkedHashMap<Long, Driver> getDriversForOrder(Long time, OrderDTO orderDTO) {
        return driverService.getDriversForOrder(time, orderDTO);
    }

    public LinkedHashMap<Long, Truck> getTrucksForOrder(Long weight) {
        return truckService.getTrucksForOrder(weight);
    }

    public void setDropLocations(RouteDTO routeDTO, List<RoutePointDTO> points) {
        for (RoutePointDTO point : points) {
            if (point.getType().equals(RoutePointType.DROP_OFF))
                continue;
            if (point.getCargoDTO().getDropLocationSelected()) {
                routeDTO.setAllDroppingLocationsSelected(true);
            } else {
                routeDTO.setAllDroppingLocationsSelected(false);
            }
        }
    }

    public Long getWeight(RoutePointDTO routePointDTO, List<RoutePointDTO> points, Long weight) {

        logger.info("getWeight(routePointDTO: " + routePointDTO + " points: " + points + " \n" +
                ", weight: " + weight);
        if (routePointDTO.getType().equals(RoutePointType.DROP_OFF)) {
            routePointDTO.setCargoDTO(new CargoDTO());
            routePointDTO.getCargoDTO().setWeight(0L);
        }

        if (points.size() > 2 && !routePointDTO.getType().equals(RoutePointType.DROP_OFF)) {
            RoutePointDTO previousRoutePoint = points.get(points.size() - 2);

            if (routePointDTO.getType().equals(RoutePointType.PICK_UP)
                    && previousRoutePoint.getType().equals(RoutePointType.DROP_OFF)) {
                weight = routePointDTO.getCargoDTO().getWeight() - previousRoutePoint.getCargoDTO().getWeight();
            } else {
                weight += routePointDTO.getCargoDTO().getWeight();

            }
        } else if (points.size() > 0 && points.size() < 3 && !routePointDTO.getType().equals(RoutePointType.DROP_OFF)) {

            weight += routePointDTO.getCargoDTO().getWeight();

        }
        /*else if (points.size() > 0 && points.size() < 3 && routePointDTO.getType().equals(RoutePointType.DROP_OFF)) {
            routePointDTO.setCargoDTO(new CargoDTO());
            routePointDTO.getCargoDTO().setWeight(0L);
        }*/

        logger.info("getWeight(...) {" +
                "\n ..." +
                "weight: " + weight + "}");
        return weight;
    }

    public void newRoutePoint(RoutePointDTO routePointDTO, RouteDTO routeDTO, List<RoutePointDTO> points) {
        if (routePointDTO.getType().equals(RoutePointType.PICK_UP)) {
            routePointDTO.setType(RoutePointType.PICK_UP);
            routePointDTO.getCargoDTO().setPickedUpIn(routePointDTO);
            routePointDTO.getCargoDTO().setDropLocationSelected(false);
            routeDTO.setAllDroppingLocationsSelected(false);

        } else if (routePointDTO.getType().equals(RoutePointType.DROP_OFF)) {
            routePointDTO.setType(RoutePointType.DROP_OFF);
            List<String> droppingCargoes = routePointDTO.getCargoesForDroppingOff();

            for (RoutePointDTO point : points) {
                if (point.getType().equals(RoutePointType.DROP_OFF)) {
                    continue;
                }
                for (String droppingCargo : droppingCargoes) {
                    CargoDTO cargo = point.getCargoDTO();
                    if (cargo.toString().equals(droppingCargo)) {
                        cargo.setDroppedOffIn(routePointDTO);
                        cargo.setDropLocationSelected(true);

                    }
                }
            }
        }
    }

    public LinkedHashMap<Long, City> getAllCitiesMap() {
        return cityService.getAllCitiesMap();
    }

    //TODO implemenet

    @Transactional
    public void saveOrder(RouteDTO routeDTO, OrderDTO orderDTO) {

        Customer customer = new Customer();
        customer.setName(orderDTO.getCustomer());
        customerService.saveCustomer(customer);

        Order order = new Order();
        order.setCustomer(customer);

        Truck truck = truckService.getTruckById(orderDTO.getTruck());
        truck.setFree(false);

        truckRepository.update(truck);
        order.setTruck(truck);

        order.setDate(LocalDateTime.now());

        List<Driver> drivers = driverService.getAllDrivers();
        List<Long> driversDTO = orderDTO.getDrivers();
        List<Driver> driversForOrder = new ArrayList<>();

        for (Long id : driversDTO) {
            for (Driver driver : drivers) {
                if (driver.getId() == id)
                    driversForOrder.add(driver);
            }
        }
        for (Driver driver : driversForOrder) {
            driver.setOrder(order);
            driver.setTruck(truck);
            driver.setStatus(DriverStatus.DRIVER);
            driverService.updateDriver(driver);
        }

        order.setDrivers(driversForOrder);

        order.setStatus(OrderStatus.INCOMPLETED);
        orderRepository.add(order);

        //TODO move to service

        List<RoutePointDTO> pointsDTO = routeDTO.getRoutePoints();
        List<CargoDTO> cargoes = new ArrayList<>();
        List<Cargo> cargoObjectList = new ArrayList<>();

        List<RoutePoint> routePointList = new ArrayList<>();

        for (RoutePointDTO pointDTO : pointsDTO) {
            RoutePoint routePoint = new RoutePoint();
            routePoint.setType(pointDTO.getType());

            List<City> cities = cityService.getAllCitiesList();
            for (City city : cities) {
                if (city.getId() == pointDTO.getCityId()) {
                    routePoint.setCity(city);
                    break;
                }
            }

            //TODO move to service

            if (pointDTO.getType().equals(RoutePointType.PICK_UP)) {
                CargoDTO cargoDTO = pointDTO.getCargoDTO();
                Cargo cargo = new Cargo();
                cargo.setName(cargoDTO.getName());
                cargo.setWeight(cargoDTO.getWeight());
                cargoRepository.add(cargo);
                routePoint.setCargo(cargo);
                cargoes.add(cargoDTO);
            }

            if (pointDTO.getType().equals(RoutePointType.DROP_OFF)) {
                List<String> cargoesForDroppingOff = pointDTO.getCargoesForDroppingOff();
                List<Cargo> cargoesForDrop = new ArrayList<>();
                for (String cargoName : cargoesForDroppingOff) {
                    for (CargoDTO cargoDTO : cargoes) {
                        if (cargoDTO.toString().equals(cargoName)) {
                            Cargo cargo = new Cargo();
                            cargo.setName(cargoDTO.getName());
                            cargo.setWeight(cargoDTO.getWeight());
                            cargoesForDrop.add(cargo);
                        }
                    }
                }

                for (Cargo cargo : cargoesForDrop) {
                    cargo.setDrop(routePoint);
                }
                routePoint.setCargoesForDrop(cargoesForDrop);
            }

            routePoint.setOrder(order);
            routePointList.add(routePoint);
        }

        routePointRepository.add(routePointList);
    }

    @Transactional
    public List<OrderDTO> getAllOrdersDTO() {
        List<Order> orders = orderRepository.queryExisted();
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order : orders) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setCustomer(order.getCustomer().getName());
            orderDTO.setId(order.getId());
            orderDTO.setTruckObject(order.getTruck());
            orderDTO.setDriversList(order.getDrivers());
            orderDTO.setStatus(order.getStatus().toString());
            orderDTO.setRoutePoints(getRoutePointsForOrder(order));
            orderDTO.setCargoes(getCargoesForOrder(orderDTO));
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }

    @Transactional
    public List<RoutePoint> getRoutePointsForOrder(Order order) {
        List<RoutePoint> points = routePointRepository.query();
        List<RoutePoint> pointsForOrder = new ArrayList<>();
        for (RoutePoint point : points) {
            if (point.getOrder().getId() == order.getId()) {
                pointsForOrder.add(point);
            }
        }
        Collections.sort(pointsForOrder, new Comparator<RoutePoint>() {

            @Override
            public int compare(final RoutePoint id1, final RoutePoint id2) {
                return id1.getId().compareTo(id2.getId());
            }
        });
        return pointsForOrder;
    }

    @Transactional
    public List<Cargo> getCargoesForOrder(OrderDTO orderDTO) {
        List<RoutePoint> points = orderDTO.getRoutePoints();
        List<Cargo> cargoes = new ArrayList<>();
        for (RoutePoint routePoint : points) {
            if (routePoint.getCargo() != null) {
                cargoes.add(routePoint.getCargo());
            }
        }
        return cargoes;
    }

    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.remove(id);

    }

    @Transactional
    public void updateOrder(Order order) {
        orderRepository.update(order);

    }


}
