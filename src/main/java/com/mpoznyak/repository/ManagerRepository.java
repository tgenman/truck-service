package com.mpoznyak.repository;

import com.mpoznyak.configuration.Constants;
import com.mpoznyak.model.Manager;
import com.mpoznyak.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 18/10/2018  at 20:14
 */
@Repository
public class ManagerRepository {

    public void add(Manager manager) {
        add(Collections.singletonList(manager));
    }

    public void add(Iterable<Manager> managers) {
        for (Manager manager : managers) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
            EntityManager entityManager = factory.createEntityManager();
            EntityTransaction transaction= entityManager.getTransaction();
            transaction.begin();
            User user = new User();
            user.setCompanyId(Long.valueOf(123));
            user.setPassword("123");
            user.setManager(manager);
            entityManager.persist(manager);
            entityManager.persist(user);
            transaction.commit();
            entityManager.close();
            factory.close();
        }
    }

    public void remove(Manager manager) {

    }

    public void update(Manager manager) {

    }

    public List<Manager> query() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = factory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Manager> query = criteriaBuilder.createQuery(Manager.class);
        Root<Manager> manager = query.from(Manager.class);
        query.select(manager);
        TypedQuery<Manager> typedQuery = entityManager.createQuery(query);
        List<Manager> managers = typedQuery.getResultList();
        return managers;
    }


}
