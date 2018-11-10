package com.mpoznyak.dto;

import com.mpoznyak.model.Cargo;
import com.mpoznyak.model.Driver;
import com.mpoznyak.model.RoutePoint;
import com.mpoznyak.model.Truck;

import java.time.LocalDateTime;
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

    private LocalDateTime date = LocalDateTime.now();

    private List<Long> drivers = new ArrayList<>();

    private List<Driver> driversList = new ArrayList<>();

    private Long truck;

    private Truck truckObject;

    private Long manager;

    private List<RoutePoint> routePoints;

    private List<Cargo> cargoes;

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

    public List<Long> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Long> drivers) {
        this.drivers = drivers;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<RoutePoint> getRoutePoints() {
        return routePoints;
    }

    public void setRoutePoints(List<RoutePoint> routePoints) {
        this.routePoints = routePoints;
    }

    public List<Cargo> getCargoes() {
        return cargoes;
    }

    public void setCargoes(List<Cargo> cargoes) {
        this.cargoes = cargoes;
    }

    public List<Driver> getDriversList() {
        return driversList;
    }

    public void setDriversList(List<Driver> driversList) {
        this.driversList = driversList;
    }

    public Truck getTruckObject() {
        return truckObject;
    }

    public void setTruckObject(Truck truckObject) {
        this.truckObject = truckObject;
    }
}
