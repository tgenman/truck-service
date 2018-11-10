package com.mpoznyak.dto;


import com.mpoznyak.model.Shift;
import com.mpoznyak.model.type.DriverStatus;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 23:02
 */

public class DriverDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private Shift shift;

    private DriverStatus status;

    private Long cityId;

    private String truck;

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

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
}
