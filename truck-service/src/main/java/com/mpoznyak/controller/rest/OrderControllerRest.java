package com.mpoznyak.controller.rest;

import com.mpoznyak.dto.rest.OrderDTORest;
import com.mpoznyak.service.api.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 15:15
 */

@RestController
@RequestMapping("/api/order")
public class OrderControllerRest {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    @Secured("ROLE_ADMIN")
    public List<OrderDTORest> getAllOrders() {
        return orderService.getAllOrdersDTORest();
    }
}
