package com.mpoznyak.controller.rest;

import com.mpoznyak.dto.rest.CustomerDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.Customer;
import com.mpoznyak.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 15:00
 */

@RestController
@RequestMapping("/rest/customer")
public class CustomerControllerRest {

    @Autowired
    private CustomerService customerService;

    @Loggable
    @GetMapping("/list")
    public List<CustomerDTORest> getAllCustomers() {
        return customerService.getAllCustomersDTORest();
    }
}
