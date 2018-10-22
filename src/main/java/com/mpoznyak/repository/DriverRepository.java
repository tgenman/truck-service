package com.mpoznyak.repository;

import com.mpoznyak.Constants;
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

    public void add(Driver driver) {
        add(Collections.singletonList(driver));
    }

    public void add(Iterable<Driver> drivers) {
        for (Driver driver : drivers) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
            EntityManager manager = factory.createEntityManager();
            EntityTransaction transaction= manager.getTransaction();
            transaction.begin();
            User user = new User();
            user.setCompanyId(Long.valueOf(456));
            user.setPassword("456");
            user.setDriver(driver);
            manager.persist(driver);
            manager.persist(user);
            transaction.commit();
            manager.close();
            factory.close();
        }
    }

    public void remove(Driver driver) {

    }

    public void update(Driver driver) {

    }

    public List<Driver> query() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = factory.createEntityManager();
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
