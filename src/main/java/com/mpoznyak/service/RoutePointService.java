package com.mpoznyak.service;

import com.mpoznyak.dto.RoutePointDTO;
import com.mpoznyak.model.Cargo;
import com.mpoznyak.model.Order;
import com.mpoznyak.model.RoutePoint;
import com.mpoznyak.model.type.CargoStatus;
import com.mpoznyak.model.type.RoutePointType;
import com.mpoznyak.repository.CargoRepository;
import com.mpoznyak.repository.RoutePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Max Poznyak
 * on 11/8/18  at 13:09
 */

@Service
public class RoutePointService {

    @Autowired
    private RoutePointRepository routePointRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private OrderService orderService;

    @Transactional
    public void updateRoutePoint(RoutePoint routePoint) {


        routePointRepository.update(routePoint);
    }

    @Transactional
    public void updatePointStatusForOrder(RoutePoint routePoint, Order order) {

        List<RoutePoint> pointsForOrder = orderService.getRoutePointsForOrder(order);
        Cargo cargo = routePoint.getCargo();

        if (routePoint.getType().equals(RoutePointType.PICK_UP)) {
            cargo.setStatus(CargoStatus.PICKED_UP);
            cargoRepository.update(cargo);
            routePoint.setCargo(cargo);
        }

        if (routePoint.getType().equals(RoutePointType.DROP_OFF)) {
            List<Cargo> cargoesForDrop = routePoint.getCargoesForDrop();
            List<Cargo> cargoList = cargoRepository.query();

            for (Cargo cargoL : cargoList) {
                if (cargoL.getDrop() != null) {
                    continue;
                }
                for (Cargo cargo1 : cargoesForDrop) {
                    if (cargo1.getWeight() == cargoL.getWeight()
                            && cargo1.getName()
                            .equals(cargoL
                                    .getName())) {
                       cargoL.setStatus(CargoStatus.DROPPED_OFF);
                       cargoRepository.update(cargoL);
                    }
                }
            }
        }

        routePointRepository.update(routePoint);

    }

    @Transactional
    public void updateRoutePoint(RoutePointDTO routePointDTO) {
        List<RoutePoint> pointList = routePointRepository.query();
        for (RoutePoint routePoint : pointList) {
            if (routePointDTO.getId() == routePoint.getId()) {
                routePoint.setCompleted(routePointDTO.getCompleted());
                routePointRepository.update(routePoint);
                break;
            }
        }
    }

    @Transactional
    public List<RoutePoint> getRoutePoints() {
        return routePointRepository.query();
    }
}
