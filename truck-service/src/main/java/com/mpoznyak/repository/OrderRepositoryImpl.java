package com.mpoznyak.repository;

import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.Order;
import com.mpoznyak.repository.api.OrderRepository;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 * Created by Max Poznyak
 * on 06/11/2018  at 11:14
 */

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Loggable
    public void add(Order order) {
        add(Collections.singletonList(order));
    }

    @Override
    @Loggable
    public void add(Iterable<Order> orders) {
        for (Order order : orders) {
            entityManager.persist(order);
        }
    }

    @Override
    @Loggable
    public Order findOrderById(Long id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    @Loggable
    public void remove(Order order) {
        Query query = entityManager.createQuery("UPDATE Order d SET d.deleted = true " +
                "WHERE d.id = :id");
        query.setParameter("id", order.getId());
        query.executeUpdate();
    }

    @Override
    @Loggable
    public void remove(Long id) {
        Order order = new Order();
        order.setId(id);
        remove(order);
    }

    @Override
    @Loggable
    public void update(Order order) {
        Order orderDb = entityManager.find(Order.class, order.getId());
        orderDb.setCustomer(order.getCustomer());
        orderDb.setDate(order.getDate());
        orderDb.setTruck(order.getTruck());
        orderDb.setDrivers(order.getDrivers());
        orderDb.setStatus(order.getStatus());
    }

    @Override
    @Loggable
    public List<Order> query() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = query.from(Order.class);
        query.select(orderRoot);
        TypedQuery<Order> typedQuery = entityManager.createQuery(query);
        List<Order> orders = typedQuery.getResultList();
        return orders;
    }

    @Override
    @Loggable
    public List<Order> queryExisted() {

        Query query = entityManager.createQuery("SELECT d FROM Order d WHERE d.deleted = false ");
        List<Order> orders = query.getResultList();
        return orders;
    }
}
