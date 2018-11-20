package com.mpoznyak.repository;

import com.mpoznyak.model.Cargo;
import com.mpoznyak.model.Driver;
import com.mpoznyak.model.RoutePoint;
import com.mpoznyak.model.type.CargoStatus;
import com.mpoznyak.model.type.RoutePointType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 06/11/2018  at 14:20
 */

@Repository
public class RoutePointRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void add(RoutePoint routePoint) {
        add(Collections.singletonList(routePoint));
    }

    public void add(Iterable<RoutePoint> routePoints) {
        for (RoutePoint point : routePoints) {
            entityManager.persist(point);
        }
    }

    public void remove(RoutePoint routePoint) {
        Query query = entityManager.createQuery("UPDATE RoutePoint d SET d.deleted = true " +
                "WHERE d.id = :id");
        query.setParameter("id", routePoint.getId());
        query.executeUpdate();
    }

    public void remove(Long id) {
        RoutePoint routePoint = new RoutePoint();
        routePoint.setId(id);
        remove(routePoint);
    }

    public void removeFull(RoutePoint routePoint) {
        entityManager.remove(routePoint);
    }

    public void update(RoutePoint routePoint) {
        RoutePoint routePointDb = (RoutePoint) entityManager.find(RoutePoint.class, routePoint.getId());
        routePointDb.setCity(routePoint.getCity());
        Cargo cargo = routePoint.getCargo();
        routePointDb.setCargo(cargo);
        routePointDb.setCompleted(routePoint.getCompleted());
        routePointDb.setOrder(routePoint.getOrder());
        routePointDb.setType(routePoint.getType());
        routePointDb.setRouteSequnceIndex(routePoint.getRouteSequnceIndex());
    }

    public List<RoutePoint> query() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RoutePoint> query = criteriaBuilder.createQuery(RoutePoint.class);
        Root<RoutePoint> routePointRoot = query.from(RoutePoint.class);
        query.select(routePointRoot);
        TypedQuery<RoutePoint> typedQuery = entityManager.createQuery(query);
        List<RoutePoint> routePoints = typedQuery.getResultList();
        return routePoints;
    }

    public List<RoutePoint> queryExisted() {

        Query query = entityManager.createQuery("SELECT d FROM RoutePoint d WHERE d.deleted = false ");
        List<RoutePoint> routePoints = query.getResultList();
        return routePoints;
    }
}
