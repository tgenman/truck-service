package com.mpoznyak.service;

import com.mpoznyak.controller.OrderController;
import com.mpoznyak.dto.CargoDTO;
import com.mpoznyak.dto.OrderDTO;
import com.mpoznyak.dto.RouteDTO;
import com.mpoznyak.dto.RoutePointDTO;
import com.mpoznyak.dto.rest.OrderDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.mapper.OrderMapper;
import com.mpoznyak.model.Cargo;
import com.mpoznyak.model.City;
import com.mpoznyak.model.Customer;
import com.mpoznyak.model.Driver;
import com.mpoznyak.model.Order;
import com.mpoznyak.model.RoutePoint;
import com.mpoznyak.model.TempShift;
import com.mpoznyak.model.Truck;
import com.mpoznyak.model.type.DriverStatus;
import com.mpoznyak.model.type.OrderStatus;
import com.mpoznyak.model.type.RoutePointType;
import com.mpoznyak.repository.api.CargoRepository;
import com.mpoznyak.repository.api.OrderRepository;
import com.mpoznyak.repository.api.TruckRepository;
import com.mpoznyak.service.api.CityService;
import com.mpoznyak.service.api.CustomerService;
import com.mpoznyak.service.api.DriverService;
import com.mpoznyak.service.api.GraphService;
import com.mpoznyak.service.api.MessageEmitter;
import com.mpoznyak.service.api.OrderService;
import com.mpoznyak.service.api.RoutePointService;
import com.mpoznyak.service.api.TempShiftService;
import com.mpoznyak.service.api.TruckService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Max Poznyak
 * on 05/11/2018  at 21:40
 */

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = Logger.getLogger(OrderController.class);

    @Autowired
    private CityService cityService;

    @Autowired
    private OrderMapper orderMapper;

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
    private RoutePointService routePointService;

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private TempShiftService tempShiftService;

    @Autowired
    private MessageEmitter messageEmitter;


    @Override
    @Loggable
    public Long getRouteTime(RouteDTO routeDTO, OrderDTO orderDTO) {
        Long time = graphService.getRouteTime(routeDTO, orderDTO);
        return time;
    }

    @Override
    @Loggable
    public LinkedHashMap<Long, Driver> getDriversForOrder(Long time, OrderDTO orderDTO) {
        return driverService.getDriversForOrder(time, orderDTO);
    }

    @Override
    @Loggable
    public LinkedHashMap<Long, Truck> getTrucksForOrder(Long weight) {
        return truckService.getTrucksForOrder(weight);
    }

    @Override
    @Loggable
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

    @Override
    @Loggable
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

        logger.info("getWeight(...) {" +
                "\n ..." +
                "weight: " + weight + "}");

        return weight;
    }

    @Override
    @Loggable
    public void newRoutePoint(RoutePointDTO routePointDTO, RouteDTO routeDTO, List<RoutePointDTO> points) {

        List<City> cities = cityService.getAllCitiesList();

        for (City city : cities) {
            if (city.getId() == routePointDTO.getCityId()) {
                routePointDTO.setCityName(city.getName());
            }
        }

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

    @Override
    @Loggable
    public LinkedHashMap<Long, City> getAllCitiesMap() {
        return cityService.getAllCitiesMap();
    }


    @Override
    @Loggable
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
        TempShift tempShift = new TempShift();
        tempShiftService.saveNewTempShift(tempShift);
        order.setTempShift(tempShift);
        orderRepository.add(order);

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

        routePointService.addRoutePoints(routePointList);

        messageEmitter.produceMessage("Create a new order");
    }

    @Override
    @Loggable
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

    @Override
    @Loggable
    @Transactional
    public List<RoutePoint> getRoutePointsForOrder(Order order) {
        List<RoutePoint> points = routePointService.getRoutePoints();
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

    @Override
    @Loggable
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

    @Override
    @Loggable
    @Transactional
    public void deleteOrder(Long id) {

        Order order = orderRepository.findOrderById(id);
        Truck truck = order.getTruck();
        truck.setFree(true);
        truckService.updateTruck(truck);
        List<Driver> drivers = order.getDrivers();
        for (Driver driver : drivers) {
            driver.setOrder(null);
            driver.setTruck(null);
            driver.setStatus(DriverStatus.FREE);
            driverService.updateDriver(driver);
        }
        List<RoutePoint> routePoints = routePointService.getRoutePoints();
        for (RoutePoint routePoint : routePoints) {
            if (routePoint.getOrder().getId() == order.getId()) {
                routePointService.deleteRoutePoint(routePoint);
            }
        }
        customerService.deleteCustomer(order.getCustomer());
        orderRepository.remove(order);

        messageEmitter.produceMessage("Delete an order", id);


    }

    @Override
    @Loggable
    @Transactional
    public void updateOrder(Order order) {
        orderRepository.update(order);

    }

    @Override
    @Loggable
    @Transactional
    public List<OrderDTORest> getAllOrdersDTORest() {
        List<Order> orders = orderRepository.queryExisted();
        List<OrderDTORest> dtos = new ArrayList<>();

        for (Order order : orders) {
            OrderDTORest dto = orderMapper.mapToDTORestFrom(order);
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    @Loggable
    public void deleteRoutePoint(String routePoint, RouteDTO routeDTO) {

        List<RoutePointDTO> points = routeDTO.getRoutePoints();
        RoutePointDTO deletePoint = null;
        for (RoutePointDTO point : points) {
            if (point.toString().equals(routePoint)) {
                deletePoint = point;
                if (deletePoint.getType().equals(RoutePointType.PICK_UP)) {
                    if (point.getCargoDTO() == null) {

                        points.remove(deletePoint);
                        break;
                    }
                    Long weight = routeDTO.getWeight();
                    weight -= deletePoint.getCargoDTO().getWeight();
                    routeDTO.setWeight(weight);
                }
                if (deletePoint.getType().equals(RoutePointType.DROP_OFF)) {
                    for (RoutePointDTO routePointDTO : points) {
                        List<String> cargoes = deletePoint.getCargoesForDroppingOff();
                        for (String cargo : cargoes) {
                            if (routePointDTO.getCargoDTO().toString().equals(cargo)) {
                                routePointDTO.getCargoDTO().setDropLocationSelected(false);

                            }

                        }

                    }
                }
                points.remove(point);
                break;
            }
        }
    }
}
