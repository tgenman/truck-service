package com.mpoznyak.service.api;

import com.mpoznyak.dto.rest.CustomerDTORest;
import com.mpoznyak.model.Customer;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 2018-12-07  at 16:53
 */

public interface CustomerService {

    void saveCustomer(Customer customer);

    List<CustomerDTORest> getAllCustomersDTORest();

    void deleteCustomer(Customer customer);

}
