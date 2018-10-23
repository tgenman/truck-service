package com.mpoznyak.repository;

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

    }

    public void update(Truck driver) {

    }

    public List<Truck> query() {


        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Truck> query = criteriaBuilder.createQuery(Truck.class);
        Root<Truck> driverRoot = query.from(Truck.class);
        query.select(driverRoot);
        TypedQuery<Truck> typedQuery = entityManager.createQuery(query);
        List<Truck> trucks = typedQuery.getResultList();
        return trucks;
    }

    public List<Truck> findTrucks() {
        return null;
    }


}
