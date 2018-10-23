package com.mpoznyak.repository;

import com.mpoznyak.model.Manager;
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
 * on 18/10/2018  at 20:14
 */

@Repository
public class ManagerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void add(Manager manager) {
        add(Collections.singletonList(manager));
    }

    public void add(Iterable<Manager> managers) {
        for (Manager manager : managers) {

        }
    }

    public void remove(Manager manager) {

    }

    public void update(Manager manager) {

    }

    public List<Manager> query() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Manager> query = criteriaBuilder.createQuery(Manager.class);
        Root<Manager> manager = query.from(Manager.class);
        query.select(manager);
        TypedQuery<Manager> typedQuery = entityManager.createQuery(query);
        List<Manager> managers = typedQuery.getResultList();
        return managers;
    }

    private User newUser(Long id, String password) {
        User user = new User();
        return user;
    }


}
