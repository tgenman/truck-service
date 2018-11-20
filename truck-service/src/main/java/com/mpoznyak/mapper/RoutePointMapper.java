package com.mpoznyak.mapper;

import com.mpoznyak.dto.rest.RoutePointDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.Cargo;
import com.mpoznyak.model.RoutePoint;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 16:32
 */

@Component
public class RoutePointMapper {

    @Loggable
    public RoutePointDTORest mapToDTORestFrom(RoutePoint routePoint) {
        RoutePointDTORest dto = new RoutePointDTORest();
        dto.setId(routePoint.getId());
        if (routePoint.getCargo() != null) {
            dto.setCargoId(routePoint.getCargo().getId());
        }
        dto.setCityName(routePoint.getCity().getName());
        dto.setCompleted(routePoint.getCompleted());
        dto.setType(routePoint.getType().toString());
        if (routePoint.getOrder() != null) {
            dto.setOrderId(routePoint.getOrder().getId());
        }
        if (routePoint.getCargoesForDrop() != null) {
            List<Cargo> dropCargoes = routePoint.getCargoesForDrop();
            List<Long> dropsId = new ArrayList<>();
            for (Cargo dropCargo : dropCargoes) {
                dropsId.add(dropCargo.getId());
            }
            dto.setCargoesForDropId(dropsId);
        }
        return dto;
    }

}
