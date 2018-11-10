package com.mpoznyak.repository;

import com.mpoznyak.model.Customer;
import com.mpoznyak.model.Driver;
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
 * on 18/10/2018  at 20:15
 */

@Repository
public class CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void add(Customer customer) {
        add(Collections.singletonList(customer));
    }

    public void add(Iterable<Customer> customers) {
        for (Customer customer : customers) {
            entityManager.persist(customer);
        }
    }

    public void remove(Customer customer) {
        Query query = entityManager.createQuery("UPDATE Customer d SET d.deleted = true " +
                "WHERE d.id = :id");
        query.setParameter("id", customer.getId());
        query.executeUpdate();
    }

    public void remove(Long id) {
        Customer customer = new Customer();
        customer.setId(id);
        remove(customer);
    }

    public void update(Customer customer) {
        Customer customerDb = entityManager.find(Customer.class, customer.getId());
        customerDb.setName(customer.getName());
    }

    public List<Customer> query() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = query.from(Customer.class);
        query.select(customerRoot);
        TypedQuery<Customer> typedQuery = entityManager.createQuery(query);
        List<Customer> customers = typedQuery.getResultList();
        return customers;
    }

    public List<Customer> queryExisted() {

        Query query = entityManager.createQuery("SELECT d FROM Customer d WHERE d.deleted = false ");
        List<Customer> customers = query.getResultList();
        return customers;
    }
}
