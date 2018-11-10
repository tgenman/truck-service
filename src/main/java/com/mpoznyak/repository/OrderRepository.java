package com.mpoznyak.repository;

import com.mpoznyak.model.Driver;
import com.mpoznyak.model.Order;
import com.mpoznyak.model.RoutePoint;
import com.mpoznyak.model.Truck;
import com.mpoznyak.model.type.DriverStatus;
import org.springframework.beans.factory.annotation.Autowired;
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
 * on 06/11/2018  at 11:14
 */

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private RoutePointRepository routePointRepository;

    @Autowired CustomerRepository customerRepository;

    public void add(Order order) {
        add(Collections.singletonList(order));
    }

    public void add(Iterable<Order> orders) {
        for (Order order : orders) {
            entityManager.persist(order);
        }
    }

    //TODO refactor, move repositories to service level and call from custom delete service method
    public void remove(Order order) {

        Order orderDb = entityManager.find(Order.class, order.getId());

        Truck truck = orderDb.getTruck();
        truck.setFree(true);
        truckRepository.update(truck);

        List<Driver> drivers = orderDb.getDrivers();
        for (Driver driver : drivers) {
            driver.setOrder(null);
            driver.setTruck(null);
            driver.setStatus(DriverStatus.FREE);
            driverRepository.update(driver);
        }

        List<RoutePoint> routePoints = routePointRepository.query();
        for (RoutePoint routePoint : routePoints) {
            if (routePoint.getOrder().getId() == orderDb.getId()) {
                routePointRepository.removeFull(routePoint);
            }
        }


        customerRepository.remove(orderDb.getCustomer());

        Query query = entityManager.createQuery("UPDATE Order d SET d.deleted = true " +
                "WHERE d.id = :id");
        query.setParameter("id", order.getId());
        query.executeUpdate();
    }

    public void remove(Long id) {
        Order order = new Order();
        order.setId(id);
        remove(order);
    }

    public void update(Order order) {
        Order orderDb = entityManager.find(Order.class, order.getId());
        orderDb.setCustomer(order.getCustomer());
        orderDb.setDate(order.getDate());
        orderDb.setTruck(order.getTruck());
        orderDb.setDrivers(order.getDrivers());
        orderDb.setStatus(order.getStatus());
    }

    public List<Order> query() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = query.from(Order.class);
        query.select(orderRoot);
        TypedQuery<Order> typedQuery = entityManager.createQuery(query);
        List<Order> orders = typedQuery.getResultList();
        return orders;
    }

    public List<Order> queryExisted() {

        Query query = entityManager.createQuery("SELECT d FROM Order d WHERE d.deleted = false ");
        List<Order> orders = query.getResultList();
        return orders;
    }
}
