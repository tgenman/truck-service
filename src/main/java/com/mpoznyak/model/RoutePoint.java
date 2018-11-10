package com.mpoznyak.model;

import com.mpoznyak.model.type.RoutePointType;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Max Poznyak
 * on 18/10/2018  at 20:15
 */

@Entity
public class RoutePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private RoutePointType type;

    @Column(name = "completed")
    private Boolean completed = false;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "route_sequence_index")
    private Integer routeSequnceIndex;

    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @OneToMany(mappedBy = "dropoff", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Cargo> cargoesForDrop;

    @Column(name = "deleted")
    private Boolean deleted = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoutePointType getType() {
        return type;
    }

    public void setType(RoutePointType type) {
        this.type = type;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public Integer getRouteSequnceIndex() {
        return routeSequnceIndex;
    }

    public void setRouteSequnceIndex(Integer routeSequnceIndex) {
        this.routeSequnceIndex = routeSequnceIndex;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<Cargo> getCargoesForDrop() {
        return cargoesForDrop;
    }

    public void setCargoesForDrop(List<Cargo> cargoesForDrop) {
        this.cargoesForDrop = cargoesForDrop;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
