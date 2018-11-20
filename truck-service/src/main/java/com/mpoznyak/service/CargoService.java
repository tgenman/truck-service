package com.mpoznyak.service;

import com.mpoznyak.dto.rest.CargoDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.mapper.CargoMapper;
import com.mpoznyak.model.Cargo;
import com.mpoznyak.repository.CargoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 14:30
 */

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private CargoMapper cargoMapper;

    @Loggable
    @Transactional
    public List<Cargo> getAllCargoes() {
        return cargoRepository.query();
    }

    @Loggable
    @Transactional
    public List<CargoDTORest> getAllCargoesDTORest() {
        List<Cargo> cargoes = getAllCargoes();
        List<CargoDTORest> dtos = new ArrayList<>();

        for (Cargo cargo : cargoes) {
            CargoDTORest dto = cargoMapper.mapToDTORestFrom(cargo);
            dtos.add(dto);
        }

        return dtos;
    }
}
