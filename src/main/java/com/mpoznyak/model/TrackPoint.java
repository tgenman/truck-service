package com.mpoznyak.model;

import com.mpoznyak.model.type.TrackPointType;

import javax.persistence.*;

/**
 * Created by Max Poznyak
 * on 18/10/2018  at 20:15
 */

@Entity
public class TrackPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TrackPointType type;

    @OneToOne
    @JoinColumn(name = "order_id")
    private DeliveryOrder deliveryOrder;

    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrackPointType getType() {
        return type;
    }

    public void setType(TrackPointType type) {
        this.type = type;
    }

    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }


}
