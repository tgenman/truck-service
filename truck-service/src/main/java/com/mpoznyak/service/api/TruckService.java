package com.mpoznyak.service.api;

import com.mpoznyak.dto.TruckDTO;
import com.mpoznyak.dto.rest.TruckDTORest;
import com.mpoznyak.model.Truck;
import com.mpoznyak.validator.form.TruckForm;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 17:40
 */
public interface TruckService {

    void saveNewTruck(TruckDTO truckDTO);

    void saveNewTruck(TruckDTORest truckDTORest);

    TruckDTORest getTruckDTORestById(Long id);

    void updateTruck(TruckDTORest truckDTORest);

    void saveNewTruck(TruckForm truckForm);

    List<String> getTrucksStatus();

    List<String> getTrucksDetails();

    List<Truck> getAllTrucks();

    void updateTruck(TruckDTO truckDTO);

    void deleteTruck(Long id);

    LinkedHashMap<Long,Truck> getTrucksForOrder(Long cargoCapacity);

    Truck getTruckById(Long id);

    void updateTruck(Truck truck);

    List<TruckDTORest> getAllTrucksDTORest();
}
