package com.mpoznyak.repository;

import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.Road;
import com.mpoznyak.repository.api.RoadRepository;
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
public class RoadRepositoryImpl implements RoadRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Loggable
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
