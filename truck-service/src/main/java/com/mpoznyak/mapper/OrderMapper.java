package com.mpoznyak.mapper;

import com.mpoznyak.dto.rest.OrderDTORest;
import com.mpoznyak.model.Driver;
import com.mpoznyak.model.Order;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 15:18
 */

@Component
public class OrderMapper {

    public OrderDTORest mapToDTORestFrom(Order order) {
        OrderDTORest dto = new OrderDTORest();
        dto.setId(order.getId());
        if (order.getCustomer() != null) {
            dto.setCustomerId(order.getCustomer().getId());
        }
        if (order.getTruck() != null) {
            dto.setTruckId(order.getTruck().getId());
        }
        if (order.getTempShift() != null) {
            dto.setTempShiftId(order.getTempShift().getId());
        }
        if (order.getDrivers() != null) {
            List<Driver> drivers = order.getDrivers();
            List<Long> driversId = new ArrayList<>();
            for (Driver driver : drivers) {
                driversId.add(driver.getId());
            }
            dto.setDriversId(driversId);
        }

        dto.setDate(order.getDate().toString());
        dto.setStatus(order.getStatus().toString());

        return dto;
    }
}
