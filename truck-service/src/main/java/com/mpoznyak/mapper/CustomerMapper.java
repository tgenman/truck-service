package com.mpoznyak.mapper;

import com.mpoznyak.dto.rest.CustomerDTORest;
import com.mpoznyak.logging.annotation.Loggable;
import com.mpoznyak.model.Customer;
import org.springframework.stereotype.Component;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 15:03
 */

@Component
public class CustomerMapper {

    @Loggable
    public CustomerDTORest mapToDTORestFrom(Customer customer) {
        CustomerDTORest dto = new CustomerDTORest();

        dto.setId(customer.getId());
        dto.setName(customer.getName());

        return dto;
    }
}
