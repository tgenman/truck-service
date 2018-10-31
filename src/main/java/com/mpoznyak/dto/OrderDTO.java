package com.mpoznyak.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 26/10/2018  at 21:11
 */

public class OrderDTO {

    private Long id;

    private String customer;

    private String status;

    private List<Long> driver = new ArrayList<>();

    private Long truck;

    private Long manager;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Long> getDriver() {
        return driver;
    }

    public void setDriver(List<Long> driver) {
        this.driver = driver;
    }

    public Long getTruck() {
        return truck;
    }

    public void setTruck(Long truck) {
        this.truck = truck;
    }

    public Long getManager() {
        return manager;
    }

    public void setManager(Long manager) {
        this.manager = manager;
    }
}
