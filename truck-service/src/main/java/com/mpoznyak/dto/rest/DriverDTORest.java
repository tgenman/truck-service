package com.mpoznyak.dto.rest;

import java.util.Objects;

/**
 * Created by Max Poznyak
 * on 11/20/18  at 14:17
 */
public class DriverDTORest {


    private Long id;
    private String firstName;
    private String lastName;
    private String status;
    private String city;
    private Long truckId;
    private Long orderId;
    private Long shiftId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getTruck() {
        return truckId;
    }

    public void setTruck(Long truck) {
        this.truckId = truck;
    }

    public Long getOrder() {
        return orderId;
    }

    public void setOrder(Long order) {
        this.orderId = order;
    }

    public Long getShift() {
        return shiftId;
    }

    public void setShift(Long shift) {
        this.shiftId = shift;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverDTORest driver = (DriverDTORest) o;
        return Objects.equals(id, driver.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
