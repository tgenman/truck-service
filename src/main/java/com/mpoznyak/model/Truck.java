package com.mpoznyak.model;

import com.mpoznyak.model.type.TruckStatus;

import javax.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "max_drivers")
    private Integer maxDrivers;

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


}
