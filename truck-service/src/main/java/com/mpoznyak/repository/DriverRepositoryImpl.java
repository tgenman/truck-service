package com.mpoznyak.repository;

import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.Driver;
import com.mpoznyak.repository.api.DriverRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;


import java.util.Collections;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 18/10/2018  at 20:15
 */

@Repository
public class DriverRepositoryImpl implements DriverRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Loggable
    public void add(Driver driver) {
        add(Collections.singletonList(driver));
    }

    @Override
    @Loggable
    public void add(Iterable<Driver> drivers) {
        for (Driver driver : drivers) {
            entityManager.persist(driver);
        }
    }

    @Override
    @Loggable
    public void remove(Driver driver) {
        Query query = entityManager.createQuery("UPDATE Driver d SET d.deleted = true " +
                "WHERE d.id = :id");
        query.setParameter("id", driver.getId());
        query.executeUpdate();
    }

    @Override
    @Loggable
    public void remove(Long id) {
        Driver driver = new Driver();
        driver.setId(id);
        remove(driver);
    }

    @Override
    @Loggable
    public void update(Driver driver) {
        Driver driverDb = (Driver) entityManager.find(Driver.class, driver.getId());
        driverDb.setFirstName(driver.getFirstName());
        driverDb.setLastName(driver.getLastName());
        driverDb.setOrder(driver.getOrder());
        driverDb.setCity(driver.getCity());
        driverDb.setTruck(driver.getTruck());
        driverDb.setStatus(driver.getStatus());
    }

    @Override
    @Loggable
    public List<Driver> query() {


        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Driver> query = criteriaBuilder.createQuery(Driver.class);
        Root<Driver> driverRoot = query.from(Driver.class);
        query.select(driverRoot);
        TypedQuery<Driver> typedQuery = entityManager.createQuery(query);
        List<Driver> drivers = typedQuery.getResultList();
        return drivers;
    }

    @Override
    @Loggable
    public List<Driver> queryExisted() {

        Query query = entityManager.createQuery("SELECT d FROM Driver d WHERE d.deleted = false ");
        List<Driver> drivers = query.getResultList();
        return drivers;
    }
}
