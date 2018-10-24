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
import java.util.StringJoiner;

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

    public List<String> getTrucksStatus() {
        List<String> truckStatusList = new ArrayList<>();
        for (TruckStatus truckStatus : TruckStatus.values()) {
            truckStatusList.add(truckStatus.name());
        }
        return truckStatusList;
    }

    @Transactional
    public List<String> getTrucksDetails() {
        List<Truck> trucks = truckRepository.query();
        List<String> details = new ArrayList<>();
        for (Truck truck : trucks) {
            StringJoiner truckDetails = new StringJoiner(" ");
            truckDetails.add("brand: " + truck.getBrand());
            truckDetails.add("model: " + truck.getModel());
            truckDetails.add("license plate: " + truck.getLicensePlate());
            details.add(truckDetails.toString());
        }
        return details;
    }

    @Transactional
    public List<Truck> getAllTrucks() {
        return truckRepository.query();
    }






}
