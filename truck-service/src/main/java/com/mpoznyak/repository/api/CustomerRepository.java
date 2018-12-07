package com.mpoznyak.repository.api;

import com.mpoznyak.model.Customer;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 19:00
 */
public interface CustomerRepository {

    void add(Customer customer);

    void add(Iterable<Customer> customers);

    void remove(Customer customer);

    void remove(Long id);

    void update(Customer customer);

    List<Customer> query();

    List<Customer> queryExisted();
}
