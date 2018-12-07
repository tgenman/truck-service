package com.mpoznyak.repository;

import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.User;
import com.mpoznyak.repository.api.UserRepository;
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
 * on 19/10/2018  at 15:27
 */

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Loggable
    public void add(User user) {
        add(Collections.singletonList(user));
    }

    @Override
    @Loggable
    public void add(Iterable<User> users) {

        for (User user : users) {
            entityManager.persist(user);
        }

    }

    @Override
    @Loggable
    public void remove(User user) {
        entityManager.remove(user);
    }

    @Override
    @Loggable
    public void update(User user) {
        User userDb = entityManager.find(User.class, user.getId());
        userDb.setRole(user.getRole());
        userDb.setPassword(user.getPassword());
        userDb.setCompanyId(user.getCompanyId());

    }

    @Override
    @Loggable
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
