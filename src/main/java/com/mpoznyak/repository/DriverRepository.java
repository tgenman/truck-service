package com.mpoznyak.repository;

import com.mpoznyak.model.Driver;
import com.mpoznyak.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
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
        Query query = entityManager.createQuery("UPDATE Driver d SET d.deleted = true " +
                "WHERE d.id = :id");
        query.setParameter("id", driver.getId());
        query.executeUpdate();
    }

    public void remove(Long id) {
        Driver driver = new Driver();
        driver.setId(id);
        remove(driver);
    }

    public void update(Driver driver) {
        Driver driverDb = (Driver) entityManager.find(Driver.class, driver.getId());
        driverDb.setFirstName(driver.getFirstName());
        driverDb.setLastName(driver.getLastName());
        driverDb.setWorkedTime(driver.getWorkedTime());
        driverDb.setCity(driver.getCity());
        driverDb.setTruck(driver.getTruck());
        driverDb.setStatus(driver.getStatus());
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

    public List<Driver> queryExisted() {

        Query query = entityManager.createQuery("SELECT d FROM Driver d WHERE d.deleted = false ");
        List<Driver> drivers = query.getResultList();
        return drivers;
    }
}
