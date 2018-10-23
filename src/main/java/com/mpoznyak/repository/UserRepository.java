package com.mpoznyak.repository;

import com.mpoznyak.Constants;
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
 * on 19/10/2018  at 15:27
 */

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void add(User user) {
        add(Collections.singletonList(user));
    }

    public void add(Iterable<User> users) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        for (User user : users) {
            entityManager.persist(user);
            entityTransaction.commit();
        }

    }

    public void remove(User user) {

    }

    public void update(User role) {

    }

    public List<User> queryAll() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> roleRoot = query.from(User.class);
        query.select(roleRoot);
        TypedQuery<User> typedQuery = entityManager.createQuery(query);
        List<User> users = typedQuery.getResultList();
        return users;
    }
}
