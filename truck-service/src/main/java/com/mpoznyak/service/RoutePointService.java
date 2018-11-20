package com.mpoznyak.service;

import com.mpoznyak.dto.RoutePointDTO;
import com.mpoznyak.dto.rest.RoutePointDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.mapper.RoutePointMapper;
import com.mpoznyak.model.Cargo;
import com.mpoznyak.model.Order;
import com.mpoznyak.model.RoutePoint;
import com.mpoznyak.model.type.CargoStatus;
import com.mpoznyak.model.type.RoutePointType;
import com.mpoznyak.repository.CargoRepository;
import com.mpoznyak.repository.RoutePointRepository;
import java.util.ArrayList;
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
    private RoutePointMapper routePointMapper;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private OrderService orderService;

    @Loggable
    @Transactional
    public void updateRoutePoint(RoutePoint routePoint) {


        routePointRepository.update(routePoint);
    }

    @Loggable
    @Transactional
    public void updatePointStatusForOrder(RoutePoint routePoint) {

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

        routePoint.setCompleted(true);
        routePointRepository.update(routePoint);

    }

    @Loggable
    @Transactional
    public void updateRoutePoint(RoutePointDTO routePointDTO) {
        List<RoutePoint> pointList = routePointRepository.query();
        for (RoutePoint routePoint : pointList) {
            if (routePointDTO.getId() == routePoint.getId()) {
                updatePointStatusForOrder(routePoint);
                routePointRepository.update(routePoint);
                break;
            }
        }
    }

    @Loggable
    @Transactional
    public List<RoutePoint> getRoutePoints() {
        return routePointRepository.query();
    }

    @Loggable
    @Transactional
    public List<RoutePointDTORest> getAllRoutePointsDTORest() {
        List<RoutePoint> routePoints = getRoutePoints();
        List<RoutePointDTORest> dtos = new ArrayList<>();

        for (RoutePoint routePoint : routePoints) {
            RoutePointDTORest dto = routePointMapper.mapToDTORestFrom(routePoint);
            dtos.add(dto);
        }
        return dtos;
    }
}
