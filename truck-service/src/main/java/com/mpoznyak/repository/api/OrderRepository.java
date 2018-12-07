package com.mpoznyak.repository.api;

import com.mpoznyak.model.Order;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 19:08
 */
public interface OrderRepository {

    Order findOrderById(Long id);

    void remove(Order order);

    void add(Iterable<Order> orders);

    void add(Order order);

    void remove(Long id);

    void update(Order order);

    List<Order> query();

    List<Order> queryExisted();
}
