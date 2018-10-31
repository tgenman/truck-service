package com.mpoznyak.model;


import com.mpoznyak.model.type.TruckStatus;

import javax.persistence.*;

/**
 * Created by Max Poznyak
 * on 18/10/2018  at 20:15
 */

@Entity
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "working_session")
    private Integer workingSession;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TruckStatus status;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "max_drivers")
    private Integer maxDrivers;

    @Column(name = "deleted")
    private Boolean deleted = false;

    @Column(name = "free")
    private Boolean free = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getWorkingSession() {
        return workingSession;
    }

    public void setWorkingSession(Integer workingSession) {
        this.workingSession = workingSession;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public TruckStatus getStatus() {
        return status;
    }

    public void setStatus(TruckStatus status) {
        this.status = status;
    }

    public Integer getMaxDrivers() {
        return maxDrivers;
    }

    public void setMaxDrivers(Integer maxDrivers) {
        this.maxDrivers = maxDrivers;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean isFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    @Override
    public String toString() {
        return "brand: " + brand + ", model: " + model + ", license plate: " + licensePlate;
    }
}
