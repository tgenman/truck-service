package com.mpoznyak.service;

import com.mpoznyak.dto.rest.CargoDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.mapper.CargoMapper;
import com.mpoznyak.model.Cargo;
import com.mpoznyak.repository.api.CargoRepository;
import com.mpoznyak.service.api.CargoService;
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
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private CargoMapper cargoMapper;

    @Override
    @Loggable
    @Transactional
    public List<Cargo> getAllCargoes() {
        return cargoRepository.query();
    }

    @Override
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
