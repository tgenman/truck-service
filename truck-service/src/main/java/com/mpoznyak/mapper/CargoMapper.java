package com.mpoznyak.mapper;

import com.mpoznyak.dto.rest.CargoDTORest;
import com.mpoznyak.model.Cargo;
import org.springframework.stereotype.Component;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 14:33
 */

@Component
public class CargoMapper {


    public CargoDTORest mapToDTORestFrom(Cargo cargo) {
        CargoDTORest dto = new CargoDTORest();
        dto.setId(cargo.getId());
        dto.setName(cargo.getName());
        if (cargo.getDrop() != null) {
            dto.setRoutePointDropOff(cargo.getDrop().getId());
        }
        dto.setStatus(cargo.getStatus().toString());
        dto.setWeight(cargo.getWeight());

        return dto;
    }
}
