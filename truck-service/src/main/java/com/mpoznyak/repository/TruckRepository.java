package com.mpoznyak.repository;

import com.mpoznyak.model.Driver;
import com.mpoznyak.model.Truck;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 18:28
 */

@Repository
public class TruckRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void add(Truck truck) {
        add(Collections.singletonList(truck));
    }

    public void add(Iterable<Truck> trucks) {
        for (Truck truck : trucks) {
            entityManager.persist(truck);
        }
    }

    public void remove(Truck truck) {
        Query query = entityManager.createQuery("UPDATE Truck d SET d.deleted = true " +
                "WHERE d.id = :id");
        query.setParameter("id", truck.getId());
        query.executeUpdate();
    }

    public void remove(Long id) {
        Truck truck = new Truck();
        truck.setId(id);
        remove(truck);
    }

    public void update(Truck truck) {
        Truck truckDb = (Truck) entityManager.find(Truck.class, truck.getId());
        truckDb.setBrand(truck.getBrand());
        truckDb.setModel(truck.getModel());
        truckDb.setFree(truck.isFree());
        truckDb.setCity(truck.getCity());
        truckDb.setStatus(truck.getStatus());
        truckDb.setCapacity(truck.getCapacity());
        truckDb.setWorkingSession(truck.getWorkingSession());
        truckDb.setMaxDrivers(truck.getMaxDrivers());
        truckDb.setLicensePlate(truck.getLicensePlate());
    }

    public List<Truck> query() {


        Query query = entityManager.createQuery("SELECT d FROM Truck d WHERE d.deleted = false ");
        List<Truck> trucks = query.getResultList();
        return trucks;
    }

}
