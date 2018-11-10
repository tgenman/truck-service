package com.mpoznyak.model;



import com.mpoznyak.model.type.CargoStatus;

import javax.persistence.*;

@Entity
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CargoStatus status;

    @ManyToOne
    @JoinColumn(name = "dropoff")
    private RoutePoint dropoff;

    @Column(name = "deleted")
    private Boolean deleted = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public CargoStatus getStatus() {
        return status;
    }

    public void setStatus(CargoStatus status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public RoutePoint getDrop() {
        return dropoff;
    }

    public void setDrop(RoutePoint drop) {
        this.dropoff = drop;
    }
}
