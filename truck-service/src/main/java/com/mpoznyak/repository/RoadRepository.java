package com.mpoznyak.repository;

import com.mpoznyak.model.Road;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 31/10/2018  at 00:33
 */

@Repository
public class RoadRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Road> query() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Road> query = criteriaBuilder.createQuery(Road.class);
        Root<Road> roadRoot = query.from(Road.class);
        query.select(roadRoot);
        TypedQuery<Road> typedQuery = entityManager.createQuery(query);
        List<Road> roads = typedQuery.getResultList();
        return roads;
    }
}
