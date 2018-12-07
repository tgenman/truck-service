package com.mpoznyak.service;

import com.mpoznyak.dto.rest.CustomerDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.mapper.CustomerMapper;
import com.mpoznyak.model.Customer;
import com.mpoznyak.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private CustomerMapper customerMapper;

    @Transactional
    @Loggable
    public void saveCustomer(Customer customer) {
        customerRepository.add(customer);
    }

    @Transactional
    @Loggable
    public List<CustomerDTORest> getAllCustomersDTORest() {
        List<Customer> customers = customerRepository.query();
        List<CustomerDTORest> dtos = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDTORest dto = customerMapper.mapToDTORestFrom(customer);
            dtos.add(dto);
        }

        return dtos;
    }
}
