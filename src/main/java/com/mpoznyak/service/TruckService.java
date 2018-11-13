package com.mpoznyak.service;

import com.mpoznyak.dto.DriverDTO;
import com.mpoznyak.dto.TruckDTO;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.mapper.TruckMapper;
import com.mpoznyak.model.City;
import com.mpoznyak.model.Driver;
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

    @Loggable
    @Transactional
    public void saveNewTruckData(TruckDTO truckDTO) {
        Truck truck = truckMapper.map(truckDTO);
        truckRepository.add(truck);
    }

    @Loggable
    public List<String> getTrucksStatus() {
        List<String> truckStatusList = new ArrayList<>();
        for (TruckStatus truckStatus : TruckStatus.values()) {
            truckStatusList.add(truckStatus.name());
        }
        return truckStatusList;
    }

    @Loggable
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

    @Loggable
    @Transactional
    public List<Truck> getAllTrucks() {
        return truckRepository.query();
    }

    @Loggable
    @Transactional
    public void updateTruck(TruckDTO truckDTO) {
        Truck truck = truckMapper.map(truckDTO);
        truckRepository.update(truck);
    }

    @Loggable
    @Transactional
    public void deleteTruck(Long id) {
        truckRepository.remove(id);
    }

    @Loggable
    @Transactional
    public LinkedHashMap<Long,Truck> getTrucksForOrder(Long cargoCapacity) {

        List<Truck> trucks = truckRepository.query();
        LinkedHashMap<Long, Truck> map = new LinkedHashMap<>();
        for (int i = 0; i < trucks.size(); i++) {
            Truck truck = trucks.get(i);
            if (truck.getStatus().equals(TruckStatus.OPERABLE) && truck.isFree()
                    && (truck.getCapacity() - cargoCapacity) >= 0) {
                map.put(truck.getId(), truck);
            }
        }
        return map;
    }

    @Loggable
    @Transactional
    public Truck getTruckById(Long id) {
        List<Truck> trucks = truckRepository.query();
        for (Truck truck : trucks) {
            if (truck.getId() == id) {
                return truck;
            }
        }
        return null;
    }

    @Loggable
    @Transactional
    public void updateTruck(Truck truck) {
        truckRepository.update(truck);
    }




}
