package com.mpoznyak.service;

import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.Customer;
import com.mpoznyak.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Max Poznyak
 * on 27/10/2018  at 00:11
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    @Loggable
    public void saveCustomer(Customer customer) {
        customerRepository.add(customer);
    }
}
