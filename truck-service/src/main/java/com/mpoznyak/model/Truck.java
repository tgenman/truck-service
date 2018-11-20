package com.mpoznyak.model;


import com.mpoznyak.model.type.TruckStatus;

import javax.persistence.*;
import java.util.Objects;

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
    private Long workingSession;

    @Column(name = "capacity")
    private Long capacity;

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
    private Long maxDrivers;

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

    public Long getMaxDrivers() {
        return maxDrivers;
    }

    public void setMaxDrivers(Long maxDrivers) {
        this.maxDrivers = maxDrivers;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public TruckStatus getStatus() {
        return status;
    }

    public void setStatus(TruckStatus status) {
        this.status = status;
    }

    public void setWorkingSession(Long workingSession) {
        this.workingSession = workingSession;
    }

    public Long getWorkingSession() {
        return workingSession;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Truck truck = (Truck) o;
        return Objects.equals(id, truck.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
