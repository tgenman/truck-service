package com.mpoznyak.repository;

import com.mpoznyak.model.Driver;
import com.mpoznyak.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 18/10/2018  at 20:15
 */
@Repository
public class DriverRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void add(Driver driver) {
        add(Collections.singletonList(driver));
    }

    public void add(Iterable<Driver> drivers) {
        for (Driver driver : drivers) {
            entityManager.persist(driver);
        }
    }

    public void remove(Driver driver) {

    }

    public void update(Driver driver) {

    }

    public List<Driver> query() {


        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Driver> query = criteriaBuilder.createQuery(Driver.class);
        Root<Driver> driverRoot = query.from(Driver.class);
        query.select(driverRoot);
        TypedQuery<Driver> typedQuery = entityManager.createQuery(query);
        List<Driver> drivers = typedQuery.getResultList();
        return drivers;
    }

    //TODO implement
    public List<Driver> findDrivers() {
        return null;
    }
}
