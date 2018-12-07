package com.mpoznyak.service;

import com.mpoznyak.dto.TruckDTO;
import com.mpoznyak.dto.rest.TruckDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.mapper.TruckMapper;
import com.mpoznyak.model.Truck;
import com.mpoznyak.model.type.TruckStatus;
import com.mpoznyak.repository.api.TruckRepository;
import com.mpoznyak.service.api.MessageEmitter;
import com.mpoznyak.service.api.TruckService;
import com.mpoznyak.validator.form.TruckForm;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringJoiner;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 14:52
 */

@Service
public class TruckServiceImpl implements TruckService {

    private static final Logger logger = Logger.getLogger(TruckServiceImpl.class);

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private TruckMapper truckMapper;

    @Autowired
    private MessageEmitter messageEmitter;

    @Override
    @Loggable
    @Transactional
    public void saveNewTruck(TruckDTO truckDTO) {
        Truck truck = truckMapper.map(truckDTO);
        truckRepository.add(truck);
    }

    @Override
    @Loggable
    @Transactional
    public void saveNewTruck(TruckDTORest truckDTORest) {
        TruckDTO truckDTO = truckMapper.mapToTruckDTOFrom(truckDTORest);
        saveNewTruck(truckDTO);
    }

    @Override
    @Loggable
    @Transactional
    public TruckDTORest getTruckDTORestById(Long id) {
        Truck truck = getTruckById(id);
        TruckDTORest truckDTORest = truckMapper.mapToDTORestFrom(truck);
        return truckDTORest;
    }

    @Override
    @Loggable
    @Transactional
    public void updateTruck(TruckDTORest truckDTORest) {
        TruckDTO truckDTO = truckMapper.mapToTruckDTOFrom(truckDTORest);
        updateTruck(truckDTO);
    }

    @Override
    @Loggable
    @Transactional
    public void saveNewTruck(TruckForm truckForm) {
        TruckDTO truckDTO = new TruckDTO();
        truckDTO.setBrand(truckForm.getBrand());
        truckDTO.setCapacity(truckForm.getCapacity());
        truckDTO.setCity(truckForm.getCity());
        truckDTO.setLicensePlate(truckForm.getLicensePlate());
        truckDTO.setMaxDrivers(truckForm.getMaxDrivers());
        truckDTO.setModel(truckForm.getModel());
        truckDTO.setStatus(truckForm.getStatus());
        truckDTO.setWorkingSession(truckForm.getWorkingSession());
        saveNewTruck(truckDTO);

        messageEmitter.produceMessage("Created a new truck");
    }

    @Override
    @Loggable
    public List<String> getTrucksStatus() {
        List<String> truckStatusList = new ArrayList<>();
        for (TruckStatus truckStatus : TruckStatus.values()) {
            truckStatusList.add(truckStatus.name());
        }
        return truckStatusList;
    }

    @Override
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

    @Override
    @Loggable
    @Transactional
    public List<Truck> getAllTrucks() {
        return truckRepository.query();
    }

    @Override
    @Loggable
    @Transactional
    public void updateTruck(TruckDTO truckDTO) {
        Truck truck = truckMapper.map(truckDTO);
        truckRepository.update(truck);

        messageEmitter.produceMessage("Updated a driver", truck.getId());
    }


    @Override
    @Loggable
    @Transactional
    public void deleteTruck(Long id) {
        truckRepository.remove(id);
        messageEmitter.produceMessage("Deleted a driver", id);
    }

    @Override
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

    @Override
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

    @Override
    @Loggable
    @Transactional
    public void updateTruck(Truck truck) {
        truckRepository.update(truck);
    }


    @Override
    @Loggable
    @Transactional
    public List<TruckDTORest> getAllTrucksDTORest() {
        List<Truck> truckEntities = truckRepository.query();
        List<TruckDTORest> truckDTOS = new ArrayList<>();

        for (Truck truckEntity : truckEntities) {
            TruckDTORest truckDTO = truckMapper.mapToDTORestFrom(truckEntity);
            truckDTOS.add(truckDTO);
        }

        return truckDTOS;
    }


}
