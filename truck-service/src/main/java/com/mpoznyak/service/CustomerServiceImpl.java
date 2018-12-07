package com.mpoznyak.service;

import com.mpoznyak.dto.rest.CustomerDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.mapper.CustomerMapper;
import com.mpoznyak.model.Customer;
import com.mpoznyak.repository.api.CustomerRepository;
import com.mpoznyak.service.api.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    @Transactional
    @Loggable
    public void saveCustomer(Customer customer) {
        customerRepository.add(customer);
    }

    @Override
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

    @Override
    @Transactional
    @Loggable
    public void deleteCustomer(Customer customer) {
        customerRepository.remove(customer);
    }
}
