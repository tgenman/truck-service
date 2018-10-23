package com.mpoznyak.service;

import com.mpoznyak.dto.TruckDTO;
import com.mpoznyak.mapper.TruckMapper;
import com.mpoznyak.model.City;
import com.mpoznyak.model.Truck;
import com.mpoznyak.model.type.TruckStatus;
import com.mpoznyak.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 14:52
 */

@Service
public class TruckService {

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private TruckMapper truckMapper;

    @Transactional
    public void saveNewTruckData(TruckDTO truckDTO) {
        Truck truck = truckMapper.map(truckDTO);
        truckRepository.add(truck);
    }

    public List<String> getAllTruckStatus() {
        List<String> truckStatusList = new ArrayList<>();
        for (TruckStatus truckStatus : TruckStatus.values()) {
            truckStatusList.add(truckStatus.name());
        }
        return truckStatusList;
    }

    @Transactional
    public List<Truck> getAllTrucks() {
        return truckRepository.query();
    }




}
