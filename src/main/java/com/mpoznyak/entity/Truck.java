package com.mpoznyak.entity;

import javax.persistence.*;

@Entity
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "labour_time")
    private Integer labourTime;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "operable")
    private Integer operable;

    @Column(name = "city")
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLabourTime() {
        return labourTime;
    }

    public void setLabourTime(Integer labourTime) {
        this.labourTime = labourTime;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getOperable() {
        return operable;
    }

    public void setOperable(Integer operable) {
        this.operable = operable;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
