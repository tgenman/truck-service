package com.mpoznyak.repository;

import com.mpoznyak.model.Manager;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 * Created by Max Poznyak
 * on 31/10/2018  at 00:33
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
            entityManager.persist(manager);

        }
    }

    public void remove(Manager manager) {
        entityManager.remove(manager);
    }

    public void remove(Long id) {
        Manager manager = entityManager.find(Manager.class, id);
        remove(manager);
    }

    public void update(Manager manager) {
        Manager managerDb = entityManager.find(Manager.class, manager.getId());
        managerDb.setFirstName(manager.getFirstName());
        managerDb.setLastName(manager.getLastName());
    }

    public List<Manager> query() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Manager> query = criteriaBuilder.createQuery(Manager.class);
        Root<Manager> managerRoot = query.from(Manager.class);
        query.select(managerRoot);
        TypedQuery<Manager> typedQuery = entityManager.createQuery(query);
        List<Manager> managers = typedQuery.getResultList();
        return managers;
    }
}
